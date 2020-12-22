package slack.beans.crud;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import slack.dao.GenericDAO;
import slack.entities.Channel;
import slack.entities.Message;
import slack.entities.User;

/**
 * Session Bean implementation class MessageService
 */
@Stateless
@LocalBean
public class MessageService extends GenericCrudService<Message> implements MessageServiceRemote {

	@EJB
	GenericDAO<Message> dao;
	
    /**
     * Default constructor. 
     */
    public MessageService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createMessage(Message message) {
		this.create(message);
	}

	@Override
	public void updateMessage(Message message) {
		this.update(message);
	}

	@Override
	public Message findMessage(int id) {
		return this.find(id);
	}

	@Override
	public void deleteMessage(Message message) {
		this.delete(message);
	}

	@Override
	public void deleteMessage(int id) {
		this.delete(id);
	}

	@Override
	public List<Message> findAllMessages() {
		return this.findAll();
	}
}
