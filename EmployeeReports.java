// File: EmployeeReports.java
// Written by: Jennifer King
// Date: 11/26/2018

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeReports
{
   public static void main(String[] args)
   {
      final String DB_URL = "jdbc:derby:EmployeeDB";
      
      try
      {
         Connection conn = DriverManager.getConnection(DB_URL);
         Statement stmt = conn.createStatement();
         String sqlStatement = "SELECT * FROM Employees";
         ResultSet result = stmt.executeQuery(sqlStatement);
         
         System.out.println("\t\t\t   Raw Data - All Employees");
         System.out.println("SSN\t    First      Last   Salary  Birthday    Type\t\t       Department");
         System.out.println("------------------------------------------------------------------------------------");
         
         while (result.next())
         {
            System.out.printf("%-10s %-10s %-6s %-7.1f %-11s %-20s %-10s\n",
                              result.getString("socialSecurityNumber"),
                              result.getString("firstName"),
                              result.getString("lastName"),
                              result.getFloat("weeklySalary"),
                              result.getString("birthday"),
                              result.getString("employeeType"),
                              result.getString("departmentName"));
         }
         conn.close();
      }
      catch(Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
      
      try
      {
         Connection conn = DriverManager.getConnection(DB_URL);
         Statement stmt = conn.createStatement();
         String sqlStatement = "SELECT firstName, lastName, employeeType FROM Employees WHERE (departmentName = 'SALES')";
         
         ResultSet result = stmt.executeQuery(sqlStatement);
         
         System.out.println("");
         System.out.println("      Employees in Department: Sales");
         System.out.println("First      Last       Type");
         System.out.println("-------------------------------------------");
         
         while (result.next())
         {
            System.out.printf("%-10s %-10s %-20s\n",
                              result.getString("firstName"),
                              result.getString("lastName"),
                              result.getString("employeeType"));
         }
         conn.close();
      }
      catch(Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
   }
}
