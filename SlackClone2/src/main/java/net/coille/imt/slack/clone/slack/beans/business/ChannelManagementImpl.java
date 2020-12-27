package net.coille.imt.slack.clone.slack.beans.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceImpl;
import net.coille.imt.slack.clone.slack.beans.crud.InvitationServiceImpl;
import net.coille.imt.slack.clone.slack.beans.crud.MessageServiceImpl;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceImpl;
import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.entities.Invitation;
import net.coille.imt.slack.clone.slack.entities.Message;
import net.coille.imt.slack.clone.slack.entities.User;

@Stateless
@LocalBean
public class ChannelManagementImpl implements ChannelManagementRemote {

	@EJB
	ChannelServiceImpl channelService;
	@EJB
	MessageServiceImpl messageService;
	@EJB
	UserServiceImpl userService;
	@EJB
	InvitationServiceImpl invitationService;
	
	@Override
	public int createChannel(int creatorId, String name, String description, boolean isPrivate) {
		User creator = userService.findUser(creatorId);
		
		Channel channel = new Channel();
		channel.setCreationDate(new Date());
		channel.setDescription(description);
		channel.setIsInviteOnly(isPrivate);
		channel.setMembers(new ArrayList<User>(Arrays.asList(creator)));
		//channel.setMessages(new ArrayList<Message>());
		channel.setName(name);
		
		return channelService.createChannel(channel);
	}

	@Override
	public void removeUser(int channelId, int userId) {
		Channel channel = channelService.findChannel(channelId);
		User user = userService.findUser(userId);
		
		channel.getMembers().remove(user);
		
		channelService.updateChannel(channel);
	}

	@Override
	public int inviteUser(int senderId,int receiverId, int channelId) {
		Channel channel = channelService.findChannel(channelId);
		User sender = userService.findUser(senderId);
		User receiver = userService.findUser(receiverId);
		
		if (channel.getMembers().contains(sender)) {
			return sendInvite(sender, receiver, channel);
		}
		else {
			return -1;
		}
	}
	
	private int sendInvite(User sender, User receiver, Channel channel) {
		Invitation invitation = new Invitation();
		invitation.setChannel(channel);
		invitation.setInvitationDate(new Date());
		invitation.setReceiver(receiver);
		invitation.setSender(sender);
		
		return invitationService.createInvitation(invitation);
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

	@Override
	public List<Message> getChannelMessage(int channelId) {
		//Channel channel = channelService.findChannel(channelId);

		return channelService.getChannelMessages(channelId);
	}

	@Override
	public LoginStatus userLogin(int channelId, String username, String password) {
		User user = userService.findUserByName(username);
		Channel channel = channelService.findChannel(channelId);
		
		if (user != null && username != null && password != null) {
			if(password.equals(user.getPassword())) {
				if(channel.isInviteOnly() && !channel.getMembers().contains(user)) {
					return LoginStatus.NOT_A_MEMBER;
				}
				else {
					return LoginStatus.SUCCESS;
				}
			}
		}
		
		return LoginStatus.WRONG_ID;
	}
	
	

}
