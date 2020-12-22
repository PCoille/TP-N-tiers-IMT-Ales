package slack.beans.business;

import java.util.Date;

import javax.ejb.EJB;

import slack.beans.crud.ChannelService;
import slack.beans.crud.MessageService;
import slack.beans.crud.UserService;
import slack.entities.Channel;
import slack.entities.Message;
import slack.entities.User;

public class MessagingService implements MessagingServiceRemote {
	@EJB
	ChannelService channelService;
	@EJB
	MessageService messageService;
	@EJB
	UserService userService;
	
	@Override
	public void sendMessage(int emitterId, String content, int channelId) {
		User emitter = userService.findUser(emitterId);
		Channel channel = channelService.findChannel(channelId);
		
		
		Message message = new Message();
		message.setChannel(channel);
		message.setContent(content);
		message.setEdited(false);
		message.setEmitter(emitter);
		message.setSendDate(new Date());
		
		messageService.createMessage(message);
		channel.getMessages().add(message);
		channelService.updateChannel(channel);
	}

	@Override
	public void editMessage(int editerId, int messageId, String newContent) {
		Message message = messageService.findMessage(messageId);
		
		if (message != null) {
			if (editerId == message.getEmitter().getId()) {
				message.setContent(newContent);
				message.setEdited(true);
				
				messageService.updateMessage(message);
			}
		}
	}
}
