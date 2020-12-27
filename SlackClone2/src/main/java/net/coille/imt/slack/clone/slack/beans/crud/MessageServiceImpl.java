package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.dao.entities.MessageDAOImpl;
import net.coille.imt.slack.clone.slack.entities.Message;

/**
 * Session Bean implementation class MessageServiceImpl
 */
@Stateless
@LocalBean
public class MessageServiceImpl implements MessageServiceRemote {

	@EJB
	MessageDAOImpl simpleDao;
	
    /**
     * Default constructor. 
     */
    public MessageServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int createMessage(Message message) {
		return simpleDao.persist(message);
	}

	@Override
	public void updateMessage(Message message) {
		simpleDao.update(message);
	}

	@Override
	public Message findMessage(int id) {
		return simpleDao.find(id);
	}

	@Override
	public void deleteMessage(Message message) {
		simpleDao.delete(message.getId());
	}

	@Override
	public void deleteMessage(int id) {
		simpleDao.delete(id);
	}

	@Override
	public List<Message> findAllMessages() {
		return simpleDao.findAll();
	}
}
