package slack.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import slack.beans.exceptions.AlreadyExistsExeption;
import slack.beans.exceptions.NotFoundException;
import slack.entities.GenericEntity;

/**
 * Session Bean implementation class GenericDAO
 */
@Stateless
@LocalBean
public class GenericDAO<T extends GenericEntity> {

	@PersistenceContext
	private EntityManager em;
	private final Class<T> type;

	
	public GenericDAO() {
		//From https://xebia.com/blog/acessing-generic-types-at-runtime-in-java/
        this.type = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
	
    public GenericDAO(Class<T> type) {
        this.type = type;
    }
    
    /**
     * Persist an element which is not already existing
     * @param t
     * @return
     * @throws AlreadyExistsExeption
     */
    public int persist(T t) throws AlreadyExistsExeption {
    	try {
    		em.persist(t);
    	}
    	catch (PersistenceException e)
		{
			e.printStackTrace();
			throw new AlreadyExistsExeption();
		}
    	return t.getId();
    }
    
    /**
     * Update an already existing element 
     * @param t
     * @throws NotFoundException
     */
    public void update(T t) throws NotFoundException {
    	T t2 = em.find(type,t.getId());
		if (t2==null) throw new NotFoundException();
		em.merge(t);
    }

    /**
     * Find element with matching id
     * @param id
     * @return Element with matching id
     * @throws NotFoundException
     */
    public T find(int id) throws NotFoundException {
    	T t = em.find(type, id);
    	if (t==null) throw new NotFoundException();
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
    public T find(String field, String value) throws NotFoundException {
    	T t = findAll(field, value).get(0);
    	if (t==null) throw new NotFoundException();
    	return t;
    }
    
    /**
     * Find exact match for value in field
     * @param field
     * @param value
     * @return Matching elements
     * @throws NotFoundException
     */
    public List<T> findAll(String field, String value) throws NotFoundException {
    	TypedQuery<T> q = em.createQuery("select t from " + type.getSimpleName() + " t "
    			+ "where t." + field + " = \'"+ value +"\'", type);
    	
    	List<T> matchList = q.getResultList();
    	return matchList;
    }
    
    public List<T> findAll() throws NotFoundException {
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
    public List<T> searchAll(String field, String value) throws NotFoundException {
    	TypedQuery<T> q = em.createQuery("select t from " + type.getSimpleName() + " t "
    			+ "where t." + field + " like \'%"+ value +"%\'", type);
    	
    	List<T> matchList = q.getResultList();
    	return matchList;
    }
    
    public void remove(int id) throws NotFoundException {
    	T t = this.find(id);
    	
    	em.remove(t);
    }
}
