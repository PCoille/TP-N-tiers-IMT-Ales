package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.User;

@Remote
public interface UserServiceRemote {
	int createUser(User user);
	void updateUser(User user);
	User findUser(int id);
	void deleteUser(User user);
	void deleteUser(int id);
	List<User> findAllUsers();
	User findUserByName(String username);
}
