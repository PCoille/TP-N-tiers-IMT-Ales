package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.Invitation;

@Remote
public interface InvitationServiceRemote {
	int createInvitation(Invitation invitation);
	void updateInvitation(Invitation invitation);
	Invitation findInvitation(int id);
	void deleteInvitation(Invitation invitation);
	void deleteInvitation(int id);
	List<Invitation> findAllInvitations();

}
