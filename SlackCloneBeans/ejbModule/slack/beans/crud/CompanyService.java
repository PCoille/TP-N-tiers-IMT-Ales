package slack.beans.crud;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import slack.entities.Company;

/**
 * Session Bean implementation class CompanyService
 */
@Stateless
@LocalBean
public class CompanyService extends GenericCrudService<Company> implements CompanyServiceRemote {

	@Override
	public void createCompany(Company company) {
		this.create(company);
	}

	@Override
	public void updateCompany(Company company) {
		this.update(company);
	}

	@Override
	public Company findCompany(int id) {
		return this.find(id);
	}

	@Override
	public void deleteCompany(Company company) {
		this.delete(company);
	}

	@Override
	public void deleteCompany(int id) {
		this.delete(id);
	}

	@Override
	public List<Company> findAllCompanies() {
		return this.findAll();
	}

}
