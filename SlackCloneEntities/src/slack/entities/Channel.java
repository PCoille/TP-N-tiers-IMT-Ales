package slack.entities;

import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Channel
 *
 */
@Entity

public class Channel implements GenericEntity {

	   
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private Date creationDate;
	private boolean inviteOnly;
	private static final long serialVersionUID = 1L;
	@OneToMany
	private List<Message> messages;
	@ManyToMany
	private List<User> members;

	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Channel() {
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}   
	public boolean getInviteOnly() {
		return this.inviteOnly;
	}

	public void setInviteOnly(boolean inviteOnly) {
		this.inviteOnly = inviteOnly;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
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
		if (!(obj instanceof Channel))
			return false;
		Channel other = (Channel) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
