package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.entities.Message;

@Remote
public interface ChannelServiceRemote {
	int createChannel(Channel channel);
	void updateChannel(Channel channel);
	Channel findChannel(int id);
	void deleteChannel(Channel channel);
	void deleteChannel(int id);
	List<Channel> findAllChannels();
	
	List<Message> getChannelMessages(int id);
}
