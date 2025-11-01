package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

@Repository
public class Storage {

	private final String URL = "jdbc:mysql://localhost:3306/employee_db";
	private final String USER = "root";
	private final String PASSWORD = null;

	public Storage() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("✅ MySQL Driver Loaded Successfully");
		} catch (ClassNotFoundException e) {
			System.out.println("❌ MySQL Driver Not Found: " + e.getMessage());
		}
	}

	// Helper method to get connection
	private Connection getConnection()throws SQLException{
		return DriverManager.getConnection(URL,USER, PASSWORD);
			}
	
	// Create method
	public void create(String employeeName, String employeeId, String employeeContacts, String gender, String email,
			String address, String salary, String dept) throws SQLException{
		String query = "INSERT INTO employees(id, name, contacts,gender,email,address,salary,department)" + "VALUES(?,?,?,?,?,?,?,?)";
		
	    try (Connection con =getConnection(); PreparedStatement ps = con.prepareStatement(query)){
	    	ps.setString(1, employeeId);
	    	ps.setString(2, employeeName);
	    	ps.setString(3,  employeeContacts);
	    	ps.setString(4, gender);
	    	ps.setString(5, email);
	    	ps.setString(6, address);
	    	ps.setString(7, salary);
	    	ps.setString(8, dept);
	        int rows = ps.executeUpdate();
	        if(rows>0) {
	        	System.out.println("Employee Details  Addes Successfully.");
	        }
	        else
	        System.out.println("No Rows inserted.");
	     }
	    
	    catch(SQLException e) {
	    	System.out.println(" Error inserting employees ."+  e.getMessage());
	    }
}
	
	//Read Employee data
	public void getAllEmployees(){
		String query = "SELECT * FROM employees";
		try(Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query);ResultSet rs = pst.executeQuery()){
				
			System.out.println("Employees list:");
			while(rs.next()) {
				System.out.println("ID:" + rs.getString("id") + ",Name:" + rs.getString("name") +  ",Contacts:" + rs.getString("contacts") 
			                    	+ ", Gender:" + rs.getString("gender") + ",Email:" + rs.getString("email") + ",Address:"
						            + rs.getString("address") + ",Salary: " + rs.getString("salary") + ",Department:"+rs.getString("department"));
			}
			
			}
		catch(SQLException e) {
			System.out.println("Error Fetching Employees:" + e.getMessage());
		}
		}
	
	//UPDATE 
	public void update(String employeeId, String newContacts, String newEmail) throws SQLException {
		String selectQuery = "SELECT contacts, email FROM employees WHERE id=?";
		String updateQuery = "UPDATE employees SET contacts=?, email=? WHERE id=?";
		
		try (Connection con = getConnection(); PreparedStatement selectStmt = con.prepareStatement(selectQuery)) {
		//step 1: fetch current employee details
			  selectStmt.setString(1,employeeId);   
			  ResultSet rs = selectStmt.executeQuery();
			  
			  if(!rs.next()) {
				  System.out.println("Employee with Id" + employeeId + " not found");
				  return;   
			  }
			  String currentContacts = rs.getString("contacts");
			  String currentEmail = rs.getString("email");
			  
			  //compare new data with current data
			  if(currentContacts.equals(newContacts)&&currentEmail.equals(currentEmail)) {
				  System.out.println("No changes detected. Email and Contacts already same.");
				  return;
			  }
			  
			  try(PreparedStatement updateStmt = con.prepareStatement(updateQuery)){
				  updateStmt.setString(1, newContacts);
				  updateStmt.setString(2, newEmail);
				  updateStmt.setString(3, employeeId);
				  
				  int rows = updateStmt.executeUpdate();
				  if(rows>0) {
					  System.out.println("Employee details updated successfully!");
					  
				  }
				  else {
					  System.out.println("Failed to update employee details.");
				  }
			  }
			  
			  catch(SQLException e) {
				  System.out.println("Error updating employee."+e.getMessage());
			  }
			
		}
	}
		
		//Delete (Single Employees)
		public void delete(String employeeId) throws Exception {
			String query =  "DELETE FROM employee WHERE id=?";
			try(Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)){
				ps.setString(1, employeeId);
				int rows = ps.executeUpdate();
				
				if(rows>0) {
					System.out.println("Employee Deleted Successfully.");
				}
				else {
					System.out.println("Employee Id not found!");
				}
				
		}
			catch(SQLException e) {
				System.out.print("Error deleting employees:" + e.getMessage());
			}
	}
		
		//DELETE ALL
		
		public void removeALL() throws SQLException {
			String query = "DELETE FROM employees";
			try(Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)){
				int rows = ps.executeUpdate();
				System.out.println("Deleted " + rows + " employees from table." );
			}
			
			catch(SQLException e) {
				System.out.println("Error deleting all employees:" + e.getMessage());
			}
		}
	
}
