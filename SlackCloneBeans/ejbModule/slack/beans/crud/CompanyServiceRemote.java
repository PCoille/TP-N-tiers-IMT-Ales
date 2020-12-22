package slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import slack.entities.Channel;
import slack.entities.Company;

@Remote
public interface CompanyServiceRemote {
	void createCompany(Company company);
	void updateCompany(Company company);
	Company findCompany(int id);
	void deleteCompany(Company company);
	void deleteCompany(int id);
	List<Company> findAllCompanies();

}
