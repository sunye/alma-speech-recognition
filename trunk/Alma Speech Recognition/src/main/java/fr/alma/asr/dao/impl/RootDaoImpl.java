package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.RootDao;
import fr.alma.asr.entities.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Implementation dao de la classe Root.
 * @author Jérémy Braud
 */
public class RootDaoImpl extends AbstractDaoImpl<Root> implements RootDao {

	@Override
	public Root findRoot() {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Root";
		Root resultat = (Root) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

}
