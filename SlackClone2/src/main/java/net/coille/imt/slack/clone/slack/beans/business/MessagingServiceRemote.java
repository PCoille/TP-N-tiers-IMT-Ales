package net.coille.imt.slack.clone.slack.beans.business;

import javax.ejb.Remote;

@Remote
public interface MessagingServiceRemote {
	void sendMessage(int emitterId, String content, int channelId);
	void editMessage(int editerId, int messageId, String newContent);
}
