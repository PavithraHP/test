package org.mindtree.employeemanagement.Service;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindtree.employeemanagement.model.EmployeeBean;
import org.mindtree.employeemanagement.service.EmployeeserviceImpl;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

    @Autowired
	private EmployeeserviceImpl employeeserviceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {

		Collection<EmployeeBean> list = employeeserviceImpl.findAll();
		Assert.assertNotNull("failure- expected not null", list);
	}

	@Test
	public void testFindOne() {
		int id = 1;
		EmployeeBean employee = employeeserviceImpl.findOne(id);
		Assert.assertNotNull("failure-expected not null", employee);
		Assert.assertEquals("failure-expected id method", id, employee.getId());

	}
	
	@Test
	@Rollback
	public void testCreat() {
		EmployeeBean employee = new EmployeeBean();
		employee.setId(4);
		;
		employee.setEmailId("game@gmail.com");
		employee.setFullName("rupesh m");
		employee.setGender("Male");
		employee.setPassword("gate");
		employee.setSecurityAnswer("m");
		employee.setSecurityAnswer("last name?");
		employee.setUserName("avi");
		employee.setDateOfBirth("1992-02-10");
		EmployeeBean createdEmployee = employeeserviceImpl.create(employee);
		Assert.assertNotNull("failure-expected not null", createdEmployee);

	}

	@Test
	@Rollback
	public void testDelete() {
		int id = 3;
		employeeserviceImpl.delete(id);
		Collection<EmployeeBean> list = employeeserviceImpl.findAll();
		Assert.assertEquals("failure-expected size", 3, list.size());

	}

	@Test

	public void testCheckLogin() {
		String userName = "udayrana";
		String password = "gate";
		EmployeeBean employee = employeeserviceImpl.checkLogin(userName, password);
		Assert.assertNotNull("failure-expected entity not null", employee);
		Assert.assertEquals("failure-expected attributed username is equal", userName, employee.getUserName());
		Assert.assertEquals("failure-expected attributed password is equal", userName, employee.getUserName());

	}
	
	 
	 
}
