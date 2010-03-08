package fr.alma.asr.entities;

import java.io.Serializable;

/**
 * Super-classe abstraite des entités.
 * @author Cédric Krommenhoek
 */
public abstract class AbstractEntity implements Serializable {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;

	/**
	 * Accès à l'ID d'une entité.
	 * @return l'ID
	 */
	public abstract long getId();

}
