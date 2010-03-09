package fr.alma.asr.dao;

import fr.alma.asr.entities.Root;

/**
 * Interface dao de la classe Root.
 * @author Jérémy Braud
 */
public interface RootDao extends AbstractDao<Root> {

	/**
	 * Accède au root.
	 * @return l'instance de root
	 */
	Root findRoot();

}
