package net.coille.imt.slack.clone.slack.dao.entities;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.coille.imt.slack.clone.slack.dao.GenericDAO;
import net.coille.imt.slack.clone.slack.entities.User;

/**
 * Session Bean implementation class UserDAOImpl
 */
@Stateless
@LocalBean
public class UserDAOImpl {

	@PersistenceContext
	private EntityManager em;
	private GenericDAO<User> genericDao;

	
	public UserDAOImpl() {
		this.genericDao = new GenericDAO<>(User.class);
	}
    
    /**
     * Persist an element which is not already existing
     * @param t
     * @return
     */
    public int persist(User t) {
    	return this.genericDao.persist(t, em);
    }
    
    /**
     * Update an already existing element 
     * @param t
     */
    public void update(User t) {
    	this.genericDao.update(t, em);
    }

    /**
     * Find element with matching id
     * @param id
     * @return Element with matching id
     */
    public User find(int id) {
    	return this.genericDao.find(id, em);
    }
    
    /**
     * Find exact match for value in field (one element)
     * Does not check for unicity
     * @param field
     * @param value
     * @return Matching element
     */
    public User find(String field, String value) {
    	return this.genericDao.find(field, value, em);
    }
    
    /**
     * Find exact match for value in field
     * @param field
     * @param value
     * @return Matching elements
     */
    public List<User> findAll(String field, String value) {
    	return this.genericDao.findAll(field, value, em);
    }
    
    public List<User> findAll() {
    	return this.genericDao.findAll(em);
    }
    
    /**
     * Find partial match for value in field
     * @param field
     * @param value
     * @return Matching elements
     */
    public List<User> searchAll(String field, String value) {
    	return this.genericDao.searchAll(field, value, em);
    }
    
    public void delete(int id) {
    	this.genericDao.delete(id, em);
    }

}
