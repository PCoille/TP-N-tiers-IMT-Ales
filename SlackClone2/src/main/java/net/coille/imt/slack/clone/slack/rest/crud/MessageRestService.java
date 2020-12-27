package net.coille.imt.slack.clone.slack.rest.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.coille.imt.slack.clone.slack.beans.crud.MessageServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Message;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("/message")
public class MessageRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		MessageServiceRemote messageService;
		try {
			messageService = beansManager.getMessageService();
			List<Message> message = messageService.findAllMessages();
			
			
			return Response
					.status(Response.Status.OK)
					.entity(message)
					.build();
		} catch (NamingException e) {
			e.printStackTrace();
			return Response
					.status(Response.Status.OK)
					.entity("error")
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{id}")
	public Response getById(@PathParam("id") int id) {
		MessageServiceRemote messageService;
		try {
			messageService = beansManager.getMessageService();
			Message message = messageService.findMessage(id);
			
			return Response
					.status(Response.Status.OK)
					.entity(message)
					.build();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response
					.status(Response.Status.OK)
					.entity("error")
					.build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/post")
	public Response post(Message message) {
		MessageServiceRemote messageService;
		try {
			messageService = beansManager.getMessageService();
			
			int id = messageService.createMessage(message);
			
			return Response
					.status(Response.Status.OK)
					.entity(messageService.findMessage(id))
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
