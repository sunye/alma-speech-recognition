package fr.alma.asr.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe des entités Cours.
 */
@Entity
public class Cours extends AbstractEntity {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;
	
	/** Id. */
	@Id
	@GeneratedValue
	private long id;	
	/** Nom. */
	@Basic
	private String nom;

	/**
	 * Constructeur par defaut.
	 */
	public Cours() {
	}

	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Cours construit
	 */
	public Cours(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de l'attribut nom.
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter de l'attribut nom.
	 * @param nom le nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public long getId() {
		return this.id;
	}
	
}
