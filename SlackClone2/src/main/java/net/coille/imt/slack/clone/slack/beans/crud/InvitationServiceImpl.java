package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.dao.entities.InvitationDAOImpl;
import net.coille.imt.slack.clone.slack.entities.Invitation;

/**
 * Session Bean implementation class InvitationServiceImpl
 */
@Stateless
@LocalBean
public class InvitationServiceImpl implements InvitationServiceRemote {

	@EJB
	InvitationDAOImpl simpleDao;

	
	@Override
	public int createInvitation(Invitation invitation) {
		return simpleDao.persist(invitation);
	}

	@Override
	public void updateInvitation(Invitation invitation) {
		simpleDao.update(invitation);
	}

	@Override
	public Invitation findInvitation(int id) {
		return simpleDao.find(id);
	}

	@Override
	public void deleteInvitation(Invitation invitation) {
		simpleDao.delete(invitation.getId());
	}

	@Override
	public void deleteInvitation(int id) {
		simpleDao.delete(id);
	}

	@Override
	public List<Invitation> findAllInvitations() {
		return simpleDao.findAll();
	}

}
