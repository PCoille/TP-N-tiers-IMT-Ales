package slack.entities;

import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Invitation
 *
 */
@Entity

public class Invitation implements GenericEntity {

	   
	@Id
	@GeneratedValue
	private int id;
	private Date invitationDate;
	private Date joinDate;
	private User sender;
	private User receiver;
	private Channel channel;
	private static final long serialVersionUID = 1L;

	public Invitation() {
		super();
	}   
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public void setId(int ID) {
		this.id = ID;
	}   
	public Date getInvitationDate() {
		return this.invitationDate;
	}

	public void setInvitationDate(Date invitationDate) {
		this.invitationDate = invitationDate;
	}   
	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}   
	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}   
	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}   
	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Invitation))
			return false;
		Invitation other = (Invitation) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
