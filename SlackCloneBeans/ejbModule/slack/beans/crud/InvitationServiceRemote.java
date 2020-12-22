package slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import slack.entities.Channel;
import slack.entities.Invitation;

@Remote
public interface InvitationServiceRemote {
	void createInvitation(Invitation invitation);
	void updateInvitation(Invitation invitation);
	Invitation findInvitation(int id);
	void deleteInvitation(Invitation invitation);
	void deleteInvitation(int id);
	List<Invitation> findAllInvitations();

}
