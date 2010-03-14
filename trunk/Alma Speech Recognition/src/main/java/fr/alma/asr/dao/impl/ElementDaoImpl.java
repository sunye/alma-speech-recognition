package fr.alma.asr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Folder;

/**
 * Implementation dao de la classe Element.
 * @author Jérémy Braud
 */
public class ElementDaoImpl extends AbstractDaoImpl<Element> implements ElementDao {
	
	@Override
	public void delete(Long id) {
		Element element = this.find(id);
		
		// Suppression des sous-éléments d'un dossier
		if (!element.isFile()) {
			Folder projet = (Folder) element;
			for (Element contenu : projet.getElements()) {
				this.delete(contenu.getId());
			}
		}

		// Suppression de la référence dans le dossier conteneur
		Folder conteneur = element.getDossierConteneur();
		if (conteneur != null) {
			conteneur.removeElement(element);
			element.setDossierConteneur(null);
			this.update(element);
			this.update(conteneur);
		}		

		// Suppression de l'élément
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
