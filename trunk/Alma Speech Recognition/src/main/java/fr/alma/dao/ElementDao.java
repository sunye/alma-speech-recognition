package fr.alma.dao;

import fr.alma.entities.Dossier;
import fr.alma.entities.Element;
import java.util.List;

public interface ElementDao extends AbstractDao<Element> {

	public List<Element> findAllOfDossier(Dossier projet);

}
