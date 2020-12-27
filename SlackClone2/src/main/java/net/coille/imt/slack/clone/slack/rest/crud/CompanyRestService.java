package net.coille.imt.slack.clone.slack.rest.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.coille.imt.slack.clone.slack.beans.crud.CompanyServiceRemote;
import net.coille.imt.slack.clone.slack.entities.Company;
import net.coille.imt.slack.clone.slack.rest.BeansManager;

@Path("company")
public class CompanyRestService {
	@EJB
	BeansManager beansManager;	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getAll() {
		CompanyServiceRemote companyService;
		try {
			companyService = beansManager.getCompanyService();
			List<Company> companies = companyService.findAllCompanies();
			
			
			return Response
					.status(Response.Status.OK)
					.entity(companies)
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
