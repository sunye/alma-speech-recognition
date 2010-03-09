package fr.alma.asr.entities;

import javax.persistence.Entity;

/**
 * Classe des entités Matière.
 * @author Jérémy Braud
 */
@Entity
public class Subject extends Folder {

	/**
	 * Constructeur par défaut.
	 */
	public Subject() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom
	 */
    public Subject(String nom) {
		super(nom);
	}

}
