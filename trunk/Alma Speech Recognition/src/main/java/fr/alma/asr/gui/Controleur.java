package fr.alma.asr.gui;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import fr.alma.asr.utils.FileHandler;
import fr.alma.asr.utils.RecognitionEngine;
import fr.alma.asr.utils.RecognitionEngineStub;

/**
 * Contrôleur de l'IHM.
 * @author Jérémy Braud
 */
public final class Controleur implements Observer {

	/** L'instance du controleur. */
	private static Controleur instance;

	private String workPlanPosition = "right";
	private boolean workShowPlan = true;
	private boolean workShowCourses = true;

	private HashMap<WorkPanel, Lesson> workPanelMap;

	/** Le moteur de reconnaissance vocale. */	
	private RecognitionEngine engine;
	
	/**
	 * Constructeur privé.
	 */
	private Controleur() {
		
		workPanelMap = new HashMap<WorkPanel, Lesson>();
		connexion();
		
		try {
			FileHandler handler = FileHandler.getInstance();
			handler.setFile("log.txt");
			Logger.getLogger("fr.alma.asr").addHandler(handler);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		this.engine = new RecognitionEngineStub();
		this.engine.addObserver(this);
		this.engine.start();
		
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

	/* --------------------------------------------------------- */
	/* -----------------Gestion de l'arbre---------------------- */
	/* --------------------------------------------------------- */

	/**
	 * Construit l'arbre des cours.
	 * @param racine la racine de l'arbre.
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
			index --;
		}
		//TODO sysout a supprimer
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
		printLog(Level.INFO, "Impression...");
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
		Lesson lesson = (Lesson) node.getUserObject();
		addNewWorkPanel(lesson);
		printLog(Level.INFO, "ouverture du fichier " + node);
		
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
		printLog(Level.INFO, "Update du jtree!");
	}

	/**
	 * Enregistre les deux textes d'un cours donné.
	 * @param cours le cours
	 * @param prof le texte dicté
	 * @param eleve le texte saisi
	 */
	public void enregistrerCours(WorkPanel onglet, String prof, String eleve) {

		Lesson cours = this.workPanelMap.get(onglet);
		cours.setDataProf(prof);
		cours.setDataEleve(eleve);
		new LessonDaoImpl().update(cours);
	}

	/**
	 * Method which provides a way create PDF  documents.
	 * @param outputFilePath The filePath of the output pdf
	 * @param jTextPane The component to printout
	 */
	public static void printOutPdf(String outputFilePath, JTextPane jTextPane) {
		FileExporter.createPdf(false, jTextPane, outputFilePath);
	}

	/* --------------------------------------------------------- */
	/* --------------Gestion du panel d'accueil----------------- */
	/* --------------------------------------------------------- */

	/**
	 * Accède à la liste des fichiers.
	 * @param classerParCreation si la liste doit être classé par ordre de création
	 * @return la liste des fichiers
	 */
	public List<Lesson> getListeFichiers(Boolean classerParCreation) {
		LessonDao dao = new LessonDaoImpl();
		return dao.findAll(classerParCreation);
	}

	/**
	 * Accède à la liste des modules.
	 * @return une liste de module
	 */
	public List<Folder> getListeModules() {
		FolderDao dao = new FolderDaoImpl();
		return dao.findModules();
	}

	/**
	 * Accède à la liste des fichiers d'un module donné.
	 * @param folder le module
	 * @param classerParCreation indique si les fichiers sont classés par ordre de création ou de modifications
	 * @return la liste des cours du module
	 */
	public List<Lesson> getListeFichiers(Folder folder, Boolean classerParCreation) {
		LessonDao dao = new LessonDaoImpl();
		return dao.findAllOfModule(folder, classerParCreation);
	}

	/*-----------------------------------------------------------*/
	/* MainWindow function */
	/*-----------------------------------------------------------*/

	/**
	 * Add a work panel as a new tab in the Controler and MainWindows
	 * 
	 * @param String
	 *            name of the module.
	 */
	public void addNewWorkPanel(Lesson lesson) {

		boolean isOpened = false;

		for (WorkPanel work : workPanelMap.keySet()) {
			if (workPanelMap.get(work) == lesson) {
				isOpened = true;
			}
		}
		if (!isOpened) {
			WorkPanel workPanel = new WorkPanel(MainWindow.getInstance());
			workPanelMap.put(workPanel, lesson);
			workPanel.setLesson(lesson);
			MainWindow.getInstance()
					.addNewWorkPanel(workPanel, lesson.getNom());
		}
	}
	
	
	/**
	 * close a work panel in MainWindow and the controler
	 * 
	 * @param String
	 *            name of the module.
	 */
	public void closeWorkPanel(WorkPanel workPanel) {
		workPanelMap.keySet().remove(workPanel);
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
		JTextPane txtArea = ViewPanel.getViewPanel().getTextArea();
		txtArea.setText(txtArea.getText() + "\n" + msg);
	}

	/**
	 * Début de la reconnaissance vocale.
	 */
	public void openMic() {
		this.engine.openMic();
	}

	/**
	 * Fin de la reconnaissance vocale.
	 */
	public void closeMic() {
		this.engine.closeMic();
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
	public static void printLog(Level level, String msg){
		Logger.getLogger("fr.alma.asr").log(level, msg);
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

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass().getSuperclass().getSimpleName().equals("RecognitionEngine")) {
			String msg = (String) arg;
			//TODO insérer ce message dans le panel lié à la reconnaissance vocale
			System.out.println(msg);
		}
		
	}

}
