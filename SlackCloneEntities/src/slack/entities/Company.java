package slack.entities;

import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity

public class Company implements GenericEntity {

	   
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String website;
	private static final long serialVersionUID = 1L;

	public Company() {
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
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}
 
}
