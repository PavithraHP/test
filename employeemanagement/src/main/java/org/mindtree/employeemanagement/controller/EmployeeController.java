package org.mindtree.employeemanagement.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.mindtree.employeemanagement.model.EmployeeBean;
import org.mindtree.employeemanagement.model.ResponseDetails;
import org.mindtree.employeemanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;




@Api
@RestController
@RequestMapping("/EmpMgt")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
  
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/getAllEmpDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDetails getAllEmpDetails() {
		System.out.println("==Hello==");
		try {
			Collection<EmployeeBean> employees = employeeService.findAll();

			if (!employees.isEmpty()) {
				return new ResponseDetails("Success ", "Success ", "200", employees);

			} else {
				return new ResponseDetails("failure", "No Employee found", "200", employees);
			}
		}

		catch (Exception e) {
			return new ResponseDetails("failure", "Exception while fetching recore from db", "400", null);
		}

	}
	
	@RequestMapping(value = "/getByEmpId/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDetails getByEmpId(@PathVariable("empId") int empId) {
		Collection<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		try {
			EmployeeBean employee = employeeService.findOne(empId);
			if (employee != null) {
				employees.add(employee);
				return new ResponseDetails("Success ", "[]", "200 ", employees);
			} else {
				return new ResponseDetails("failure", "Input data mismatch", "400", employees);
			}

		} catch (Exception e) {
			return new ResponseDetails("failure", "There is some issue at server side. Please check the log", "500",
					employees);
		}

	}
	
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDetails addEmp(@RequestBody EmployeeBean employee) {
		Collection<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		try {
			employeeService.create(employee);
			return new ResponseDetails("Success ", "Employee data inserted successfully", "200 ", employees);

		} catch (Exception e) {
			return new ResponseDetails("failure", "Input data not inserted", "400", employees);
		}

	}
	
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDetails checkLogin(@RequestBody EmployeeBean employee) {
		Collection<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		String userName = employee.getUserName();
		String password = employee.getPassword();
		EmployeeBean findEmployee = null;
		findEmployee = employeeService.checkLogin(userName, password);
		if (findEmployee != null) {
			employees.add(findEmployee);
			return new ResponseDetails("Success", "Employee has authenticated successfully", "200", employees);
		} else {
			return new ResponseDetails("Success", "Invalid Username and Password", "302", employees);

		}
	}

	@RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDetails deleteEmp(@PathVariable("empId") int id) {
		try {
			boolean action = employeeService.delete(id);
			if (action)
				return new ResponseDetails("Success", "Employee data deleted successfully", "200", null);
			else
				return new ResponseDetails("failure", "Input data mismatch emp data not deleted", "400", null);

		} catch (Exception e) {
			return new ResponseDetails("failure", "Input data mismatch", "400", null);
		}

	}
}


