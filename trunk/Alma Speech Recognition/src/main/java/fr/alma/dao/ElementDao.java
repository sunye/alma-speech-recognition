package fr.alma.dao;

import fr.alma.entities.Dossier;
import fr.alma.entities.Element;
import java.util.List;

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
	List<Element> findAllOfDossier(Dossier dossier);

}
