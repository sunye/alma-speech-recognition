package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.LessonDao;
import fr.alma.asr.entities.Lesson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Implementation dao de la classe Fichier.
 * @author Jérémy Braud
 */
public class LessonDaoImpl extends AbstractDaoImpl<Lesson> implements LessonDao {

	@Override
	public List<Lesson> findAll(Boolean classerParCreation) {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Fichier ORDER BY ";
		if (classerParCreation) {
			requete += "dateCreation DESC";
		} else {
			requete += "dateModification DESC";
		}
		List<Lesson> resultats = em.createQuery(requete).getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
