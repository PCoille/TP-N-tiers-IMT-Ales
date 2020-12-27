package net.coille.imt.slack.clone.slack.rest.business;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.coille.imt.slack.clone.slack.beans.business.MessagingServiceRemote;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("/business/messaging")
public class MessagingRestService {
	@EJB
	BeansManager beansManager;
	
	private static class SendMessageData {
		public int getEmitterId() {
			return emitterId;
		}
		public void setEmitterId(int emitterId) {
			this.emitterId = emitterId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getChannelId() {
			return channelId;
		}
		public void setChannelId(int channelId) {
			this.channelId = channelId;
		}
		int emitterId; 
		String content;
		int channelId;
	}
	
	private class EditMessageData {
		int editerId;
		int messageId; 
		String newContent;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/send")
	public Response sendMessage(SendMessageData data) {
		MessagingServiceRemote messagingService;
		try {
			messagingService = beansManager.getMessagingService();
			messagingService.sendMessage(data.emitterId, data.content, data.channelId);
			
			
			return Response
					.status(Response.Status.OK)
					.entity("{}")
					.build();
		} catch (NamingException e) {
			e.printStackTrace();
			return Response
					.status(Response.Status.OK)
					.entity("error")
					.build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/edit")
	public Response editMessage(EditMessageData data) {
		MessagingServiceRemote messagingService;
		try {
			messagingService = beansManager.getMessagingService();
			messagingService.editMessage(data.editerId, data.messageId, data.newContent);
			
			return Response
					.status(Response.Status.OK)
					.entity("{}")
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
