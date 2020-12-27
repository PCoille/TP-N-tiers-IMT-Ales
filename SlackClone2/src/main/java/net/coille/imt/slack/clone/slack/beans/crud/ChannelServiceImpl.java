package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.dao.entities.ChannelDAOImpl;
import net.coille.imt.slack.clone.slack.dao.entities.MessageDAOImpl;
import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.entities.Message;

/**
 * Session Bean implementation class ChannelServiceImpl
 */
@Stateless
@LocalBean
public class ChannelServiceImpl implements ChannelServiceRemote {

	@EJB
	ChannelDAOImpl simpleDao;
	@EJB
	MessageDAOImpl messageDao;
	
	@Override
	public int createChannel(Channel channel) {
		return simpleDao.persist(channel);
	}

	@Override
	public void updateChannel(Channel channel) {
		simpleDao.update(channel);
	}

	@Override
	public Channel findChannel(int id) {
		return simpleDao.find(id);
	}

	@Override
	public void deleteChannel(Channel channel) {
		simpleDao.delete(channel.getId());
	}

	@Override
	public void deleteChannel(int id) {
		simpleDao.delete(id);
	}

	@Override
	public List<Channel> findAllChannels() {
		return simpleDao.findAll();
	}

	@Override
	public List<Message> getChannelMessages(int id) {
		return messageDao.findAll("channel", Integer.toString(id));
	}



}
