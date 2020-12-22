package slack.beans.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.ejb.EJB;

import slack.beans.crud.ChannelService;
import slack.beans.crud.InvitationService;
import slack.beans.crud.MessageService;
import slack.beans.crud.UserService;
import slack.entities.Channel;
import slack.entities.Invitation;
import slack.entities.Message;
import slack.entities.User;

public class ChannelManagement implements ChannelManagementRemote {

	@EJB
	ChannelService channelService;
	@EJB
	MessageService messageService;
	@EJB
	UserService userService;
	@EJB
	InvitationService invitationService;
	
	@Override
	public void createChannel(int creatorId, String name, String description, boolean isPrivate) {
		User creator = userService.findUser(creatorId);
		
		Channel channel = new Channel();
		channel.setCreationDate(new Date());
		channel.setDescription(description);
		channel.setInviteOnly(isPrivate);
		channel.setMembers(new ArrayList<User>(Arrays.asList(creator)));
		channel.setMessages(new ArrayList<Message>());
		channel.setName(name);
		
		channelService.createChannel(channel);
	}

	@Override
	public void removeUser(int channelId, int userId) {
		Channel channel = channelService.findChannel(channelId);
		User user = userService.findUser(userId);
		
		channel.getMembers().remove(user);
		
		channelService.updateChannel(channel);
	}

	@Override
	public void inviteUser(int senderId,int receiverId, int channelId) {
		Channel channel = channelService.findChannel(channelId);
		User sender = userService.findUser(senderId);
		User receiver = userService.findUser(receiverId);
		
		Invitation invitation = new Invitation();
		invitation.setChannel(channel);
		invitation.setInvitationDate(new Date());
		invitation.setReceiver(receiver);
		invitation.setSender(sender);
		
		invitationService.createInvitation(invitation);
	}

	@Override
	public void acceptInvite(int userId, int inviteId) {
		Invitation invitation = invitationService.findInvitation(inviteId);
		User user = userService.findUser(userId);
		// Get an updated version of channel
		Channel channel = channelService.findChannel(invitation.getChannel().getId());
		
		if (user != null) {
			if (user.equals(invitation.getReceiver())) {
				invitation.setJoinDate(new Date());
				channel.getMembers().add(user);
				
				invitationService.updateInvitation(invitation);
				channelService.updateChannel(channel);
			}
		}
	}

}
