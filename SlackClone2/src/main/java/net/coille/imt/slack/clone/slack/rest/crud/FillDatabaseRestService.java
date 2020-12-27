package net.coille.imt.slack.clone.slack.rest.crud;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.coille.imt.slack.clone.slack.beans.business.ChannelManagementRemote;
import net.coille.imt.slack.clone.slack.beans.business.MessagingServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.CompanyServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.InvitationServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.MessageServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.entities.Company;
import net.coille.imt.slack.clone.slack.entities.User;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("/fill_db")
public class FillDatabaseRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		UserServiceRemote userService;
		CompanyServiceRemote companyService;
		ChannelServiceRemote channelService;
		InvitationServiceRemote invitationService;
		
		MessagingServiceRemote messagingService;
		ChannelManagementRemote channelManagementService;
		try {
			
			userService = beansManager.getUserService();
			companyService = beansManager.getCompanyService();
			channelService = beansManager.getChannelService();
			invitationService = beansManager.getInvitationService();
			
			channelManagementService = beansManager.getChannelManagementService();
			messagingService = beansManager.getMessagingService();
			
			
			
			Company company1 = new Company();
			company1.setName("company1");
			company1.setWebsite("https://www.google.com");
			
			Company company2 = new Company();
			company2.setName("company2");

			User user1 = new User();
			user1.setName("name");
			user1.setBirthDate(new Date());
			user1.setForename("forename");
			user1.setNickname("nickname");
			user1.setPassword("pwd");
			user1.setCompany(company1);
			
			User user2 = new User();
			user2.setName("name2");
			user2.setBirthDate(new Date());
			user2.setForename("forename2");
			user2.setNickname("nickname2");
			user2.setPassword("pwd2");
			user2.setCompany(company2);
			
			User user3 = new User();
			user3.setName("name3");
			user3.setBirthDate(new Date());
			user3.setForename("forename3");
			user3.setNickname("nickname3");
			user3.setPassword("pwd3");

			
			company1.setId(companyService.createCompany(company1));
			company2.setId(companyService.createCompany(company2));
			
			user1.setId(userService.createUser(user1));
			user2.setId(userService.createUser(user2));
			user3.setId(userService.createUser(user3));
			
			

			
			int channel1_id = channelManagementService.createChannel(user1.getId(), "channel_1_priv", "desc_1", true);
			int channel2_id = channelManagementService.createChannel(user2.getId(), "channel_2_pub", "desc_2", false);
			
			int inv1_id = channelManagementService.inviteUser(user1.getId(), user2.getId(), channel1_id);
			int inv2_id = channelManagementService.inviteUser(user2.getId(), user3.getId(), channel1_id);
			
			channelManagementService.acceptInvite(user2.getId(), inv1_id);
			
			messagingService.sendMessage(user1.getId(), "hello from u1 to channel_1_priv (OK)", channel1_id);
			messagingService.sendMessage(user2.getId(), "hello from u2 to channel_1_priv (OK)", channel1_id);
			messagingService.sendMessage(user3.getId(), "hello from u3 to channel_1_priv (ERROR)", channel1_id);
			
			messagingService.sendMessage(user2.getId(), "hello from u2 to channel_2_pub (OK)", channel2_id);
			messagingService.sendMessage(user1.getId(), "hello from u1 to channel_2_pub (OK)", channel2_id);
			
			
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
	@Path("/test")
	public Response test() {
		UserServiceRemote userService;
		CompanyServiceRemote companyService;
		ChannelServiceRemote channelService;
		InvitationServiceRemote invitationService;
		MessageServiceRemote messageService;
		
		try {
			
			userService = beansManager.getUserService();
			companyService = beansManager.getCompanyService();
			channelService = beansManager.getChannelService();
			invitationService = beansManager.getInvitationService();
			messageService = beansManager.getMessageService();
			
			System.out.println(userService.findAllUsers());
			System.out.println(companyService.findAllCompanies());
			System.out.println(channelService.findAllChannels());
			System.out.println(invitationService.findAllInvitations());
			System.out.println(messageService.findAllMessages());

			List<Channel> a =channelService.findAllChannels();
			
			System.out.println(a);
			//channelService.findAllChannels().forEach((channel)->System.out.println(channelService.getChannelMessages(channel.getId())));
			//channelService.findAllChannels().forEach((channel)->channel.getMessages().forEach((message)->System.out.println(message.getContent())));
			
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
