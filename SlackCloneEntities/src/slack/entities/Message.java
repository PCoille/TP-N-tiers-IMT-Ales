package slack.entities;

import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements GenericEntity {
	// TODO: Add a "seen by" feature

	   
	@Id
	@GeneratedValue
	private int id;
	private String content;
	private Date sendDate;
	private User emitter;
	private Channel channel;
	private boolean edited;
	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}   
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public User getEmitter() {
		return emitter;
	}
	public void setEmitter(User emitter) {
		this.emitter = emitter;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
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
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
