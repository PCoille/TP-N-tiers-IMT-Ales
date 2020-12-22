package slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import slack.entities.Channel;
import slack.entities.User;

@Remote
public interface UserServiceRemote {
	void createUser(User user);
	void updateUser(User user);
	User findUser(int id);
	void deleteUser(User user);
	void deleteUser(int id);
	List<User> findAllUsers();
}
