package net.coille.imt.slack.clone.slack.entities;

import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import net.bytebuddy.pool.TypePool.Default.ReaderMode;
/**
 * Entity implementation class for Entity: Channel
 *
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Channel implements GenericEntity {

	   
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private Date creationDate;
	private boolean inviteOnly;
	private static final long serialVersionUID = 1L;
	//@OneToMany(mappedBy = "channel")
	//private List<Message> messages;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> members;

	/*public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}*/
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
	public boolean isInviteOnly() {
		return this.inviteOnly;
	}

	public void setIsInviteOnly(boolean inviteOnly) {
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
