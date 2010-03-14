package fr.alma.asr.dao.impl;

import fr.alma.asr.dao.AbstractDao;
import fr.alma.asr.entities.AbstractEntity;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 * Implementation dao de la classe abstraite.
 * @param <AnyEntity> template
 * @author Cédric Krommenhoek
 */
public abstract class AbstractDaoImpl<AnyEntity extends AbstractEntity> implements AbstractDao<AnyEntity> {

	private static EntityManagerFactory emf;
	private static HashMap<String, String> specificProperties = new HashMap<String, String>();
	private static boolean newProperties = false;
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {			
			emf = Persistence.createEntityManagerFactory("speech-recognition", specificProperties);
		} else if (newProperties) {
			emf.close();
			emf = Persistence.createEntityManagerFactory("speech-recognition", specificProperties);
		}
		newProperties = false;
		return emf;
	}
	
	protected static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<AnyEntity> getEntity() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<AnyEntity>) type.getActualTypeArguments()[0];
	}
	
	protected String getEntitySimpleName() {
		return getEntity().getSimpleName();
	}
	
	public static void addSpecificProperty(String key, String value) {
		specificProperties.put(key, value);
		newProperties = true;
	}

	public static void deconnexion() {
		if (emf != null) {
			emf.close();
		}
	}
	
	
	@Override
	public Long create(AnyEntity entity) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(entity);
		
		tx.commit();
		em.close();		
		return entity.getId();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.remove(em.find(getEntity(), id));
		
		tx.commit();
		em.close();		
	}

	@Override
	public void deleteAll() {
		for (AnyEntity entity : findAll()) {
			long id = entity.getId();
			if (find(id) != null) {
				delete(id);
			}			
		}		
	}
	
	@Override
	public AnyEntity find(Long id) {
		EntityManager em = getEntityManager();
		AnyEntity entity = em.find(getEntity(), id);		
		em.close();		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnyEntity> findAll() {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		String requete = "FROM " + getEntitySimpleName();
		List<AnyEntity> resultats = em.createQuery(requete).getResultList();
		
		tx.commit();
		em.close();
		return resultats;
	}

	@Override
	public void update(AnyEntity entity) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(entity);
		
		tx.commit();
		em.close();		
	}

}
