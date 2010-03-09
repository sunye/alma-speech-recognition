package fr.alma.asr.entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe de l'entité Racine.
 * @author Jérémy Braud
 */
@Entity
public class Root extends AbstractEntity {

	/** Numéro de série par défaut. */
	private static final long serialVersionUID = 1L;
	/** Id. */
	@Id
	@GeneratedValue
	private long id;
	/** Le nom. */
	@Basic
	private String nom;
	/** Modules. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Subject> modules;

	/**
	 * Constructeur par défaut.
	 */
	public Root() {
		this.modules = new LinkedList<Subject>();
	}

	/**
	 * Constructeur.
	 * @param nom le nom
	 */
	public Root(String nom) {
		this.nom = nom;
		this.modules = new LinkedList<Subject>();
	}

	@Override
	public long getId() {
		return this.id;
	}

	/**
	 * Getter de l'attribut modules.
	 * @return modules
	 */
	public List<Subject> getModules() {
		return this.modules;
	}

	/**
	 * Setter de l'attribut modules.
	 * @param modules la collection d'modules
	 */
	public void setModules(LinkedList<Subject> modules) {
		this.modules = modules;
	}

	/**
	 * Methode permettant d'ajouter des modules.
	 * @param module l'élément ajouté
	 */
	public void addModules(Subject module){
		this.modules.add(module);
	}

	/**
	 * Methode permettant d'ajouter des modules à un index donné.
	 * @param module l'élément ajouté
	 * @param index l'index
	 */
	public void addModuleIndex(Subject module, int index) {
		this.modules.add(index, module);
	}

	/**
	 * Methode permettant de supprimer des modules.
	 * @param module l'élément supprimé
	 */
	public void removeModule(Subject module){
		this.modules.remove(module);
	}

}
