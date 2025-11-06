package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import com.example.demo.database.Storage;
import com.example.demo.model.Employee;

@Service
public class CrudService {

	private final Storage storage = new Storage();

	// Create Employees
	public void create(Employee employee) throws SQLException {
		if (employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
			throw new IllegalArgumentException("Employee ID cannot be null or empty");
		} else if (!employee.getEmployeeName().matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Invalid name! Only letters (A–Z or a–z) and spaces are allowed.");
		}

		// Validate contact number
		else if (!employee.getContacts().matches("\\d{10}")) {
			throw new IllegalArgumentException(
					"Invalid contact number! Must contain exactly 10 digits (no letters or symbols).");
		}

		else if (!employee.getGender().equalsIgnoreCase("M") && !employee.getGender().equalsIgnoreCase("F")) {
			throw new IllegalArgumentException("Invalid gender! Enter only 'M' for Male or 'F' for Female.");
		}

		else if (!employee.getEmail().endsWith("@gmail.com")) {
			throw new IllegalArgumentException("Invalid email! It must end with '@gmail.com'.");
		}

		else if (!employee.getAddress().matches("[a-zA-Z0-9, ]+")) {
			throw new IllegalArgumentException(
					"Invalid address! Only letters, digits, commas, and spaces are allowed.");
		}

		else if (!String.valueOf(employee.getSalary()).matches("[0-9,\\.]+")) {
			throw new IllegalArgumentException("Invalid salary! Only digits, commas, and a decimal point are allowed.");
		} else if (!employee.getDepartment().matches("[a-zA-Z.]+")) {
			throw new IllegalArgumentException("Invalid department! Only letters (A–Z or a–z) are allowed.");
		}
		// If everything is valid → create employee record
		else {
			storage.create(employee);
			System.out.println(" Employee data stored successfully.");
		}
	}

	public void getAllEmployees() {
		storage.getAllEmployees();
	}

	public void update(Employee employee) throws Exception {
		
		// 1. Check if the ID is null or empty
		if (employee.getEmployeeId() == null || employee.getEmployeeId().trim().isEmpty()) {
			throw new IllegalArgumentException("Employee ID cannot be null or empty");
		}
		if (employee.getContacts() == null || employee.getContacts().trim().isEmpty()) {
			throw new IllegalArgumentException("Employee  contacts cannot be null.");
		} else if (!employee.getContacts().matches("\\d{10}")) {
			throw new IllegalArgumentException(
					"Invalid contact number! Must contain exactly 10 digits (no letters or symbols).");
		}
		if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException(" Employee email cannot be null.");
		} else if (!employee.getEmail().endsWith("@gmail.com")) {
			throw new IllegalArgumentException("Invalid email! It must end with '@gmail.com'.");
		}
		storage.update(employee);
	 }

	public void delete(Employee employee) throws Exception {

		storage.delete(employee);
	}

	public void remove(Employee employee) throws SQLException {
		// empty the DB
		storage.removeALL(employee);
	}

}