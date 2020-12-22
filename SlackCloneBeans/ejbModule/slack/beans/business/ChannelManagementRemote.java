package slack.beans.business;

import javax.ejb.Remote;

@Remote
public interface ChannelManagementRemote {
	void createChannel(int creatorId, String name, String description, boolean isPrivate);
	void removeUser(int channelId, int userId);
	void inviteUser(int senderId, int receiverId, int channelId);
	void acceptInvite(int userId, int inviteId);
}
