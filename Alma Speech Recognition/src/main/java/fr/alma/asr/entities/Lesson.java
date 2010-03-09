package fr.alma.asr.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Classe Fichier.
 * @author Jérémy Braud
 */
@Entity
public class Lesson extends Element {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;

	/** Le texte de l'élève. */
	@Basic
	private String dataEleve;

	/** Le texte du prof. */
	@Basic
	private String dataProf;

	/**
	 * Constructeur par défaut.
	 */
	public Lesson() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom du fichier.
	 */
	public Lesson(String nom) {
		super(nom, Boolean.TRUE);
	}

	/**
	 * Accède aux données de l'élève.
	 * @return les données.
	 */
	public String getDataEleve() {
		return dataEleve;
	}

	/**
	 * Change les données de l'élève.
	 * @param texte les données à enregistrer.
	 */
	public void setDataEleve(String texte) {
		this.dataEleve = texte;
		this.setChanged();
	}

	/**
	 * Accède aux données du professeur.
	 * @return les données.
	 */
	public String getDataProf() {
		return dataProf;
	}

	/**
	 * Change les données du professeur.
	 * @param texte les données à enregistrer.
	 */
	public void setDataProf(String texte) {
		this.dataProf = texte;
		this.setChanged();
	}

}
