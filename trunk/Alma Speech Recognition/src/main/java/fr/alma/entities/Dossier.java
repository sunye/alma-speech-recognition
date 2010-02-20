package fr.alma.entities;

import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Classe Dossier.
 */
@Entity
public class Dossier extends Element {

	/** Elements. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dossierConteneur")
	private Collection<Element> elements;
	
	/**
	 * Constructeur par défaut.
	 */
	public Dossier() {
		this.elements = new LinkedList<Element>();
	}
	
	/**
	 * Constructeur.
	 */
	public Dossier(String nom) {
		super(nom, Boolean.FALSE);
		this.elements = new LinkedList<Element>();
	}
	
	/**
	 * Getter de l'attribut elements.
	 * @return elements 
	 */
	public Collection<Element> getElements() {
		return this.elements;
	}
	
	/**
	 * Setter de l'attribut elements.
	 * @param elements 
	 */
	public void setElements(Collection<Element> elements) {
		this.elements = elements;
	}
	
	/**
	 * Methode permettant d'ajouter des elements.
	 * @param element l'élément ajouté
	 */
	public void addElements(Element element){
		this.elements.add(element);
	}
	
	/**
	 * Methode permettant de supprimer des elements.
	 * @param element l'élément supprimé
	 */
	public void removeElements(Element element){
		this.elements.remove(element);
	}

}
