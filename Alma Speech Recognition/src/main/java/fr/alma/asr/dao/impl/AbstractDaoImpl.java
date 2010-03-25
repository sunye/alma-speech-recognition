package fr.alma.asr.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.alma.asr.dao.AbstractDao;
import fr.alma.asr.entities.AbstractEntity;

/**
 * Implementation dao de la classe abstraite.
 * @param <AnyEntity> template
 * @author Cédric Krommenhoek
 */
public abstract class AbstractDaoImpl<AnyEntity extends AbstractEntity> implements AbstractDao<AnyEntity> {

	/** Singleton factory des EntityManager. */
	private static EntityManagerFactory emf;
	/** L'ensemble des propriétés relatives au mécanisme de persistance. */
	private static Map<String, String> specificProperties = new HashMap<String, String>();
	/** Indique si les propriétés ont été changées, ce qui provoque la mise à jour du singleton. */
	private static boolean newProperties = false;
	
	/**
	 * Méthode d'accès au singleton factory des EntityManager.
	 * @return la factory des EntityManager
	 */
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
	
	/**
	 * Méthode de création d'un nouveau gestionnaire de persistance.
	 * @return le gestionnaire de persistance
	 */
	protected static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	
	/**
	 * Accès à la classe de l'entité courante.
	 * @return la classe de l'entité
	 */
	@SuppressWarnings("unchecked")
	protected final Class<AnyEntity> getEntity() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<AnyEntity>) type.getActualTypeArguments()[0];
	}
	
	/**
	 * Accès au nom simple de l'entité courante.
	 * @return le nom de l'entité
	 */
	protected final String getEntitySimpleName() {
		return getEntity().getSimpleName();
	}
	
	/**
	 * Méthode d'ajout ou de modification des propriétés du gestionnaire de persistance.
	 * @param key la clé de la propriété
	 * @param value la valeur de la propriété
	 */
	public static void addSpecificProperty(String key, String value) {
		specificProperties.put(key, value);
		newProperties = true;
	}

	/**
	 * Méthode de fermeture de la factory.
	 */
	public static void deconnexion() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}
	
	@Override
	public final Long create(AnyEntity entity) {
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
	public final void deleteAll() {
		for (AnyEntity entity : findAll()) {
			long id = entity.getId();
			if (find(id) != null) {
				delete(id);
			}			
		}		
	}
	
	@Override
	public final AnyEntity find(Long id) {
		EntityManager em = getEntityManager();
		AnyEntity entity = em.find(getEntity(), id);		
		em.close();		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<AnyEntity> findAll() {
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
	public final void update(AnyEntity entity) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(entity);
		
		tx.commit();
		em.close();		
	}

}
