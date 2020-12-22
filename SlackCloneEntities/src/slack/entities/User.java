package slack.entities;

import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements GenericEntity {

	   
	@Id
	@GeneratedValue
	private int UID;
	private String name;
	private String forename;
	private String nickname;
	private Date birthDate;
	private String email;
	private String password;
	private Company company;
	@OneToMany()
	private List<Message> messages;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public int getUID() {
		return this.UID;
	}

	public void setUID(int UID) {
		this.UID = UID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getForename() {
		return this.forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}   
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}   
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.getUID();
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.setUID(id);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + UID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (UID != other.UID)
			return false;
		return true;
	}
   
}
