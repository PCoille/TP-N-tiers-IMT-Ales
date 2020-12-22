package slack.beans.crud;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import slack.entities.Channel;
import slack.entities.Message;

/**
 * Session Bean implementation class ChannelService
 */
@Stateless
@LocalBean
public class ChannelService extends GenericCrudService<Channel> implements ChannelServiceRemote {

	@Override
	public void createChannel(Channel channel) {
		this.create(channel);
	}

	@Override
	public void updateChannel(Channel channel) {
		this.update(channel);
	}

	@Override
	public Channel findChannel(int id) {
		return this.find(id);
	}

	@Override
	public void deleteChannel(Channel channel) {
		this.delete(channel);
	}

	@Override
	public void deleteChannel(int id) {
		this.delete(id);
	}

	@Override
	public List<Channel> findAllChannels() {
		return this.findAll();
	}

	@Override
	public List<Message> getChannelMessages(int id) {
		return this.findChannel(id).getMessages();
	}



}
