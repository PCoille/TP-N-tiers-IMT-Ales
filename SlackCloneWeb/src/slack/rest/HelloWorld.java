package slack.rest;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import slack.entities.User;
import slack.beans;

@Stateless
@LocalBean
@Path("/test")
public class HelloWorld {
	@EJB
	UserService userService;
	
	
	@GET
	@Produces("text/json")
	@Path("/create")
	public String test() {
		User user = new User();
		user.setName("name");
		user.setBirthDate(new Date());
		user.setForename("forename");
		user.setNickname("nickname");
		
		
		
		return "created";
	}
}
