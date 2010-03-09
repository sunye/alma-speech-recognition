package fr.alma.asr.gui;

import fr.alma.asr.dao.DossierDao;
import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.FichierDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.DossierDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.dao.impl.FichierDaoImpl;
import fr.alma.asr.entities.Dossier;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Fichier;

import java.io.File;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controleur de l'IHM.
 * @author Braud Jeremy
 */
public final class Controleur {

	/** L'instance du controleur. */
	private static Controleur instance = new Controleur();

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
			Dossier dossierRacine = new Dossier("Cours");
			new DossierDaoImpl().create(dossierRacine);
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
		DossierDao dao = new DossierDaoImpl();
		Dossier dossierRacine = dao.findDossierRacine();
		racine.setUserObject(dossierRacine);
		construireArbreCoursBis(racine, dossierRacine);
	}

	/**
	 * Fonction auxiliaire pour construire l'arbre des cours.
	 * @param racine la racine courante.
	 * @param dossier le dossier courant.
	 */
	private void construireArbreCoursBis(DefaultMutableTreeNode racine, Dossier dossier) {
		ElementDao dao = new ElementDaoImpl();
		for (Element elem : dao.findAllOfDossier(dossier)) {
			DefaultMutableTreeNode rep;
			if (elem.isFile()) {
				rep = new DefaultMutableTreeNode(elem, false);
			} else {
				rep = new DefaultMutableTreeNode(elem);
				construireArbreCoursBis(rep, (Dossier) elem);
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
		Dossier dossier = element.getDossierConteneur();
		dossier.removeElements(element);
		dao.update(dossier);
		dao.delete(element.getId());
	}

	/**
	 * Fonction d'ajout de fichier.
	 * @param nom le nom du fichier à créer
	 * @param node le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutFichier(String nom, DefaultMutableTreeNode node) {
		Fichier file = new Fichier(nom);
		Dossier dossier = (Dossier) node.getUserObject();
		file.setDossierConteneur(dossier);
		dossier.addElements(file);
		new FichierDaoImpl().create(file);
		new DossierDaoImpl().update(dossier);
		return file;
	}

	/**
	 * Fonction d'ajout de dossier.
	 * @param nom le nom du dossier à créer
	 * @param node le noeud parent
	 * @return l'objet créé
	 */
	public Object ajoutDossier(String nom, DefaultMutableTreeNode node) {
		Dossier folder = new Dossier(nom);
		Dossier dossier = (Dossier) node.getUserObject();
		folder.setDossierConteneur(dossier);
		dossier.addElements(folder);
		DossierDao dao = new DossierDaoImpl();
		dao.create(folder);
		dao.update(dossier);
		return folder;
	}

	/**
	 * Déplace un fichier dans les dossiers.
	 * @param node le noeud à déplacer
	 * @param parent la cible du noeud
	 * @param index l'index à laquel on veut insérer le noeud
	 */
	public void deplacerElement(DefaultMutableTreeNode node, DefaultMutableTreeNode cible, int index) {
		Element element = (Element) node.getUserObject();
		Dossier dossierSource = element.getDossierConteneur();
		Dossier dossierCible = (Dossier) cible.getUserObject();

		// vérifie si on est plus hors index lors d'une réorganisation par ex
		if ((index == dossierCible.getElements().size()) && (dossierSource.getId() == dossierCible.getId())) {
			index = index - 1;
		}

		System.out.println(index);
		dossierSource.removeElements(element);
		dossierCible.addElementIndex(element, index);
		element.setDossierConteneur(dossierCible);

		ElementDao dao = new ElementDaoImpl();
		dao.update(element);
		dao.update(dossierSource);
		dao.update(dossierCible);
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
		Dossier dossier = elem.getDossierConteneur();
		while (dossier != null) {
			chemin = "/" + dossier.getNom() + chemin;
			dossier = dossier.getDossierConteneur();
		}
		if (elem.isFile()) {
			new DialogProprietes(null, elem.getNom(), "Fichier", chemin, elem.getDateCreation(), elem.getDateModification()).setVisible(true);
		} else {
			new DialogProprietes(null, elem.getNom(), "Dossier", chemin, elem.getDateCreation(), elem.getDateModification()).setVisible(true);
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
	List<Fichier> getListeFichiers(Boolean classerParCreation) {
		FichierDao dao = new FichierDaoImpl();
		return dao.findAll(classerParCreation);
	}

}
