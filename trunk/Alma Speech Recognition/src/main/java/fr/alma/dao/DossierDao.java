package fr.alma.dao;

import fr.alma.entities.Dossier;

public interface DossierDao extends AbstractDao<Dossier> {

	public Dossier findDossierRacine();

}
