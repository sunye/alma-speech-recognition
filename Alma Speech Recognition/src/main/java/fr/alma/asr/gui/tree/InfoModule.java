package fr.alma.asr.gui.tree;

/**
 *
 * @author LeClubber
 */
public class InfoModule {
	
	/** Le nom du module. */
	private String nom;
	/** Indique si les dossiers courants doivent être créés. */
	private boolean creerDossiersCourants;

	/**
	 * Constructeur.
	 * @param nom le nom du module
	 * @param creerDossiersCourants le booléen
	 */
	public InfoModule(String nom, boolean creerDossiersCourants) {
		this.nom = nom;
		this.creerDossiersCourants = creerDossiersCourants;
	}

	/**
	 * Indique si les dossiers courants doivent être créés.
	 * @return un booléen
	 */
	public boolean creerDossiersCourants() {
		return creerDossiersCourants;
	}

	/**
	 * Accède au nom.
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}

}
