package net.coille.imt.slack.clone.slack.rest;

import java.util.Hashtable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.coille.imt.slack.clone.slack.beans.business.ChannelManagementRemote;
import net.coille.imt.slack.clone.slack.beans.business.MessagingServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.CompanyServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.InvitationServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.MessageServiceRemote;
import net.coille.imt.slack.clone.slack.beans.crud.UserServiceRemote;

@Stateless
@LocalBean
public class BeansManager {
	InitialContext ic;
	
	public BeansManager() {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.SECURITY_PRINCIPAL,"user");
	    jndiProperties.put(Context.SECURITY_CREDENTIALS, "user123");
	    jndiProperties.put("jboss.naming.client.ejb.context", true);
	    try {
			ic = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserServiceRemote getUserService() throws NamingException {
		return (UserServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/UserServiceImpl!net.coille.imt.slack.clone.slack.beans.crud.UserServiceRemote");
	}
	
	public ChannelServiceRemote getChannelService() throws NamingException {
		return (ChannelServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/ChannelServiceImpl!net.coille.imt.slack.clone.slack.beans.crud.ChannelServiceRemote");
	}
	
	public CompanyServiceRemote getCompanyService() throws NamingException {
		return (CompanyServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/CompanyServiceImpl!net.coille.imt.slack.clone.slack.beans.crud.CompanyServiceRemote");
	}
	
	public InvitationServiceRemote getInvitationService() throws NamingException {
		return (InvitationServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/InvitationServiceImpl!net.coille.imt.slack.clone.slack.beans.crud.InvitationServiceRemote");
	}
	
	public MessageServiceRemote getMessageService() throws NamingException {
		return (MessageServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/MessageServiceImpl!net.coille.imt.slack.clone.slack.beans.crud.MessageServiceRemote");
	}
	
	public MessagingServiceRemote getMessagingService() throws NamingException {
		return (MessagingServiceRemote) ic.lookup("slack-1.0-SNAPSHOT/MessagingServiceImpl!net.coille.imt.slack.clone.slack.beans.business.MessagingServiceRemote");
	}
	
	public ChannelManagementRemote getChannelManagementService() throws NamingException {
		return (ChannelManagementRemote) ic.lookup("slack-1.0-SNAPSHOT/ChannelManagementImpl!net.coille.imt.slack.clone.slack.beans.business.ChannelManagementRemote");
	}
}
