package org.mindtree.employeemanagement.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindtree.employeemanagement.model.EmployeeBean;
import org.mindtree.employeemanagement.service.EmployeeserviceImpl;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("deprecation")
@Transactional
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeserviceImpl Service;

	@Test
	public void getAllEmployeeDetailsSuccessSenario() throws Exception {

		String expected = "{\"status\":\"Success \",\"message\":\"Success \",\"statusCode\":\"200\",\"employees\":[{\"id\":1,\"userName\":\"udayrana\",\"password\":\"gate\",\"fullName\":\"uday kumar\",\"emailId\":\"er.kumaruday@gmail.com\",\"dateOfBirth\":\"1992-02-10\",\"gender\":\"male\",\"securityQuestion\":\"first name?\",\"securityAnswer\":\"uday\"},{\"id\":2,\"userName\":\"kavi kumar\",\"password\":\"gate\",\"fullName\":\"kavi kumar\",\"emailId\":\"er.kavi@gmail.com\",\"dateOfBirth\":\"1984-02-10\",\"gender\":\"male\",\"securityQuestion\":\"first name?\",\"securityAnswer\":\"uday\"}]} ";
		Collection<EmployeeBean> employees = new ArrayList<>();
		EmployeeBean employee1 = new EmployeeBean();
		employee1.setId(1);
		employee1.setUserName("udayrana");
		employee1.setEmailId("er.kumaruday@gmail.com");
		employee1.setDateOfBirth("1992-02-10");
		employee1.setFullName("uday kumar");
		employee1.setGender("male");
		employee1.setPassword("gate");
		employee1.setSecurityQuestion("first name?");
		employee1.setSecurityAnswer("uday");

		EmployeeBean employee2 = new EmployeeBean();
		employee2.setId(2);
		employee2.setUserName("kavi kumar");
		employee2.setEmailId("er.kavi@gmail.com");
		employee2.setDateOfBirth("1984-02-10");
		employee2.setFullName("kavi kumar");
		employee2.setGender("male");
		employee2.setPassword("gate");
		employee2.setSecurityQuestion("first name?");
		employee2.setSecurityAnswer("uday");

		employees.add(employee1);
		employees.add(employee2);

		Mockito.when(Service.findAll()).thenReturn(employees);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
				.andExpect(content().json(expected));

	}

	@Test
	public void getAllEmployeeDetailsNoEmployeeFound() throws Exception {

		Mockito.when(Service.findAll()).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
				.andExpect(content().json(
						"{\"status\":\"failure\",\"message\":\"Exception while fetching recore from db\",\"statusCode\":\"400\",\"employees\":null}"));

	}

	@Test
	public void getEmployeeByIdSuccessSenario() throws Exception {
		String expected = "{\"status\":\"Success \",\"message\":\"[]\",\"statusCode\":\"200 \",\"employees\":[{\"id\":1,\"userName\":\"udayrana\",\"password\":\"gate\",\"fullName\":\"uday kumar\",\"emailId\":\"er.kumaruday@gmail.com\",\"dateOfBirth\":\"1992-02-10\",\"gender\":\"male\",\"securityQuestion\":\"first name?\",\"securityAnswer\":\"uday\"}]}";
		EmployeeBean employee = new EmployeeBean();
		employee.setId(1);
		employee.setUserName("udayrana");
		employee.setEmailId("er.kumaruday@gmail.com");
		employee.setDateOfBirth("1992-02-10");
		employee.setFullName("uday kumar");
		employee.setGender("male");
		employee.setPassword("gate");
		employee.setSecurityQuestion("first name?");
		employee.setSecurityAnswer("uday");

		Mockito.when(Service.findOne(Matchers.anyInt())).thenReturn(employee);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/1")).andExpect(status().isOk())
				.andExpect(content().json(expected));
	}

	@Test
	public void getEmployeeByIdNoEmployeeFound() throws Exception {
		String expected = "{\"status\": \"failure\", \"message\": \"Input data mismatch\", \"statusCode\": \"400\", \"employees\":  []}";
		Mockito.when(Service.findOne((int) Matchers.anyLong())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/40")).andExpect(status().isOk())
				.andExpect(content().json(expected));
	}

	@Test
	@Rollback
	public void deleteEmployeeByIdSuccessSenario() throws Exception {
		String expected = "{\"status\": \"Success\",\"message\": \"Employee data deleted successfully\", \"statusCode\": \"200\",\"employees\": null}";

		Mockito.when(Service.delete(Matchers.anyInt())).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/1")).andExpect(status().isOk())
				.andExpect(content().json(expected));
	}

	@Test
	@Rollback
	public void deleteEmployeeByIdFailScenario() throws Exception {
		String expected = "{\"status\":\"failure\",\"message\":\"Input data mismatch emp data not deleted\",\"statusCode\":\"400\",\"employees\":null}";

		Mockito.when(Service.delete((int) Matchers.anyLong())).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/99")).andExpect(status().isOk())
				.andExpect(content().json(expected));
	}

	@Test
	public void checkLoginSuccessSenario() throws Exception {
		EmployeeBean employee = new EmployeeBean();
		employee.setId(1);
		employee.setUserName("udayrana");
		employee.setEmailId("er.kumaruday@gmail.com");
		employee.setDateOfBirth("1992-02-10");
		employee.setFullName("uday kumar");
		employee.setGender("male");
		employee.setPassword("gate");
		employee.setSecurityQuestion("first name?");
		employee.setSecurityAnswer("uday");

		// String expected = "{\"status\":\"Success\",\"message\":\"Employee has
		// authenticated
		// successfully\",\"statusCode\":\"200\",\"employees\":[{\"empId\":1,\"userName\":\"udayrana\",\"password\":\"gate\",\"fullName\":\"uday
		// kumar\",\"emailId\":\"er.kumaruday@gmail.com\",\"dateOfBirth\":\"1992-02-10\",\"gender\":\"male\",\"securityQuestion\":\"first
		// name?\",\"securityAnswer\":\"uday\"}]}";
		String input = "{\"userName\":\"udayrana\",\"password\":\"gate\"}";
		String expected = "{\"status\":\"Success\",\"message\":\"Employee has authenticated successfully\",\"statusCode\":\"200\",\"employees\":[{\"id\":1,\"userName\":\"udayrana\",\"password\":\"gate\",\"fullName\":\"uday kumar\",\"emailId\":\"er.kumaruday@gmail.com\",\"dateOfBirth\":\"1992-02-10\",\"gender\":\"male\",\"securityQuestion\":\"first name?\",\"securityAnswer\":\"uday\"}]}";
		Mockito.when(Service.checkLogin(Matchers.anyString(), Matchers.anyString())).thenReturn(employee);

		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful())
				.andExpect(content().json(expected));
	}

	@Test
	public void checkLoginFailSenario() throws Exception {
		String expected = "{\"status\":\"Success\",\"message\":\"Invalid Username and Password\",\"statusCode\":\"302\",\"employees\":[]}";
		String input = "{ \"userName\": \"udayrna\",\"password\": \"gate\"}";
		Mockito.when(Service.checkLogin(Matchers.anyString(), Matchers.anyString())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input)).andExpect(status().isOk()).andExpect(content().json(expected));

	}

	@Test
	public void addEmployeeSuccessScenario() throws Exception {
		String expected = "{ \"status\": \"Success \", \"message\": \"Employee data inserted successfully\", \"statusCode\": \"200 \", \"employees\": [] } ";
		String input = "{ \"userName\": \"rajesh rana\", \"password\": \"gate\", \"fullName\": \"Rajesh kumar\", \"emailId\": \"er.kumaruday@gmail.com\", \"dateOfBirth\": \"1992-02-10\", \"gender\": \"male\", \"securityQuestion\": \"first name?\", \"securityAnswer\": \"uday\" } ";

		Mockito.when(Service.create(Matchers.any(EmployeeBean.class))).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmp").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(input)).andExpect(status().isOk())
				.andExpect(content().json(expected));
	}
}
