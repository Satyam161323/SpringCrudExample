package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import com.example.demo.database.Storage;

@Service
public class CrudService {

	private final Storage storage = new Storage();

	// Create Employees
	public void create(String employeeName, String employeeId, String employeeContacts, String gender, String email,
			String address, String salary, String dept) throws SQLException {
		if (employeeId == null || employeeId.isEmpty()) {
			throw new IllegalArgumentException("Employee ID cannot be null or empty");
		} else if (!employeeName.matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Invalid name! Only letters (A–Z or a–z) and spaces are allowed.");
		}

		// Validate contact number
		else if (!employeeContacts.matches("\\d{10}")) {
			throw new IllegalArgumentException(
					"Invalid contact number! Must contain exactly 10 digits (no letters or symbols).");
		}

		else if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
			throw new IllegalArgumentException("Invalid gender! Enter only 'M' for Male or 'F' for Female.");
		}

		else if (!email.endsWith("@gmail.com")) {
			throw new IllegalArgumentException("Invalid email! It must end with '@gmail.com'.");
		}

		else if (!address.matches("[a-zA-Z0-9, ]+")) {
			throw new IllegalArgumentException(
					"Invalid address! Only letters, digits, commas, and spaces are allowed.");
		}

		else if (!String.valueOf(salary).matches("[0-9,\\.]+")) {
			throw new IllegalArgumentException("Invalid salary! Only digits, commas, and a decimal point are allowed.");
		} else if (!dept.matches("[a-zA-Z.]+")) {
			throw new IllegalArgumentException("Invalid department! Only letters (A–Z or a–z) are allowed.");
		}
		// If everything is valid → create employee record
		else {
			storage.create(employeeName, employeeId, employeeContacts, gender, email, address, salary, dept);
			System.out.println(" Employee data stored successfully.");
		}
	}

	public void getAllEmployees() {
		storage.getAllEmployees();
	}

	public void update(String employeeId, String newEmail, String newContacts) throws Exception {
		
		// 1. Check if the ID is null or empty
		if (employeeId == null || employeeId.trim().isEmpty()) {
			throw new IllegalArgumentException("Employee ID cannot be null or empty");
		}
		if (newContacts == null || newContacts.trim().isEmpty()) {
			throw new IllegalArgumentException("Employee  contacts cannot be null.");
		} else if (!newContacts.matches("\\d{10}")) {
			throw new IllegalArgumentException(
					"Invalid contact number! Must contain exactly 10 digits (no letters or symbols).");
		}
		if (newEmail == null || newEmail.trim().isEmpty()) {
			throw new IllegalArgumentException(" Employee email cannot be null.");
		} else if (!newEmail.endsWith("@gmail.com")) {
			throw new IllegalArgumentException("Invalid email! It must end with '@gmail.com'.");
		}
		storage.update(employeeId, newContacts, newEmail);
	 }

	public void delete(String employeeId) {

		storage.delete(employeeId);
	}

	public void remove() {
		// empty the DB
		storage.removeAll();
	}

}