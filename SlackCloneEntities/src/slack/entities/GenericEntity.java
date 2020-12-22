package slack.entities;

import java.io.Serializable;

public interface GenericEntity extends Serializable {
	int getId();
	void setId(int id);
}
