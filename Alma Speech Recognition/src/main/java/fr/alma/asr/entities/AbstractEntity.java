package fr.alma.asr.entities;

import java.io.Serializable;

/**
 * Classe abstraite.
 * @author Cédric Krommenhoek
 */
public abstract class AbstractEntity implements Serializable {

	/**
	 * Accès à l'id d'une entité.
	 * @return l'id
	 */
	public abstract long getId();

}
