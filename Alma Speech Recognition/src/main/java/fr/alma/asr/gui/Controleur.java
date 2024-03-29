package fr.alma.asr.gui;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;

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

	private final HashMap<WorkPanel, Lesson> workPanelMap;

	/** Le moteur de reconnaissance vocale. */	
	private final RecognitionEngine engine;
	
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
//		this.engine = SpeechrootEngine.getInstance();
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
	/* -----------------Gestion du home panel------------------- */
	/* --------------------------------------------------------- */

	/** Le home panel. */
	private HomePanel homePanel;

	/**
	 * Définit le home panel.
	 * @param panel le home panel
	 */
	void setHomePanel(HomePanel panel) {
		this.homePanel = panel;
	}

	/* --------------------------------------------------------- */
	/* -----------------Gestion de l'arbre---------------------- */
	/* --------------------------------------------------------- */

	/**
	 * Affiche un message d'erreur de chargement et propose de supprimer la bdd.
	 */
	public void erreurChargementArbre() {
		Controleur.printLog(Level.SEVERE, "Chargement de l'arbre de cours échoué");
		JOptionPane.showMessageDialog(null, "La base de donnée est corrompue,\nelle va être réinitialisée", 
				"Erreur Base de donnée", JOptionPane.ERROR_MESSAGE);
		deconnexion();
		File fichier = new File("./DataBase/db.h2.db");
		fichier.delete();
		connexion();
	}

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
		this.homePanel.update();
		setLastAction("Elément supprimé");
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
		folder.addElement(file);
		new LessonDaoImpl().create(file);
		new FolderDaoImpl().update(folder);
		this.homePanel.update();
		setLastAction("Fichier ajouté");
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
			boolean isModule, boolean ajoutDosiersCourants) {
		Folder folder = new Folder(nom);
		Folder conteneur = (Folder) node.getUserObject();
		folder.setDossierConteneur(conteneur);
		if (isModule) {
			folder.setModule();
		}
		conteneur.addElement(folder);
		FolderDao dao = new FolderDaoImpl();
		dao.create(folder);
		dao.update(conteneur);
		DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(folder);
		node.add(folderNode);
		if (isModule) {
			if (ajoutDosiersCourants) {
				Folder cm = new Folder("CM");
				Folder td = new Folder("TD");
				Folder tp = new Folder("TP");
				cm.setDossierConteneur(folder);
				td.setDossierConteneur(folder);
				tp.setDossierConteneur(folder);
				folder.addElement(cm);
				folder.addElement(td);
				folder.addElement(tp);
				dao.create(cm);
				dao.create(td);
				dao.create(tp);
				dao.update(folder);
				folderNode.add(new DefaultMutableTreeNode(cm));
				folderNode.add(new DefaultMutableTreeNode(td));
				folderNode.add(new DefaultMutableTreeNode(tp));
			}
			setLastAction("Module ajouté");
		} else {
			setLastAction("Dossier ajouté");
		}
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

		folderSource.removeElement(element);
		folderCible.addElementIndex(element, index);
		element.setDossierConteneur(folderCible);

		ElementDao dao = new ElementDaoImpl();
		dao.update(element);
		dao.update(folderSource);
		dao.update(folderCible);
		this.homePanel.update();
		setLastAction("Element déplacé");
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
		Lesson lesson = (Lesson) node.getUserObject();
		addNewWorkPanel(lesson);
	}

	/**
	 * Permet de renommer un noeud de l'arbre
	 * @param node le noeud
	 * @param nouveauNom le nouveau nom du noeud
	 */
	public void renommerElement(DefaultMutableTreeNode node, String nouveauNom) {
		Element element = (Element) node.getUserObject();
		element.setNom(nouveauNom);
		new ElementDaoImpl().update(element);
		this.homePanel.update();
		setLastAction("Elément renommé.");
	}

	/* --------------------------------------------------------- */
	/* -----------------Gestion du plan------------------------- */
	/* --------------------------------------------------------- */

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
		
		//texte="<html>   <head>    <title>    </title> <span> ssdsd </span>    </head>    <body></body></html>";
		
		System.out.println("TEST--------  "+texte);
		System.out.println("--------TEST  ");

		Parser parser ;
		TagNameFilter filter;
		
		try {
	           parser = new Parser ();
	           filter = new TagNameFilter ("SPAN");             

               parser.setInputHTML(texte);
               	
               NodeList list = parser.parse (filter);
               
               
			this.racinePlan.removeAllChildren();
			
			for (int i = 0; i < list.size(); i++) {

				NodeList list2 = list.elementAt(i).getChildren();
				
				for (int j = 0; j < list2.size(); j++) {
					if (list2.elementAt(j) instanceof TextNode) {
						String nodeName = ((TextNode)list2.elementAt(j)).getText();
						System.out.println(nodeName);
						this.racinePlan.add(new DefaultMutableTreeNode(nodeName));
					}
				}
			}

		MainWindow.getInstance().updateTree();
		} catch (Exception e) {
			printLog(Level.SEVERE, e.getMessage(	));

		}
		
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

	/**
	 * Method which provides a way create RTF  documents.
	 * @param outputFilePath The filePath of the output rtf
	 * @param jTextPane The component to printout
	 */
	public static void printOutRtf(String outputFilePath, JTextPane jTextPane) {
		FileExporter.createRtf(jTextPane, outputFilePath);
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
		List<Lesson> listeCours = getListeFichiersBis(folder);
		if (classerParCreation) {
			Collections.sort(listeCours, new Comparator<Lesson>() {
				@Override
				public int compare(Lesson o1, Lesson o2) {
					return o2.getDateCreation().compareTo(o1.getDateCreation());
				}
			});
		} else {
			Collections.sort(listeCours, new Comparator<Lesson>() {
				@Override
				public int compare(Lesson o1, Lesson o2) {
					return o2.getDateModification().compareTo(o1.getDateModification());
				}
			});
		}
		return listeCours;
	}

	/**
	 * Accède à la liste des fichiers d'un dossier donné.
	 * @param folder le module
	 * @return la liste des cours du dossier
	 */
	private List<Lesson> getListeFichiersBis(Folder folder) {
		List<Lesson> listeCours = new LinkedList<Lesson>();
		for (Element element : folder.getElements()) {
			if (element.isFile()) {
				listeCours.add((Lesson) element);
			} else {
				listeCours.addAll(getListeFichiersBis((Folder) element));
			}
		}
		return listeCours;
	}

	public void ouvrirFichier(Lesson cours) {
		addNewWorkPanel(cours);
	}
	
	public String getModule(Lesson cours) {
		Folder dossier = cours.getDossierConteneur();
		while (!dossier.isModule()) {
			dossier = dossier.getDossierConteneur();
		}
		return dossier.getNom();
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
	 * enable or disable cut and copy menu item
	 * @param activated
	 */
	public void activateCopyCut(boolean activated){
		MainWindow.getInstance().activateCopyCut(activated);
	}
	
	/**
	 * enable or disable paste menu item
	 * @param activated 
	 */
	public void activatePast(boolean activated){
		MainWindow.getInstance().activatePast(activated);
	}
	
	/**
	 * close a work panel in MainWindow and the controler
	 * @param String name of the module.
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
	 *            Print the last msg in the top of the textpane
	 */
	public void showText(String msg) {
		MainWindow.getInstance().activateSave(true);
		MainWindow.getInstance().setTextViewTextPane(msg);
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
	 * Show the parameters dialog for dico.
	 */
	public void showDicoParam() {
		this.engine.dictionary();
	}

	/**
	 * Show the parameter dialog for egine options.
	 */
	public void showOptParam() {
		this.engine.voiceModel();
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
	public void setCurrentModified(boolean modified) {
		MainWindow.getInstance().activateSave(modified);
		MainWindow.getInstance().getTabbedPaneHomeWork().setCurrentModified(modified);
	}

	/**
	 * After a "save all" action, all the tabbed pane are set to unmodified.
	 */
	public void setAllUnModified() {
		MainWindow.getInstance().activateSave(false);		
		MainWindow.getInstance().getTabbedPaneHomeWork().setAllUnModified();
	}
	
	/**
	 * Enable or disable rec button
	 * @param enabled
	 */
	public void setMicEnable(Boolean enabled){
		MainWindow.getInstance().activateEditWork(enabled);
		
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
			showText(msg);
		}
		
	}
	
	public void stopEngine(){
		this.engine.stop();
	}
	
	public void setOnRec(boolean onRec){
		MainWindow.getInstance().setOnRec(onRec);
		if(onRec){			
			openMic();
			setLastAction("Reconnaissance vocale démarrée.");
		}
		else{
			closeMic();
			setLastAction("Reconnaissance vocale stoppée.");			
		}
			
	}

}
