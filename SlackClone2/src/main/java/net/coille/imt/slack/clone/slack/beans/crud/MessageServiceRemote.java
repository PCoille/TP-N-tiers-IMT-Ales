package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.Message;

@Remote
public interface MessageServiceRemote {
	int createMessage(Message message);
	void updateMessage(Message message);
	Message findMessage(int id);
	void deleteMessage(Message message);
	void deleteMessage(int id);
	List<Message> findAllMessages();
}
