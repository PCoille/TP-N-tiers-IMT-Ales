package net.coille.imt.slack.clone.slack.beans.business;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceImpl;
import net.coille.imt.slack.clone.slack.beans.crud.MessageServiceImpl;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceImpl;
import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.entities.Message;
import net.coille.imt.slack.clone.slack.entities.User;

@Stateless
@LocalBean
public class MessagingServiceImpl implements MessagingServiceRemote {
	@EJB
	ChannelServiceImpl channelService;
	@EJB
	MessageServiceImpl messageService;
	@EJB
	UserServiceImpl userService;
	
	@Override
	public void sendMessage(int emitterId, String content, int channelId) {
		User emitter = userService.findUser(emitterId);
		Channel channel = channelService.findChannel(channelId);
		
		if(channel.isInviteOnly()) {
			if(channel.getMembers().contains(emitter)) {
				createAndSendMessage(emitter, content, channel);
			}
		}
		else {
			createAndSendMessage(emitter, content, channel);
		}
	}
	
	private void createAndSendMessage(User emitter, String content, Channel channel) {
		Message message = new Message();
		message.setChannel(channel);
		message.setContent(content);
		message.setEdited(false);
		message.setEmitter(emitter);
		message.setSendDate(new Date());
		
		messageService.createMessage(message);
		//channel.getMessages().add(message);
		//channelService.updateChannel(channel);
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
