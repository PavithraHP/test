package org.mindtree.employeemanagement.service;

import java.util.Collection;

import org.mindtree.employeemanagement.model.EmployeeBean;

public interface EmployeeService {
	
	
	EmployeeBean create(EmployeeBean employee);
	
	boolean delete(int id);
	
	Collection<EmployeeBean> findAll();

	EmployeeBean findOne(int id);
	
	EmployeeBean checkLogin(String userName, String password);



}
