package com.shakti.springquerydsl.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shakti.springquerydsl.model.Department;
import com.shakti.springquerydsl.model.Employee;
import com.shakti.springquerydsl.service.EmployeeService;

@RestController
@Transactional
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee.getName(), employee.getSalary(), employee.getDepartment());	
	}
	
	@RequestMapping(value="/departments", method=RequestMethod.POST)
	public Department createDepartment(@RequestBody Department department) {
		return employeeService.saveDepartment(department.getName());	
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();		
	}
	
	@RequestMapping(value="/employeeNames", method=RequestMethod.GET)
	public List<String> getEmployeeNames() {
		return employeeService.getEmployeeNames();		
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@RequestMapping(value="/employees/by_salary", method=RequestMethod.GET)
	public List<Employee> getEmployeesBySalary(@RequestParam BigDecimal minSalary, @RequestParam BigDecimal maxSalary) {		
		return employeeService.getEmployeesWithSalaryBetween(minSalary, maxSalary);
	}
	
	@RequestMapping(value="/employees/by_department_name", method=RequestMethod.GET)
	public List<Employee> getEmployeesByDepartmentName(@RequestParam String name) {		
		return employeeService.getEmployeesByDepartmentName(name);
	}
}
