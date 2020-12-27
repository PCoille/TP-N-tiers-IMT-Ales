package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.dao.entities.UserDAOImpl;
import net.coille.imt.slack.clone.slack.entities.User;

/**
 * Session Bean implementation class UserServiceImpl
 */
@Stateless
@LocalBean
public class UserServiceImpl implements UserServiceRemote {

	@EJB
    UserDAOImpl simpleDao;

	
	@Override
	public int createUser(User user) {
		return simpleDao.persist(user);
	}

	@Override
	public void updateUser(User user) {
		simpleDao.update(user);
	}

	@Override
	public User findUser(int id) {
		return simpleDao.find(id);
	}

	@Override
	public void deleteUser(User user) {
		simpleDao.delete(user.getId());
	}

	@Override
	public void deleteUser(int id) {
		simpleDao.delete(id);
	}

	@Override
	public List<User> findAllUsers() {
		return simpleDao.findAll();
	}

	@Override
	public User findUserByName(String username) {
		User user = null;
		
		user = simpleDao.find("name", username);
		
		return user;
	}

	

	

}
