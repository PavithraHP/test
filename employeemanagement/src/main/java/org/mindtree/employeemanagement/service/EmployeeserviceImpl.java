package org.mindtree.employeemanagement.service;

import java.util.Collection;

import org.mindtree.employeemanagement.model.EmployeeBean;
import org.mindtree.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "EmployeeService")
@CacheConfig
public class EmployeeserviceImpl implements EmployeeService {
    
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Cacheable("employee.all")
	public Collection<EmployeeBean> findAll() {
		
		Collection<EmployeeBean> EmployeesDetails = (Collection<EmployeeBean>) employeeRepository.findAll();
		return EmployeesDetails;
	}

	@Override
	@Cacheable("employee.byId")
	public EmployeeBean  findOne(int id) {
		EmployeeBean employee = employeeRepository.findById(id).get();

		return employee;
	}
	

//	@Override
//	@Transactional
//	public EmployeeBean create(EmployeeBean employee) {
//		EmployeeBean createdEmployee = employeeRepository.save(employee);
//		return createdEmployee;
//	}

	@Override
	@Transactional
	public boolean delete(int id) {
		employeeRepository.deleteById(id);
		return true;
	}

	@Override
	public EmployeeBean checkLogin(String userName, String password) {

		EmployeeBean employee = employeeRepository.findEmployeeBeanByUserNameAndPassword(userName, password);
		return employee;

	}
	
	
		@Override
			public EmployeeBean create(EmployeeBean employee) {
			EmployeeBean employeeTemp;
				System.out.println("looping going to create 1000 entry of employee");
				for (int i = 1; i < 10000; i++) {
					System.out.println("looping going to create 1000 entry of employee" + i);

					employeeTemp = new EmployeeBean();
					employeeTemp.setDateOfBirth(employee.getDateOfBirth());
					employeeTemp.setFullName(employee.getFullName() + i);
					employeeTemp.setUserName(employee.getUserName() + i);
					employeeTemp.setEmailId(employee.getEmailId() + i);
					employeeTemp.setGender(employee.getGender());
					employeeTemp.setPassword(employee.getPassword() + 1);
					employeeTemp.setSecurityAnswer(employee.getSecurityAnswer());
					employeeTemp.setSecurityQuestion(employee.getSecurityQuestion());
					employeeRepository.save(employeeTemp);

				}
				System.out.println("looping going to create 1000 entry of employee and it's complited");
				return employee;
			} 
		 



}
