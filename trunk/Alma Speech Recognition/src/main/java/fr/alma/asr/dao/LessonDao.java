package fr.alma.asr.dao;

import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;
import java.util.List;

/**
 * Interface dao de la classe Fichier.
 * @author Jérémy Braud
 */
public interface LessonDao extends AbstractDao<Lesson> {

	/**
	 * Liste les fichiers par ordre de création ou de modifications.
	 * @param classerParCreation si le classement est par ordre de création
	 * @return la liste des fichiers
	 */
	List<Lesson> findAll(Boolean classerParCreation);

	/**
	 * Recherche l'ensemble des éléments contenu dans le module.
	 * @param dossier le dossier
	 * @return une liste d'éléments
	 */
	List<Lesson> findAllOfModule(Folder dossier, Boolean classerParCreation);

}
