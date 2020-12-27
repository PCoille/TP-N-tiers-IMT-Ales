package net.coille.imt.slack.clone.slack.rest.business;

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

import net.coille.imt.slack.clone.slack.beans.business.ChannelManagementRemote;
import net.coille.imt.slack.clone.slack.beans.business.LoginStatus;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Message;
import net.coille.imt.slack.clone.slack.entities.User;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("/business/channel_management")
public class ChannelManagementRestService {
	@EJB
	BeansManager beansManager;
	
	private class ChannelCreationData {
		int creatorId;
		String name;
		String description;
		boolean isPrivate;
	}
	
	private class UserRemovalData {
		int channelId;
		int userId;
	}
	
	private class UserInviteData {
		int senderId;
		int receiverId;
		int channelId;
	}
	
	private class InviteAcceptationData {
		int userId;
		int inviteId;
	}
	
	private static class UserLoginData {
		public UserLoginData() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getChannelId() {
			return channelId;
		}
		public void setChannelId(int channelId) {
			this.channelId = channelId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		int channelId;
		String username;
		String password;
		
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/remove_user")
	public Response removeUser(UserRemovalData data) {
		ChannelManagementRemote channelManagementService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			channelManagementService.removeUser(data.channelId, data.userId);
			
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
	@Path("/invite_user")
	public Response inviteUser(UserInviteData data) {
		ChannelManagementRemote channelManagementService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			channelManagementService.inviteUser(data.senderId, data.receiverId, data.channelId);
			
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
	@Path("/invite_accepted")
	public Response sendMessage(InviteAcceptationData data) {
		ChannelManagementRemote channelManagementService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			channelManagementService.acceptInvite(data.userId, data.inviteId);
			
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
	@Path("/create")
	public Response createChannel(ChannelCreationData data) {
		ChannelManagementRemote channelManagementService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			channelManagementService.createChannel(data.creatorId, data.name, data.description, data.isPrivate);
			
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/message/{channel_id}")
	public Response getMessages(@PathParam("channel_id") int channelId) {
		ChannelManagementRemote channelManagementService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			List<Message> channelMessages = channelManagementService.getChannelMessage(channelId);
			
			return Response
					.status(Response.Status.OK)
					.entity(channelMessages)
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
	@Path("/user_login")
	public Response userLogin(UserLoginData data) {
		ChannelManagementRemote channelManagementService;
		UserServiceRemote userService;
		try {
			channelManagementService = beansManager.getChannelManagementService();
			userService = beansManager.getUserService();
			
			User user = userService.findUserByName(data.username);
			Integer uid = null;
			if (user != null) uid = user.getId();
			
			LoginStatus loginStatus = channelManagementService.userLogin(data.channelId, data.username, data.password);
					
			
			return Response
					.status(Response.Status.OK)
					.entity("{\"status\": \"" + loginStatus.getValue() + "\","
							+ "\"userId\": " + uid + "}")
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
