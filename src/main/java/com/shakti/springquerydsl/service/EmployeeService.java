package com.shakti.springquerydsl.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.jpa.impl.JPAQuery;
import com.shakti.springquerydsl.model.Department;
import com.shakti.springquerydsl.model.Employee;
import com.shakti.springquerydsl.model.QEmployee;

@Service
public class EmployeeService {
	
	@Autowired
	EntityManager em;
	
	public Employee saveEmployee(String name, BigDecimal salary, Department department) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSalary(salary);
		employee.setDepartment(department);

		employee = em.merge(employee);
		return employee;
	}

	public Department saveDepartment(String name) {
		Department department = new Department();
		department.setName(name);

		department = em.merge(department);
		return department;
	}
	
	public List<Employee> getEmployees() {
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(em).from(qEmployee);
		
		return query.list(qEmployee);
	}
	
	public List<String> getEmployeeNames() {
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(em).from(qEmployee);
		
		return query.list(qEmployee.name);
	}
	
	public Employee getEmployeeById(long id) {
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(em).from(qEmployee);
		query.where(qEmployee.id.eq(id));
		
		return query.singleResult(qEmployee);
	}
	
	public List<Employee> getEmployeesWithSalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(em).from(qEmployee);
		query.where(qEmployee.salary.between(minSalary, maxSalary));
		
		return query.list(qEmployee);
	}
	
	public List<Employee> getEmployeesByDepartmentName(String departmentName) {
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(em).from(qEmployee);
		query.where(qEmployee.department.name.eq(departmentName));
		
		return query.list(qEmployee);
	}
}
