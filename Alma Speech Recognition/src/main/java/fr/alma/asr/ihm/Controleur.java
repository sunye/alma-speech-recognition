package fr.alma.asr.ihm;

import fr.alma.asr.dao.DossierDao;
import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.DossierDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.dao.impl.FichierDaoImpl;
import fr.alma.asr.entities.Dossier;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Fichier;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controleur de l'IHM.
 * @author Braud Jeremy
 */
public final class Controleur {

	/** L'instance du controleur. */
	private static Controleur instance = new Controleur();
	/** L'instance du splash screen. */
	private static SplashScreen splash;

	/**
	 * Constructeur privé.
	 */
	private Controleur() {
		splash = new SplashScreen(null, false);
		splash.setVisible(true);
		connexion();
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
		DossierDao dao = new DossierDaoImpl();
		dao.create(folder);
		dao.update(dossier);
		return folder;
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
		//TODO Afficher les propriétés d'un élément
		System.out.println("Propriétés...");
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

}