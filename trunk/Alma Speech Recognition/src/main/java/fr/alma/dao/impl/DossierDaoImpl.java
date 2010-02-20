package fr.alma.dao.impl;

import fr.alma.dao.DossierDao;
import fr.alma.entities.Dossier;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DossierDaoImpl extends AbstractDaoImpl<Dossier> implements DossierDao {

	@Override
	public Dossier findDossierRacine() {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Projet WHERE projetconteneur_id = NULL";
		Dossier resultat = (Dossier) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

}
