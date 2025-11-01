package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.service.CrudService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		
		
		CrudService crudService = new CrudService();
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
				Scanner detailScanner = new Scanner(System.in);
				System.out.println("\n Enter Employee Name: ");
				//detailScanner.nextLine();
				String employeeName = detailScanner.nextLine();
				System.out.println("\n Enter Employee ID: ");
				String employeeId = detailScanner.next();
				System.out.println("\n Enter Employee Contacts: ");
				String contacts = detailScanner.next();
				System.out.println("\n Enter Employee Gender :");
                String gender = detailScanner.next();
				System.out.println("\n Enter Employee Email-Id : ");
				String email = detailScanner.next();
				System.out.println("\n Enter Employee Address : ");
				detailScanner.nextLine();
				String address = detailScanner.nextLine();
				System.out.println("\n Enter Employee Salary : ");
                String salary = detailScanner.next();
				System.out.println("\n Enter Employee Department : ");
				String dept = detailScanner.next();
				crudService.create(employeeName, employeeId, contacts, gender, email, address, salary, dept);
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
                String updateId = sc.next();
         
                System.out.println("Enter New Contacts: ");
                String newContacts = sc.next();
                
				System.out.println("Enter Employee Email-Id: ");
				String newEmail = sc.next();
				crudService.update(updateId, newEmail, newContacts);
				break;
			case 4:
				System.out.println("Delete operation selected.");
				// Add delete logic here
				System.out.println("Enter Employees Id to delete : ");
				String deleteId = scanner.next();
				crudService.delete(deleteId);
				break;
			case 5:
				 System.out.println("Delete all operations.");
				 crudService.remove();
				 break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");

				continue;
			}
		}
	}

}
