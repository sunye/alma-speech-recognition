package fr.alma.asr.entities;

import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Classe Dossier.
 * @author Jérémy Braud
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
	 * @param nom le nom
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
	 * @param elements la collection d'elements
	 */
	public void setElements(Collection<Element> elements) {
		this.elements = elements;
		this.setChanged();
	}
	
	/**
	 * Methode permettant d'ajouter des elements.
	 * @param element l'élément ajouté
	 */
	public void addElements(Element element){
		this.elements.add(element);
		this.setChanged();
	}
	
	/**
	 * Methode permettant de supprimer des elements.
	 * @param element l'élément supprimé
	 */
	public void removeElements(Element element){
		this.elements.remove(element);
		this.setChanged();
	}

}
