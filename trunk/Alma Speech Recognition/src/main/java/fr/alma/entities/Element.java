package fr.alma.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Classe Element.
 * @author Jérémy Braud
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element extends AbstractEntity {

	/** Id. */
	@Id
	@GeneratedValue
	private long id;
	/** Date de création. */
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateCreation;
	/** Dernière date de modification. */
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateModification;
	/** Nom. */
	@Basic
	private String nom;
	/** Le type d'élément. */
	@Basic
	private Boolean fichier;
	/** Dossier conteneur. */
	@ManyToOne
	private Dossier dossierConteneur;

	/**
	 * Constructeur par defaut.
	 */
	public Element() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom de l'élément
	 * @param estFichier indique si l'élément créé est un fichier
	 */
	public Element(String nom, Boolean estFichier) {
		this.nom = nom;
		this.fichier = estFichier;
		this.dateCreation = new Date();
		this.dateModification = new Date();
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
		this.setChanged();
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
	 * Accès à la date de création du fichier.
	 * @return la date
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * Accès à la date de modification du fichier.
	 * @return la date
	 */
	public Date getDateModification() {
		return dateModification;
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
		this.setChanged();
	}

	/**
	 * Permet de savoir le type concret (fichier ou dossier).
	 * @return le type
	 */
	public Boolean isFile() {
		return this.fichier;
	}

	/**
	 * Change la date de modification.
	 */
	protected void setChanged() {
		this.dateModification = new Date();
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
