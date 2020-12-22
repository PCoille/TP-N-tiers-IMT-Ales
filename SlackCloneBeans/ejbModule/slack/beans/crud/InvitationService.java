package slack.beans.crud;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import slack.entities.Invitation;

/**
 * Session Bean implementation class InvitationService
 */
@Stateless
@LocalBean
public class InvitationService extends GenericCrudService<Invitation> implements InvitationServiceRemote {

	@Override
	public void createInvitation(Invitation invitation) {
		this.create(invitation);
	}

	@Override
	public void updateInvitation(Invitation invitation) {
		this.update(invitation);
	}

	@Override
	public Invitation findInvitation(int id) {
		return this.find(id);
	}

	@Override
	public void deleteInvitation(Invitation invitation) {
		this.delete(invitation);
	}

	@Override
	public void deleteInvitation(int id) {
		this.delete(id);
	}

	@Override
	public List<Invitation> findAllInvitations() {
		return this.findAll();
	}

}
