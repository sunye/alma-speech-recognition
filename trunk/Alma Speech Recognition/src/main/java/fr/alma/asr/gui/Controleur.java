package fr.alma.asr.gui;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.FolderDao;
import fr.alma.asr.dao.LessonDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.dao.impl.FolderDaoImpl;
import fr.alma.asr.dao.impl.LessonDaoImpl;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;
import fr.alma.asr.gui.tree.DialogProprietes;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controleur de l'IHM.
 * @author Braud Jeremy
 */
public final class Controleur {

	/** L'instance du controleur. */
	private static Controleur instance;
	
	private static HashMap<String, WorkPanel> workPanelList;
	
	
	/*------------------------------*/
	private String workPlanPosition = "right";
	private boolean workShowPlan = true;
	private boolean workShowCourses =true;
	
	
	/**
	 * Constructeur privé.
	 */
	private Controleur() {
		connexion();
	}
	
	/** L'instance du splash screen. */
	private static SplashScreen splash;
	
	/**
	 * Affiche le splash screen.
	 */
	public static void debutChargement() {
		splash = new SplashScreen(null, false);
		splash.setVisible(true);
	}

	/**
	 * Désactive le splash screen.
	 */
	public static void chargementTermine() {
		splash.setVisible(false);
		setInfo("Chargement terminé");
	}

	/**
	 * Accès à l'instance du controleur.
	 * @return l'instance du controleur
	 */
	public static Controleur getInstance() {
		workPanelList = new HashMap<String, WorkPanel>();
		if(instance==null)
			instance= new Controleur();
		
		return instance;
	}

	/**
	 * Méthode de connexion à H2.
	 */
	private void connexion() {
		String chemin = "./DataBase/db";
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:" + chemin);
		File fichier = new File(chemin + ".h2.db");
		if (!fichier.exists()) {
			Folder FolderRacine = new Folder("Cours");
			new FolderDaoImpl().create(FolderRacine);
		}
	}

	/**
	 * Méthode de déconnexion de H2.
	 */
	public void deconnexion() {
		AbstractDaoImpl.deconnexion();
	}

	/* ---------------------------------------------------------*/
	/* ---------------Gestion du panel d'info-------------------*/
	/* ---------------------------------------------------------*/

	/** Le panel d'info. */
	private static StatusPanel panelInfo;

	/**
	 * Enregistrement du panel d'info.
	 * @param info le PanelInfo
	 */
	public static void setStatusPanel(StatusPanel info) {
		panelInfo = info;
	}

	public static void setInfo(String information) {
		if (panelInfo != null) {
			panelInfo.setStatus(information);
		}
	}

	/* ---------------------------------------------------------*/
	/* -----------------Gestion de l'arbre----------------------*/
	/* ---------------------------------------------------------*/
	
	/**
	 * Construit l'arbre des cours.
	 * @param racine la racine de l'arbre.
	 */
	public void construireArbreCours(DefaultMutableTreeNode racine) {
		FolderDao dao = new FolderDaoImpl();
		Folder FolderRacine = dao.findDossierRacine();
		racine.setUserObject(FolderRacine);
		construireArbreCoursBis(racine, FolderRacine);
	}

	/**
	 * Fonction auxiliaire pour construire l'arbre des cours.
	 * @param racine la racine courante.
	 * @param Folder le Folder courant.
	 */
	private void construireArbreCoursBis(DefaultMutableTreeNode racine, Folder Folder) {
		ElementDao dao = new ElementDaoImpl();
		for (Element elem : dao.findAllOfDossier(Folder)) {
			DefaultMutableTreeNode rep;
			if (elem.isFile()) {
				rep = new DefaultMutableTreeNode(elem, false);
			} else {
				rep = new DefaultMutableTreeNode(elem);
				construireArbreCoursBis(rep, (Folder) elem);
			}
			racine.add(rep);
		}
	}

	/**
	 * Détruit un élément de l'arbre.
	 * @param node le noeud sélectionné
	 */
	public void suppressionElement(DefaultMutableTreeNode node) {
		ElementDao dao = new ElementDaoImpl();
		Element element = (Element) node.getUserObject();
		Folder Folder = element.getDossierConteneur();
		Folder.removeElement(element);
		dao.update(Folder);
		dao.delete(element.getId());
	}

