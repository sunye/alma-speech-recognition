package fr.alma.asr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.alma.asr.dao.LessonDao;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;
import javax.persistence.Query;

/**
 * Implementation dao de la classe Fichier.
 * @author Jérémy Braud
 */
public class LessonDaoImpl extends AbstractDaoImpl<Lesson> implements LessonDao {

	@Override
	public void delete(Long id) {
		Lesson element = this.find(id);

		// Suppression de la référence dans le dossier conteneur
		Folder conteneur = element.getDossierConteneur();
		if (conteneur != null) {
			conteneur.removeElement(element);
			element.setDossierConteneur(null);
			this.update(element);
			new FolderDaoImpl().update(conteneur);
		}		

		// Suppression de l'élément
		super.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> findAll(Boolean classerParCreation) {
		EntityManager em = AbstractDaoImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Lesson ORDER BY ";
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
