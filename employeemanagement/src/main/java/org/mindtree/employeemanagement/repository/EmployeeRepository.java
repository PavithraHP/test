package org.mindtree.employeemanagement.repository;

import org.mindtree.employeemanagement.model.EmployeeBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeBean, Integer> {
	EmployeeBean findEmployeeBeanByUserNameAndPassword(String userName,String password);
}
