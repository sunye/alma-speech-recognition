package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.FolderDao;
import fr.alma.asr.entities.Folder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


/**
 * Implementation dao de la classe Dossier.
 * @author Jérémy Braud
 */
public class FolderDaoImpl extends AbstractDaoImpl<Folder> implements FolderDao {

	@Override
	public Folder findDossierRacine() {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Dossier WHERE dossierconteneur_id = NULL";
		Folder resultat = (Folder) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

}
