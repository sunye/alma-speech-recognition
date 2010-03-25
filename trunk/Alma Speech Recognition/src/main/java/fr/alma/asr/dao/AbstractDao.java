package fr.alma.asr.dao;

import java.util.List;

import fr.alma.asr.entities.AbstractEntity;

/**
 * Implémentation du pattern DAO.
 * @author Cédric Krommenhoek
 * @param <AnyEntity> une entité
 */
public interface AbstractDao<AnyEntity extends AbstractEntity> {

	/**
	 * Création d'une entité.
	 * @param entity l'entité a créer
	 * @return l'id
	 */
	Long create(AnyEntity entity);

	/**
	 * Destruction d'une entité par son id.
	 * @param id l'id de l'entité
	 */
	void delete(Long id);

	/**
	 * Détruit toute les entités.
	 */
	void deleteAll();

	/**
	 * Recherche d'une entité par son id.
	 * @param id l'id
	 * @return l'entité
	 */
	AnyEntity find(Long id);

	/**
	 * Trouve toutes les entités.
	 * @return une liste d'entités
	 */
	List<AnyEntity> findAll();

	/**
	 * Met à jour une entité.
	 * @param entity l'entité
	 */
	void update(AnyEntity entity);

}
