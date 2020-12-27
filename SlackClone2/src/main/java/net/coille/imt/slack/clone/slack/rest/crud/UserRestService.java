package net.coille.imt.slack.clone.slack.rest.crud;

import java.util.Date;
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

import net.coille.imt.slack.clone.slack.entities.User;
import net.coille.imt.slack.clone.slack.rest.BeansManager;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceRemote;

@Path("/user")
public class UserRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create() {
		UserServiceRemote userService;
		try {
			userService = beansManager.getUserService();
			
			User user = new User();
			user.setName("name");
			user.setBirthDate(new Date());
			user.setForename("forename");
			user.setNickname("nickname");
			
			
			userService.createUser(user);
			
			return Response
					.status(Response.Status.OK)
					.entity(user)
					.build();
		
		} catch (Exception e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.OK)
					.entity("error")
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		UserServiceRemote userService;
		try {
			userService = beansManager.getUserService();
			List<User> users = userService.findAllUsers();
			
			
			return Response
					.status(Response.Status.OK)
					.entity(users)
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
		UserServiceRemote userService;
		try {
			userService = beansManager.getUserService();
			User user = userService.findUser(id);
			
			return Response
					.status(Response.Status.OK)
					.entity(user)
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
	public Response post(User user) {
		UserServiceRemote userService;
		try {
			userService = beansManager.getUserService();
			
			int uid = userService.createUser(user);
			
			return Response
					.status(Response.Status.OK)
					.entity(userService.findUser(uid))
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
