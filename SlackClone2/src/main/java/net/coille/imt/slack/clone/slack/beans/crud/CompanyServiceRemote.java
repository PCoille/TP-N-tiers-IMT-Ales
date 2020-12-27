package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.Remote;

import net.coille.imt.slack.clone.slack.entities.Company;

@Remote
public interface CompanyServiceRemote {
	int createCompany(Company company);
	void updateCompany(Company company);
	Company findCompany(int id);
	void deleteCompany(Company company);
	void deleteCompany(int id);
	List<Company> findAllCompanies();

}
