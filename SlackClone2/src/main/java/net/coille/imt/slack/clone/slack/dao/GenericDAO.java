package net.coille.imt.slack.clone.slack.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import net.coille.imt.slack.clone.slack.beans.exceptions.AlreadyExistsExeption;
import net.coille.imt.slack.clone.slack.beans.exceptions.NotFoundException;
import net.coille.imt.slack.clone.slack.entities.GenericEntity;

/**
 * Session Bean implementation class GenericDAO
 */
public class GenericDAO<T extends GenericEntity> {
	//@PersistenceContext
	//private EntityManager em;
	private Class<T> type;
	
    public GenericDAO(Class<T> type) {
        this.type = type;
        //this.em = em;
    }
    
    /**
     * Persist an element which is not already existing
     * @param t
     * @return
     * @throws AlreadyExistsExeption
     */
    public int persist(T t, EntityManager em) {
    	try {
    		em.persist(t);
    	}
    	catch (PersistenceException e)
		{
			e.printStackTrace();
		}
    	return t.getId();
    }
    
    /**
     * Update an already existing element 
     * @param t
     * @throws NotFoundException
     */
    public void update(T t, EntityManager em) {
		em.merge(t);
    }

    /**
     * Find element with matching id
     * @param id
     * @return Element with matching id
     * @throws NotFoundException
     */
    public T find(int id, EntityManager em) {
    	T t = em.find(type, id);
    	return t;
    }
    
    /**
     * Find exact match for value in field (one element)
     * Does not check for unicity
     * @param field
     * @param value
     * @return Matching element
     * @throws NotFoundException
     */
    public T find(String field, String value, EntityManager em) {
    	List<T> list = findAll(field, value, em);
    	T t = null;
    	
    	if(list.size()>0) 
    		t = list.get(0);
    	return t;
    }
    
    /**
     * Find exact match for value in field
     * @param field
     * @param value
     * @return Matching elements
     * @throws NotFoundException
     */
    public List<T> findAll(String field, String value, EntityManager em) {
    	TypedQuery<T> q = em.createQuery("select t from " + type.getSimpleName() + " t "
    			+ "where t." + field + " = \'"+ value +"\'", type);
    	
    	List<T> matchList = q.getResultList();
    	return matchList;
    }
    
    public List<T> findAll(EntityManager em) {
    	TypedQuery<T> q = em.createQuery("select t from " + type.getSimpleName() + " t ", type);
    	
    	List<T> matchList = q.getResultList();
    	return matchList;
    }
    
    /**
     * Find partial match for value in field
     * @param field
     * @param value
     * @return Matching elements
     * @throws NotFoundException
     */
    public List<T> searchAll(String field, String value, EntityManager em) {
    	TypedQuery<T> q = em.createQuery("select t from " + type.getSimpleName() + " t "
    			+ "where t." + field + " like \'%"+ value +"%\'", type);
    	
    	List<T> matchList = q.getResultList();
    	return matchList;
    }
    
    public void delete(int id, EntityManager em) {
    	T t = this.find(id, em);
    	
    	em.remove(t);
    }
}
