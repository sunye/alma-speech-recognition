package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Element;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Implementation dao de la classe Element.
 * @author Jérémy Braud
 */
public class ElementDaoImpl extends AbstractDaoImpl<Element> implements ElementDao {

	@Override
	public void delete(Long id) {
		Element element = this.find(id);
		// Suppression des sous-elements d'un projet
		if (!element.isFile()) {
			Folder projet = (Folder) element;
			for (Element contenu : projet.getElements()) {
				this.delete(contenu.getId());
			}
		}

		// Suppression de la reference dans le projet conteneur
		Folder conteneur = element.getDossierConteneur();
		conteneur.removeElement(element);
		element.setDossierConteneur(null);
		this.update(element);
		this.update(conteneur);

		// Suppression de l'element
		super.delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Element> findAllOfDossier(Folder projet) {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Element WHERE dossierconteneur_id = :idProjet";
		Query query = em.createQuery(requete);
		query.setParameter("idProjet", projet.getId());
		List<Element> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
