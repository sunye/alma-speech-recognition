package fr.alma.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Classe Fichier.
 */
@Entity
public class Fichier extends Element {

	/** Le texte de l'élève. */
	@Basic
	private String DataEleve;

	/** Le texte du prof. */
	@Basic
	private String DataProf;

	/**
	 * Constructeur par défaut.
	 */
	public Fichier() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom du fichier.
	 */
	public Fichier(String nom) {
		super(nom, Boolean.TRUE);
	}

	/**
	 * Accède aux données de l'élève.
	 * @return les données.
	 */
	public String getDataEleve() {
		return DataEleve;
	}

	/**
	 * Change les données de l'élève.
	 * @param DataEleve les données à enregistrer.
	 */
	public void setDataEleve(String DataEleve) {
		this.DataEleve = DataEleve;
	}

	/**
	 * Accède aux données du professeur.
	 * @return les données.
	 */
	public String getDataProf() {
		return DataProf;
	}

	/**
	 * Change les données du professeur.
	 * @param DataProf les données à enregistrer.
	 */
	public void setDataProf(String DataProf) {
		this.DataProf = DataProf;
	}

}
