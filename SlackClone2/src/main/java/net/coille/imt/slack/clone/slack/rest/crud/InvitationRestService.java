package net.coille.imt.slack.clone.slack.rest.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.coille.imt.slack.clone.slack.beans.crud.InvitationServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Invitation;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("invitation")
public class InvitationRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		InvitationServiceRemote invitationService;
		try {
			invitationService = beansManager.getInvitationService();
			List<Invitation> invitations = invitationService.findAllInvitations();
			
			
			return Response
					.status(Response.Status.OK)
					.entity(invitations)
					.build();
		} catch (NamingException e) {
			e.printStackTrace();
			return Response
					.status(Response.Status.OK)
					.entity("error")
					.build();
		}
	}
}
