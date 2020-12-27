package net.coille.imt.slack.clone.slack.entities;

import java.lang.String;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: User
 *
 */

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements GenericEntity {

	   
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String forename;
	private String nickname;
	private Date birthDate;
	private String email;
	private String password;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId=true)
	private Company company;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
