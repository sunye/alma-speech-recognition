package fr.alma.asr.entities;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Classe des entités Dossier.
 * @author Jérémy Braud
 */
@Entity
public class Folder extends Element {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;

	/** Module. */
	@Basic
	private boolean isModule;
	
	/** Elements. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dossierConteneur")
	private Collection<Element> elements;
	
	/**
	 * Constructeur par défaut.
	 */
	public Folder() {
		this.isModule = false;
		this.elements = new LinkedList<Element>();
	}
	
	/**
	 * Constructeur.
	 * @param nom le nom
	 */
	public Folder(String nom) {
		super(nom);
		this.isModule = false;
		this.elements = new LinkedList<Element>();
	}
	
	/**
	 * Getter de l'attribut elements.
	 * @return elements 
	 */
	public final Collection<Element> getElements() {
		return this.elements;
	}
	
	/**
	 * Setter de l'attribut elements.
	 * @param elements la collection d'elements
	 */
	public final void setElements(Collection<Element> elements) {
		this.elements = elements;
		this.setChanged();
	}
	
	/**
	 * Methode permettant d'ajouter un élément.
	 * @param element l'élément ajouté
	 */
	public final void addElement(Element element){
		element.setDossierConteneur(this);
		this.elements.add(element);
		this.setChanged();
	}

	/**
	 * Methode permettant d'ajouter des elements à un index donné.
	 * @param element l'élément ajouté
	 * @param index l'index
	 */
	public final void addElementIndex(Element element, int index) {
		LinkedList<Element> liste = new LinkedList<Element>(this.elements);
		element.setDossierConteneur(this);
		liste.add(index, element);
		this.elements = liste;
		this.setChanged();
	}
	
	/**
	 * Methode permettant de supprimer des elements.
	 * @param element l'élément supprimé
	 */
	public final void removeElement(Element element){
		this.elements.remove(element);
		this.setChanged();
	}

	/**
	 * Définit le dossier en tant que module.
	 */
	public final void setModule() {
		this.isModule = true;
	}

	/**
	 * Teste si le dossier est un module.
	 * @return true si c'est un module
	 */
	public final boolean isModule() {
		return this.isModule;
	}

	@Override
	public final boolean isFile() {
		return false;
	}
	
}