	/**
	 * Fonction d'ajout de fichier.
	 * @param nom le nom du fichier à créer
	 * @param node le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutFichier(String nom, DefaultMutableTreeNode node) {
		Lesson file = new Lesson(nom);
		Folder Folder = (Folder) node.getUserObject();
		file.setDossierConteneur(Folder);
		Folder.addElements(file);
		new LessonDaoImpl().create(file);
		new FolderDaoImpl().update(Folder);
		return file;
	}

	/**
	 * Fonction d'ajout de Folder.
	 * @param nom le nom du Folder à créer
	 * @param node le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutFolder(String nom, DefaultMutableTreeNode node) {
		Folder folder = new Folder(nom);
		Folder Folder = (Folder) node.getUserObject();
		folder.setDossierConteneur(Folder);
		Folder.addElements(folder);
		FolderDao dao = new FolderDaoImpl();
		dao.create(folder);
		dao.update(Folder);
		return folder;
	}

	/**
	 * Déplace un fichier dans les Folders.
	 * @param node le noeud à déplacer
	 * @param parent la cible du noeud
	 * @param index l'index à laquel on veut insérer le noeud
	 */
	public void deplacerElement(DefaultMutableTreeNode node, DefaultMutableTreeNode cible, int index) {
		Element element = (Element) node.getUserObject();
		Folder FolderSource = element.getDossierConteneur();
		Folder FolderCible = (Folder) cible.getUserObject();

		// vérifie si on est plus hors index lors d'une réorganisation par ex
		if ((index == FolderCible.getElements().size()) && (FolderSource.getId() == FolderCible.getId())) {
			index = index - 1;
		}

		System.out.println(index);
		FolderSource.removeElement(element);
		FolderCible.addElementIndex(element, index);
		element.setDossierConteneur(FolderCible);

		ElementDao dao = new ElementDaoImpl();
		dao.update(element);
		dao.update(FolderSource);
		dao.update(FolderCible);
	}

	/**
	 * Fonction d'impression.
	 * @param node le noeud à imprimer
	 */
	public void impression(DefaultMutableTreeNode node) {
		//TODO activer l'impression
		System.out.println("Impression...");
	}

	/**
	 * Affichage des propriétés d'un noeud.
	 * @param node le noeud sélectionné
	 */
	public void afficherProprietes(DefaultMutableTreeNode node) {
		Element elem = (Element) node.getUserObject();
		String chemin = "/";
		Folder Folder = elem.getDossierConteneur();
		while (Folder != null) {
			chemin = "/" + Folder.getNom() + chemin;
			Folder = Folder.getDossierConteneur();
		}
		if (elem.isFile()) {
			new DialogProprietes(null, elem.getNom(), "Fichier", chemin, elem.getDateCreation(), elem.getDateModification()).setVisible(true);
		} else {
			new DialogProprietes(null, elem.getNom(), "Folder", chemin, elem.getDateCreation(), elem.getDateModification()).setVisible(true);
		}
	}

	/**
	 * Ouvre le fichier correspondant au noeud.
	 * @param node le noeud sélectionné
	 */
	public void ouvrir(DefaultMutableTreeNode node) {
		//TODO ouvrir le fichier
		System.out.println("ouverture du fichier "+node);
	}

	/**
	 * Construit l'arbre du plan de cours.
	 * @param racine la racine de l'arbre
	 */
	public void construireArbrePlan(DefaultMutableTreeNode racine) {
		
	}

	/* ---------------------------------------------------------*/
	/* --------------Gestion du panel d'acceuil-----------------*/
	/* ---------------------------------------------------------*/

	/**
	 * Accède à la liste des fichiers.
	 * @param classerParCreation si la liste doit être classé par ordre de création
	 * @return la liste des fichiers
	 */
	public List<Lesson> getListeFichiers(Boolean classerParCreation) {
		LessonDao dao = new LessonDaoImpl();
		return dao.findAll(classerParCreation);
	}
	
	
	
	/*-----------------------------------------------------------*/
	/*                     MainWindow function                   */
	/*-----------------------------------------------------------*/
	
	/**
	 * Add a work panel as a new tab.
	 * @param name name of the module.
	 */
	public static void addNewWorkPanel(String name){
		MainWindow.getInstance().addNewWorkPanel(name);
	}
	
	/**
	 * Display text in status bar
	 * @param text
	 */
	public void setLastAction(String text){
		 MainWindow.getInstance().getStatusPanel().setStatus(text);
	}
		
	/**
	 * Swho the preferences dialog
	 */
	public void showParamDialog(){
		ParametersDialog.getInstance().setVisible(true);
	}

	/**
	 * Print text on the view panel
	 * @param msg : Text to show in the window
	 */
	public void showText(String msg){
		JTextArea txtArea = ViewPanel.getViewPanel().getViewTextArea();
		txtArea.setText(txtArea.getText()+ "\n" + msg);
	}

	/*
	 * GUI Parameters getters and setters
	 */
	
	public String getWorkPlanPosition() {
		return workPlanPosition;
	}

	public void setWorkPlanPosition(String workPlanPosition) {
		this.workPlanPosition = workPlanPosition;
	}

	public boolean getWorkShowPlan() {
		return workShowPlan;
	}

	public void setWorkShowPlan(boolean workShowPlan) {
		this.workShowPlan = workShowPlan;
	}

	public void setWorkShowCourses(boolean workShowCourses) {
		this.workShowCourses = workShowCourses;
	}

	public boolean getWorkShowCourses() {
		return workShowCourses;
	}
	
	

}
