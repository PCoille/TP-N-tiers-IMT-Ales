package net.coille.imt.slack.clone.slack.beans.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.coille.imt.slack.clone.slack.dao.entities.CompanyDAOImpl;
import net.coille.imt.slack.clone.slack.entities.Company;

/**
 * Session Bean implementation class CompanyServiceImpl
 */
@Stateless
@LocalBean
public class CompanyServiceImpl implements CompanyServiceRemote {

	@EJB
	CompanyDAOImpl simpleDao;

	
	@Override
	public int createCompany(Company company) {
		return simpleDao.persist(company);
	}

	@Override
	public void updateCompany(Company company) {
		simpleDao.update(company);
	}

	@Override
	public Company findCompany(int id) {
		return simpleDao.find(id);
	}

	@Override
	public void deleteCompany(Company company) {
		simpleDao.delete(company.getId());
	}

	@Override
	public void deleteCompany(int id) {
		simpleDao.delete(id);
	}

	@Override
	public List<Company> findAllCompanies() {
		return simpleDao.findAll();
	}

}
