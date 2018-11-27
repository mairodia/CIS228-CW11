// File name: CreateEmployeesDB.java
// Written by: Jennifer King
// Date: 11/26/2018

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEmployeesDB
{
	public static void main(String[] args)
	{
		final String DB_URL = "jdbc:derby:EmployeeDB;create=true";
      
		try
		{
			Connection conn =
                DriverManager.getConnection(DB_URL);
			dropTables(conn);
			buildEmployeesTable(conn);
			conn.close();
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	}

	public static void dropTables(Connection conn)
	{
		System.out.println("Checking for existing tables.");
		
		try
		{
			Statement stmt  = conn.createStatement();;

			try
			{
	         stmt.execute("DROP TABLE Employees");
				System.out.println("Employees table dropped.");
			}
			catch(SQLException ex)
			{
				// do nothing
			}
		}
  		catch(SQLException ex)
		{
  			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	

	public static void buildEmployeesTable(Connection conn)
	{
		try
		{
			Statement stmt = conn.createStatement();
         
			stmt.execute("CREATE TABLE Employees (" +
   				      "socialSecurityNumber VARCHAR(30) NOT NULL PRIMARY KEY, " +
                      "firstName VARCHAR(30) NOT NULL, " +
                      "lastName VARCHAR(30) NOT NULL, " +
                      "weeklySalary REAL NOT NULL, " +
                      "birthday DATE NOT NULL, " +
                      "employeeType VARCHAR(30) NOT NULL, " +
                      "departmentName VARCHAR(30) NOT NULL " +
                      ")");
							 
			stmt.execute("INSERT INTO Employees VALUES ( " +
                      "'111-11-1111', " +
                      "'John', " +
                      "'Smith', " +
                      "1000.5, " +
                      "'1945-01-02', " +
                      "'salariedEmployee', " +
                      "'R&D' )");

			stmt.execute("INSERT INTO Employees VALUES ( " +
                      "'222-22-2222', " +
                      "'Sue', " +
                      "'Jones', " +
                      "865.0, " +
                      "'1961-02-03', " +
                      "'commissionEmployee', " +
                      "'SALES' )");

			stmt.execute("INSERT INTO Employees VALUES ( " +
                      "'333-33-3333', " +
                      "'Bob', " +
                      "'Lowis', " +
                      "950.25, " +
                      "'1958-10-05', " +
                      "'basePlusEmployee', " +
                      "'SALES' )");

			stmt.execute("INSERT INTO Employees VALUES ( " +
                      "'444-44-4444', " +
                      "'Karen', " +
                      "'Price', " +
                      "1100.15, " +
                      "'1972-05-25', " +
                      "'salariedEmployee', " +
                      "'HR' )");
							 
			System.out.println("Employees table created.");
		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}	
