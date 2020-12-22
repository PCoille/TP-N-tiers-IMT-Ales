package slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import slack.entities.Channel;
import slack.entities.Message;

@Remote
public interface ChannelServiceRemote {
	void createChannel(Channel channel);
	void updateChannel(Channel channel);
	Channel findChannel(int id);
	void deleteChannel(Channel channel);
	void deleteChannel(int id);
	List<Channel> findAllChannels();
	
	List<Message> getChannelMessages(int id);
}
