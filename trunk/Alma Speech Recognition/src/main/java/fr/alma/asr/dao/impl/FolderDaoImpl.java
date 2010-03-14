package fr.alma.asr.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.alma.asr.dao.FolderDao;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Folder;


/**
 * Implementation dao de la classe Dossier.
 * @author Jérémy Braud
 */
public class FolderDaoImpl extends AbstractDaoImpl<Folder> implements FolderDao {

	@Override
	public void delete(Long id) {
		Folder dossier = this.find(id);
		
		// Suppression des sous-éléments d'un dossier
		for (Element contenu : dossier.getElements()) {
			this.delete(contenu.getId());
		}

		// Suppression de la référence dans le dossier conteneur
		Folder conteneur = dossier.getDossierConteneur();
		if (conteneur != null) {
			conteneur.removeElement(dossier);
			dossier.setDossierConteneur(null);
			this.update(dossier);
			this.update(conteneur);
		}		

		// Suppression de l'élément
		super.delete(id);
	}
	
	@Override
	public Folder findDossierRacine() {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Folder WHERE dossierconteneur_id = NULL";
		Folder resultat = (Folder) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

}
