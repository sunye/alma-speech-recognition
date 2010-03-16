package fr.alma.asr.gui;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultMutableTreeNode;

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
import fr.alma.asr.utils.FileExporter;

/**
 * Controleur de l'IHM.
 * 
 * @author Braud Jeremy
 */
public final class Controleur {

	/** L'instance du controleur. */
	private static Controleur instance;

	/*------------------------------*/
	private String workPlanPosition = "right";
	private boolean workShowPlan = true;
	private boolean workShowCourses = true;

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
	 * 
	 * @return l'instance du controleur
	 */
	public static Controleur getInstance() {
		if (instance == null) {
			instance = new Controleur();
			new HashMap<String, WorkPanel>();
		}
		return instance;
	}

	/**
	 * Méthode de connexion à H2.
	 */
	private void connexion() {
		String chemin = "./DataBase/db";
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url",
				"jdbc:h2:" + chemin);
		File fichier = new File(chemin + ".h2.db");
		if (!fichier.exists()) {
			Folder racine = new Folder("Cours");
			new FolderDaoImpl().create(racine);
		}
	}

	/**
	 * Méthode de déconnexion de H2.
	 */
	public void deconnexion() {
		AbstractDaoImpl.deconnexion();
	}

	/* --------------------------------------------------------- */
	/* ---------------Gestion du panel d'info------------------- */
	/* --------------------------------------------------------- */

	/** Le panel d'info. */
	private static StatusPanel panelInfo;

	/**
	 * Enregistrement du panel d'info.
	 * 
	 * @param info
	 *            le PanelInfo
	 */
	public static void setStatusPanel(StatusPanel info) {
		panelInfo = info;
	}

	public static void setInfo(String information) {
		if (panelInfo != null) {
			panelInfo.setStatus(information);
		}
	}

	/* --------------------------------------------------------- */
	/* -----------------Gestion de l'arbre---------------------- */
	/* --------------------------------------------------------- */

	/**
	 * Construit l'arbre des cours.
	 * 
	 * @param racine
	 *            la racine de l'arbre.
	 */
	public void construireArbreCours(DefaultMutableTreeNode racine) {
		FolderDao dao = new FolderDaoImpl();
		Folder dossierRacine = dao.findDossierRacine();
		racine.setUserObject(dossierRacine);
		construireArbreCoursBis(racine, dossierRacine);
	}

	/**
	 * Fonction auxiliaire pour construire l'arbre des cours.
	 * 
	 * @param racine
	 *            la racine courante.
	 * @param Folder
	 *            le Folder courant.
	 */
	private void construireArbreCoursBis(DefaultMutableTreeNode racine,
			Folder folder) {
		ElementDao dao = new ElementDaoImpl();
		for (Element elem : dao.findAllOfDossier(folder)) {
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
	 * 
	 * @param node
	 *            le noeud sélectionné
	 */
	public void suppressionElement(DefaultMutableTreeNode node) {
		ElementDao dao = new ElementDaoImpl();
		Element element = (Element) node.getUserObject();
		Folder folder = element.getDossierConteneur();
		folder.removeElement(element);
		dao.update(folder);
		dao.delete(element.getId());
	}

	/**
	 * Fonction d'ajout de fichier.
	 * 
	 * @param nom
	 *            le nom du fichier à créer
	 * @param node
	 *            le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutFichier(String nom, DefaultMutableTreeNode node) {
		Lesson file = new Lesson(nom);
		Folder folder = (Folder) node.getUserObject();
		file.setDossierConteneur(folder);
		folder.addElements(file);
		new LessonDaoImpl().create(file);
		new FolderDaoImpl().update(folder);
		return file;
	}

	/**
	 * Fonction d'ajout de Folder.
	 * 
	 * @param nom
	 *            le nom du Folder à créer
	 * @param node
	 *            le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutFolder(String nom, DefaultMutableTreeNode node,
			boolean isModule) {
		Folder folder = new Folder(nom);
		Folder conteneur = (Folder) node.getUserObject();
		folder.setDossierConteneur(conteneur);
		if (isModule) {
			folder.setModule();
		}
		conteneur.addElements(folder);
		FolderDao dao = new FolderDaoImpl();
		dao.create(folder);
		dao.update(conteneur);
		return folder;
	}

	/**
	 * Déplace un fichier dans les Folders.
	 * 
	 * @param node
	 *            le noeud à déplacer
	 * @param parent
	 *            la cible du noeud
	 * @param index
	 *            l'index à laquel on veut insérer le noeud
	 */
	public void deplacerElement(DefaultMutableTreeNode node,
			DefaultMutableTreeNode cible, int index) {
		Element element = (Element) node.getUserObject();
		Folder folderSource = element.getDossierConteneur();
		Folder folderCible = (Folder) cible.getUserObject();

		// vérifie si on est plus hors index lors d'une réorganisation par ex
		if ((index == folderCible.getElements().size())
				&& (folderSource.getId() == folderCible.getId())) {
			index = index - 1;
		}

		System.out.println(index);
		folderSource.removeElement(element);
		folderCible.addElementIndex(element, index);
		element.setDossierConteneur(folderCible);

		ElementDao dao = new ElementDaoImpl();
		dao.update(element);
		dao.update(folderSource);
		dao.update(folderCible);
	}

	/**
	 * Fonction d'impression.
	 * 
	 * @param node
	 *            le noeud à imprimer
	 */
	public void impression(DefaultMutableTreeNode node) {
		// TODO activer l'impression
		System.out.println("Impression...");
	}

	/**
	 * Affichage des propriétés d'un noeud.
	 * 
	 * @param node
	 *            le noeud sélectionné
	 */
	public void afficherProprietes(DefaultMutableTreeNode node) {
		Element elem = (Element) node.getUserObject();
		String chemin = "/";
		Folder folder = elem.getDossierConteneur();
		while (folder != null) {
			chemin = "/" + folder.getNom() + chemin;
			folder = folder.getDossierConteneur();
		}
		String type = "";
		if (elem.isFile()) {
			type = "Fichier";
		} else if (((Folder) elem).isModule()) {
			type = "Module";
		} else {
			type = "Dossier";
		}
		new DialogProprietes(null, elem.getNom(), type, chemin, elem
				.getDateCreation(), elem.getDateModification())
		.setVisible(true);
	}

	/**
	 * Ouvre le fichier correspondant au noeud.
	 * 
	 * @param node
	 *            le noeud sélectionné
	 */
	public void ouvrir(DefaultMutableTreeNode node) {
		// TODO ouvrir le fichier
		System.out.println("ouverture du fichier " + node);
	}

	/** La raine du JTree plan. */
	private DefaultMutableTreeNode racinePlan;

	/**
	 * Définit la racine du JTree plan.
	 * 
	 * @param racine
	 *            la racine
	 */
	public void setRacineArbrePlan(DefaultMutableTreeNode racine) {
		this.racinePlan = racine;
	}

	/**
	 * Mise à jour du JTree plan.
	 * 
	 * @param texte
	 *            le texte à parser
	 */
	public void updateTreePlan(String texte) {
		construireArbrePlan(this.racinePlan);
	}

	/**
	 * Construit l'arbre du plan de cours.
	 * 
	 * @param racine
	 *            la racine de l'arbre
	 */
	public void construireArbrePlan(DefaultMutableTreeNode racine) {
		// TODO parser le texte et ajouter les différents noeuds
		System.out.println("Update du jtree!");
	}

	/**
	 * 
	 * Method which provides a way create PDF documents
	 * 
	 * @param outputFilePath
	 *            The filePath of the output pdf
	 * @param jTextPane
	 *            The component to printout
	 */
	public static void printOutPdf(String outputFilePath, JTextPane jTextPane) {
		FileExporter.createPdf(false, jTextPane, outputFilePath);
	}

	/* --------------------------------------------------------- */
	/* --------------Gestion du panel d'acceuil----------------- */
	/* --------------------------------------------------------- */

	/**
	 * Accède à la liste des fichiers.
	 * 
	 * @param classerParCreation
	 *            si la liste doit être classé par ordre de création
	 * @return la liste des fichiers
	 */
	public List<Lesson> getListeFichiers(Boolean classerParCreation) {
		LessonDao dao = new LessonDaoImpl();
		return dao.findAll(classerParCreation);
	}

	/*-----------------------------------------------------------*/
	/* MainWindow function */
	/*-----------------------------------------------------------*/

	/**
	 * Add a work panel as a new tab.
	 * 
	 * @param name
	 *            name of the module.
	 */
	public static void addNewWorkPanel(String name) {
		MainWindow.getInstance().addNewWorkPanel(name);
	}

	/**
	 * Display text in status bar
	 * 
	 * @param text
	 */
	public void setLastAction(String text) {
		MainWindow.getInstance().getStatusPanel().setStatus(text);
	}

	/**
	 * Swho the preferences dialog
	 */
	public void showParamDialog() {
		ParametersDialog.getInstance().setVisible(true);
	}

	/**
	 * Print text on the view panel
	 * 
	 * @param msg
	 *            : Text to show in the window
	 */
	public void showText(String msg) {
		JTextArea txtArea = ViewPanel.getViewPanel().getViewTextArea();
		txtArea.setText(txtArea.getText() + "\n" + msg);
	}

	/**
	 * Start Viavoic engine
	 */
	public void startEngine() {
		// TODO appeler fonction moteur
	}

	/**
	 * Stop viavoice engine
	 */
	public void stopEngine() {
		// TODO appeler fonction moteur
	}

	/**
	 * Show the parameters dialog for dico
	 */
	public void showDicoParam() {
		// TODO appeler fonction moteur
	}

	/**
	 * Show the parameter dialog for egine options
	 */
	public void showOptParam() {
		// TODO appeler fonction moteur
	}
	
	
	/**
	 * Function that print error msg 
	 * (Here to sysout)
	 * @param msg
	 */
	public static void printLog(String msg){
		System.out.println(msg);
	}
	
	/**
	 * Function that print exception 
	 * (Here to sysout)
	 * @param msg
	 */
	public static void printLog(Exception e){
		System.out.println(e.getMessage());
	}
	

	/**
	 * Set the current panel displayed on screen modified/unmodified. If true, a
	 * icon show that the panel has modification which haven't been saved.
	 * 
	 * @param modified
	 *            true : display icon/ false hide icon
	 */
	public void setModified(boolean modified) {
		MainWindow.getInstance().getTabbedPaneHomeWork().setCurrentModified(
				modified);
	}

	/**
	 * After a "save all" action, all the tabbed pane are set to unmodified.
	 */
	public void setAllUnModified() {
		MainWindow.getInstance().getTabbedPaneHomeWork().setAllUnModified();
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
