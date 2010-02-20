package fr.alma.dao;

import fr.alma.entities.AbstractEntity;
import java.util.List;

/**
 * Implémentation du pattern DAO
 * @author Jérémy Braud
 * @param <AnyEntity> une entité
 */
public interface AbstractDao<AnyEntity extends AbstractEntity> {
	
	public Long create(AnyEntity entity);
	
	public void delete(Long id);
	
	public void deleteAll();
	
	public AnyEntity find(Long id);
	
	public List<AnyEntity> findAll();
	
	public void update(AnyEntity entity);

}
