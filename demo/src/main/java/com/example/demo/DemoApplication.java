package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.model.Employee;
import com.example.demo.service.CrudService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		
		
		CrudService crudService = new CrudService();
		Employee employee= new Employee();
		ApplicationContext context =SpringApplication.run(DemoApplication.class, args);
		// CRUD operations via terminal

		while (true) {
			System.out.println("\n Welcome \n " + "Please Enter Your Choice: \n " + "1. Create New Employee \n " + "2. Get All Employees \n "
					+ "3. Update Employee Details \n " + "4. Delete an Employee \n "+ "5. Remove all Employee \n ");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Create operation selected.");
				// Add create logic here
				System.out.println("\n Enter Employee Details:\n ");
				System.out.println("\n Enter Employee Name: ");
				employee.setEmployeeName(scanner.nextLine());
				System.out.println("\n Enter Employee ID: ");
				employee.setEmployeeId(scanner.next());
				System.out.println("\n Enter Employee Contacts: ");
				employee.setContacts(scanner.next());
				System.out.println("\n Enter Employee Gender :");
				employee.setGender(scanner.next());
				System.out.println("\n Enter Employee Email-Id : ");
				employee.setEmail(scanner.next());
				System.out.println("\n Enter Employee Address : ");
				employee.setAddress(scanner.next());
				System.out.println("\n Enter Employee Salary : ");
                employee.setSalary(scanner.next());
				System.out.println("\n Enter Employee Department : ");
				employee.setDepartment(scanner.next());
				crudService.create(employee);
				break;
			case 2:
				System.out.println("Get all employees");
				// Add read logic here
				 crudService.getAllEmployees();
				break;
			case 3:
				Scanner sc = new Scanner(System.in);
				System.out.println("\nUpdate operation selected.");
				// Add update logic here
				System.out.println("Enter  Your Employee ID to update: ");
                employee.setEmployeeId(sc.next());
         
                System.out.println("Enter New Contacts: ");
                employee.setContacts(sc.next());
                
				System.out.println("Enter Employee Email-Id: ");
				employee.setEmail(sc.next());
				crudService.update(employee);
				break;
			case 4:
				System.out.println("Delete operation selected.");
			
				// Add delete logic here
				System.out.println("Enter Employees Id to delete : ");
				 employee.setEmployeeId(scanner.next());
				crudService.delete(employee);
				break;
			case 5:
				 System.out.println("Delete all operations.");
				 crudService.remove(employee);
				 break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");

				continue;
			}
		}
	}

}
