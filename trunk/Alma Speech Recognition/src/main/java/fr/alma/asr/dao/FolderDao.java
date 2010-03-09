package fr.alma.asr.dao;

import fr.alma.asr.entities.Folder;

/**
 * Interface dao de la classe Dossier.
 * @author Jérémy Braud
 */
public interface FolderDao extends AbstractDao<Folder> {

	/**
	 * Accède au dossier racine.
	 * @return le dossier racine
	 */
	Folder findDossierRacine();

}
