package fr.alma.asr.dao;

import java.util.List;

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

	/**
	 * Accède aux modules.
	 * @return la liste des modules
	 */
	List<Folder> findModules();

}
