package slack.beans.crud;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import slack.beans.exceptions.AlreadyExistsExeption;
import slack.beans.exceptions.NotFoundException;
import slack.dao.GenericDAO;
import slack.entities.GenericEntity;

public class GenericCrudService<T extends GenericEntity> {

	@EJB
	GenericDAO<T> dao;
	
	protected GenericDAO<T> getDAO() {
		return dao;
	}
	
	protected void create(T t) {
		try {
			dao.persist(t);
		} catch (AlreadyExistsExeption e) {
			e.printStackTrace();
		}
	}

	protected void update(T t) {
		try {
			dao.update(t);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	protected T find(int id) {
		try {
			return dao.find(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void delete(T t) {
		try {
			dao.remove(t.getId());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void delete(int id) {
		try {
			dao.remove(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected List<T> findAll() {
		try {
			return dao.findAll();
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ArrayList<T>();
		}
	}
}
