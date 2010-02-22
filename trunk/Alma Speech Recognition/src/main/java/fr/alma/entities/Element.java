package fr.alma.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * Classe Element.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element extends AbstractEntity {

	/** Id. */
	@Id
	@GeneratedValue
	private long id;
	/** Nom. */
	@Basic
	private String nom;
	/** Le type d'élément. */
	@Basic
	private Boolean fichier;
	/** Dossier conteneur. */
	@ManyToOne
	protected Dossier dossierConteneur;

	/**
	 * Constructeur par defaut.
	 */
	public Element() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom de l'élément
	 */
	public Element(String nom, Boolean estFichier) {
		this.nom = nom;
		this.fichier = estFichier;
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

	/**
	 * Accès à l'id.
	 * @return l'id
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/**
	 * Accès au dossier conteneur.
	 * @return Le dossier conteneur
	 */
	public Dossier getDossierConteneur() {
		return dossierConteneur;
	}

	/**
	 * Change le dossier conteneur.
	 * @param dossierConteneur Le dossier conteneur
	 */
	public void setDossierConteneur(Dossier dossierConteneur) {
		this.dossierConteneur = dossierConteneur;
	}

	/**
	 * Permet de savoir le type concret (fichier ou dossier).
	 * @return le type
	 */
	public Boolean isFile() {
		return this.fichier;
	}

	/**
	 * Permet un affichage simplifié.
	 * @return le nom de l'élément
	 */
	@Override
	public String toString() {
		return this.getNom();
	}

}
