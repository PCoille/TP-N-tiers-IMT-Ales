package slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import slack.entities.Channel;
import slack.entities.Message;
import slack.entities.User;

@Remote
public interface MessageServiceRemote {
	void createMessage(Message message);
	void updateMessage(Message message);
	Message findMessage(int id);
	void deleteMessage(Message message);
	void deleteMessage(int id);
	List<Message> findAllMessages();
}
