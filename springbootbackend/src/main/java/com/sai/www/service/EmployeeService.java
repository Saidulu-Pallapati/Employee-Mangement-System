package com.sai.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sai.www.exception.ResourceNotFoundException;
import com.sai.www.model.Employee;
import com.sai.www.repo.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeerepository;

	public List<Employee> getAllEmployees()
	{
		return employeerepository.findAll();
	}
	public Employee createEmployee(Employee employee) {
		return employeerepository.save(employee);
	}
	public Employee getEmployeeById(Long id)
	{
		return employeerepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id not Found"));
	}

	public ResponseEntity<Employee> updateEmployee( Long id, Employee employee)
	{
		Employee emp= employeerepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id not Found"));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		Employee updateEmp=employeerepository.save(employee);
		return ResponseEntity.ok(updateEmp);
	}
	public ResponseEntity<HttpStatus> deleteEmployee(Long id)
	{
		Employee employee=employeerepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Does not Exit"));
		employeerepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}