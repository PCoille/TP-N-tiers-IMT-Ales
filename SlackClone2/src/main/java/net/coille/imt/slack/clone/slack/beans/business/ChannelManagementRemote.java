package net.coille.imt.slack.clone.slack.beans.business;

import java.util.List;

import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.Message;

@Remote
public interface ChannelManagementRemote {
	int createChannel(int creatorId, String name, String description, boolean isPrivate);
	void removeUser(int channelId, int userId);
	int inviteUser(int senderId, int receiverId, int channelId);
	void acceptInvite(int userId, int inviteId);
	List<Message> getChannelMessage(int channelId);
	/**
	 * 
	 * @param channelId
	 * @param username
	 * @param password
	 * @return login status
	 */
	LoginStatus userLogin(int channelId, String username, String password);
}
