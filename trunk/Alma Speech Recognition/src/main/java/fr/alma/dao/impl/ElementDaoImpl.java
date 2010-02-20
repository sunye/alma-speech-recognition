package fr.alma.dao.impl;

import fr.alma.dao.ElementDao;
import fr.alma.entities.Dossier;
import fr.alma.entities.Element;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ElementDaoImpl extends AbstractDaoImpl<Element> implements ElementDao {

	@Override
	public void delete (Long id) {
		Element element = this.find(id);
		// Suppression des sous-elements d'un projet
		if (!element.isFile()) {
			Dossier projet = (Dossier) element;
			for (Element contenu : projet.getElements()) {
				this.delete(contenu.getId());
			}
		}

		// Suppression de la reference dans le projet conteneur
		Dossier conteneur = element.getDossierConteneur();
		conteneur.removeElements(element);
		element.setDossierConteneur(null);
		this.update(element);
		this.update(conteneur);

		// Suppression de l'element
		super.delete(id);
	}

	@Override
	public List<Element> findAllOfDossier(Dossier projet) {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Element WHERE projetconteneur_id = :idProjet";
		Query query = em.createQuery(requete);
		query.setParameter("idProjet", projet.getId());
		List<Element> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
