package fr.alma.ihm;

import fr.alma.dao.AbstractDao;
import fr.alma.dao.DossierDao;
import fr.alma.dao.ElementDao;
import fr.alma.dao.impl.AbstractDaoImpl;
import fr.alma.dao.impl.DossierDaoImpl;
import fr.alma.dao.impl.ElementDaoImpl;
import fr.alma.dao.impl.FichierDaoImpl;
import fr.alma.entities.Dossier;
import fr.alma.entities.Element;
import fr.alma.entities.Fichier;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controleur de l'IHM.
 * @author Braud Jeremy
 */
public class Controleur {

	/** L'instance du controleur. */
	private static Controleur instance = new Controleur();

	/**
	 * Constructeur privé.
	 */
	private Controleur() {
		connexion();
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
	void construireArbreCours(DefaultMutableTreeNode racine) {
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
	void construireArbreCoursBis(DefaultMutableTreeNode racine, Dossier dossier) {
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
	void suppressionElement(DefaultMutableTreeNode node) {

	}

	/**
	 * Construit l'arbre du plan de cours.
	 * @param racine la racine de l'arbre
	 */
	void construireArbrePlan(DefaultMutableTreeNode racine) {
		
	}

}
