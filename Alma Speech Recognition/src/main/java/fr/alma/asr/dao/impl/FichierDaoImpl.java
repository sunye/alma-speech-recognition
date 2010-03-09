package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.FichierDao;
import fr.alma.asr.entities.Fichier;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Implementation dao de la classe Fichier.
 * @author Jérémy Braud
 */
public class FichierDaoImpl extends AbstractDaoImpl<Fichier> implements FichierDao {

	@Override
	public List<Fichier> findAll(Boolean classerParCreation) {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Fichier ORDER BY ";
		if (classerParCreation) {
			requete += "dateCreation DESC";
		} else {
			requete += "dateModification DESC";
		}
		List<Fichier> resultats = em.createQuery(requete).getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
