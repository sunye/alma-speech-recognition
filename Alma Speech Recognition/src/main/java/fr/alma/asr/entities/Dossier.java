package fr.alma.asr.entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Classe des entités Dossier.
 * @author Jérémy Braud
 */
@Entity
public class Dossier extends Element {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;
	
	/** Elements. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dossierConteneur")
	private List<Element> elements;
	
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
	public List<Element> getElements() {
		return this.elements;
	}
	
	/**
	 * Setter de l'attribut elements.
	 * @param elements la collection d'elements
	 */
	public void setElements(LinkedList<Element> elements) {
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
	 * Methode permettant d'ajouter des elements à un index donné.
	 * @param element l'élément ajouté
	 * @param index l'index
	 */
	public void addElementIndex(Element element, int index) {
		this.elements.add(index, element);
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
