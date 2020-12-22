package slack.beans.crud;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import slack.entities.Channel;
import slack.entities.Message;
import slack.entities.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService extends GenericCrudService<User> implements UserServiceRemote {

	@Override
	public void createUser(User user) {
		this.create(user);
	}

	@Override
	public void updateUser(User user) {
		this.update(user);
	}

	@Override
	public User findUser(int id) {
		return this.find(id);
	}

	@Override
	public void deleteUser(User user) {
		this.delete(user);
	}

	@Override
	public void deleteUser(int id) {
		this.delete(id);
	}

	@Override
	public List<User> findAllUsers() {
		return this.findAll();
	}

	

	

}
