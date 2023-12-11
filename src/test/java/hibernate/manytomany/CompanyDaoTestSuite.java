package hibernate.manytomany;

import hibernate.mantomany.Company;
import hibernate.mantomany.Employee;
import hibernate.mantomany.dao.CompanyDao;
import hibernate.mantomany.dao.EmployeeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CompanyDaoTestSuite {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Test
	void testSaveManyToMany(){
		//given
		Employee johnSmith = new Employee("John", "Smith");
		Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
		Employee lindaKovalsky = new Employee("Linda", "Kovalsky");
		Company softwareMachine = new Company("Software Machine");
		Company dataMaesters = new Company("Data Maesters");
		Company greyMatter = new Company("Grey Matter");
		softwareMachine.getEmployees().add(johnSmith);
		dataMaesters.getEmployees().add(stephanieClarckson);
		dataMaesters.getEmployees().add(lindaKovalsky);
		greyMatter.getEmployees().add(johnSmith);
		greyMatter.getEmployees().add(lindaKovalsky);
		johnSmith.getCompanies().add(softwareMachine);
		johnSmith.getCompanies().add(greyMatter);
		stephanieClarckson.getCompanies().add(greyMatter);
		lindaKovalsky.getCompanies().add(dataMaesters);
		lindaKovalsky.getCompanies().add(greyMatter);
		//when
		companyDao.save(softwareMachine);
		companyDao.save(dataMaesters);
		companyDao.save(greyMatter);
		List<Employee> searchLastName = employeeDao.findAccordingToName("Smith");
		List<Company> searchFirstThreeLetters = companyDao.searchCompanyFirstThreeLetters();
		//then
		assertEquals(1,searchLastName.size());
		assertEquals(1,searchFirstThreeLetters.size());

		//cleanUp
	}
}
