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

import net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Channel;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("/channel")
public class ChannelRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		ChannelServiceRemote channelService;
		try {
			channelService = beansManager.getChannelService();
			List<Channel> channels = channelService.findAllChannels();
			
			
			return Response
					.status(Response.Status.OK)
					.entity(channels)
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
		ChannelServiceRemote channelService;
		try {
			channelService = beansManager.getChannelService();
			Channel channel = channelService.findChannel(id);
			
			return Response
					.status(Response.Status.OK)
					.entity(channel)
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
	public Response post(Channel channel) {
		ChannelServiceRemote channelService;
		try {
			channelService = beansManager.getChannelService();
			
			int id = channelService.createChannel(channel);
			
			return Response
					.status(Response.Status.OK)
					.entity(channelService.findChannel(id))
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
