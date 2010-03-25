package fr.alma.asr.dao;

import java.util.List;

import fr.alma.asr.entities.Element;
import fr.alma.asr.entities.Folder;

/**
 * Interface dao de la classe Element.
 * @author Jérémy Braud
 */
public interface ElementDao extends AbstractDao<Element> {

	/**
	 * Recherche des éléments contenu dans le dossier.
	 * @param dossier le dossier
	 * @return une liste d'éléments
	 */
	List<Element> findAllOfDossier(Folder dossier);

}
