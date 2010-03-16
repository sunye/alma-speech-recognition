package fr.alma.asr.entities;

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

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;
	
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
	/** Dossier conteneur. */
	@ManyToOne
	private Folder dossierConteneur;

	/**
	 * Constructeur par defaut.
	 */
	public Element() {
	}

	/**
	 * Constructeur.
	 * @param nom le nom de l'élément
	 */
	public Element(String nom) {
		this.nom = nom;
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
	public Folder getDossierConteneur() {
		return dossierConteneur;
	}

	/**
	 * Change le dossier conteneur.
	 * @param dossierConteneur Le dossier conteneur
	 */
	public void setDossierConteneur(Folder dossierConteneur) {
		this.dossierConteneur = dossierConteneur;
		this.setChanged();
	}

	/**
	 * Permet de savoir le type concret (fichier ou dossier).
	 * @return le type
	 */
	public abstract boolean isFile();

	/**
	 * Change la date de modification.
	 */
	protected void setChanged() {
		this.dateModification = new Date();
	}

	@Override
	public String toString() {
		return this.getNom();
	}

	@Override
	public boolean equals(Object other) {
		Element element = (Element) other;
		return (this.id == element.getId() && this.nom.equals(element.getNom()));
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode() + this.nom.hashCode();		
	}
	
}
