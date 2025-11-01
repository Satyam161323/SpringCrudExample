package com.example.demo.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class Storage {

	private List<Map<String, String>> db = new ArrayList<Map<String, String>>();

	public boolean existsEmp(String employeeDetails) {
		Map<String, String> record = new HashMap<String, String>();

		for (int i = 0; i < db.size(); i++) {
			record = db.get(i);
			if (record.containsValue(employeeDetails))
				return true;
		}
		return false;
	}


	// Save Employees
	public void create(String employeeName, String employeeId, String employeeContacts, String gender, String email,
			String address, String salary, String dept) {
		Map<String, String> insert = new HashMap<String, String>();

		insert.put("EmployeeId:", employeeId);
		insert.put("EmployeeName:", employeeName);
		insert.put("EmployeeContacts:", employeeContacts);
		insert.put("EmployeeGender:", gender);
		insert.put("EmployeeEmail:", email);
		insert.put("EmployeeAddress:", address);
		insert.put("EmployeeSalary:", salary);
		insert.put("EmployeeDept:", dept);
		db.add(insert);
		System.out.println("Data stored successfully.\n" + db + "\n");
	}

	public void getAllEmployees() {
		
		if (db.isEmpty()|| db==null|| db.size()==0) {
			System.out.println("No employees found.");
		}
		else
		System.out.println(db);
	}

	public void delete(String employeeId) {
		boolean removed = false;

		for (int i = 0; i < db.size(); i++) {
			Map<String, String> record = db.get(i);
			if (record.containsValue(employeeId)) {
				db.remove(i);
				removed = true;
				System.out.println("Employee deleted successfully. Deleted employeeId: " + employeeId);
				break; // Important: break to avoid IndexOutOfBounds after remove
			}
		}

		if (!removed) {
			System.out.println("Employee with ID " + employeeId + " not found.\n");
		}
	}

	public void removeAll() {
		db = null;
		System.out.println("All employees removed successfully.\n");

	}

	public void update(String employeeId, String employeeContacts, String employeeEmail) throws Exception {
		Map<String, String> record = new HashMap<String, String>();
		boolean empIdNotFound = false;
		for (int i = 0; i < db.size(); i++) {
			record = db.get(i);
			if (!record.containsValue(employeeId)) {
				empIdNotFound = true;
			} else {
				empIdNotFound = false;
				if (record.containsValue(employeeContacts) && record.containsValue(employeeEmail)) {
					System.out.println("Email and Contact already exists.");
				} else {
					record.put("EmployeeContacts:", employeeContacts);
					record.put("EmployeeEmail:", employeeEmail);
					System.out.println("Employee details Updated.");
					break;
				}
			}
		}
		if (empIdNotFound == true)
			System.out.println("EmployeeId Doesnt Exists.");
	}

}