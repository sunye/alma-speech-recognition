package fr.alma.asr.dao;

import fr.alma.asr.entities.Dossier;

/**
 * Interface dao de la classe Dossier.
 * @author Jérémy Braud
 */
public interface DossierDao extends AbstractDao<Dossier> {

	/**
	 * Accède au dossier racine.
	 * @return le dossier racine
	 */
	Dossier findDossierRacine();

}
