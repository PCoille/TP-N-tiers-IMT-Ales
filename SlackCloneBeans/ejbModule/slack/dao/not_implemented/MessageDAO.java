package slack.dao.not_implemented;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class MessageDAO
 */
@Stateless
@LocalBean
public class MessageDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public MessageDAO() {
        // TODO Auto-generated constructor stub
    }

}
