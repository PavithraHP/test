package org.mindtree.employeemanagement.model;

import java.util.Collection;

public class ResponseDetails {

	private String status;
	private String message;
	private String statusCode;

	Collection<EmployeeBean> employees;

	public ResponseDetails(String status, String message, String statusCode, Collection<EmployeeBean> employees) {
		super();
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
		this.employees = employees;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Collection<EmployeeBean> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<EmployeeBean> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "ResponseDetails [status=" + status + ", message=" + message + ", statusCode=" + statusCode
				+ ", employees=" + employees + "]";
	}

}
