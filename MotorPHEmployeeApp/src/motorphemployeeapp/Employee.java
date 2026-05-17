/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Employee {
    
     //CSV file path
    static final String EMPLOYEE_FILE = "employee.csv";
    
    // These constants represent the column positions in the employee CSV file.
    static final int COL_EMP_NUM = 0;
    static final int COL_LAST_NAME = 1;
    static final int COL_FIRST_NAME = 2;
    static final int COL_BIRTHDAY = 3;
    static final int COL_HOURLY_RATE = 18;
    
    //These ArrayLists store the CSV contents after reading and loading the files.
    static ArrayList<String[]> employeeData = 
            new ArrayList<String[]>();
    
    // This method reads the employee CSV file once and stores all rows in a data storage.
    public static void loadEmployeeData() {
        employeeData.clear();
        
        try (BufferedReader br = 
                new BufferedReader(
                        new FileReader(EMPLOYEE_FILE))) {
            
            String line;
            
            // Read the first line (header) and skip it.
            br.readLine();
            
            //Loop through the remaining lines of the file and add the data gathered to the data storage.
            while ((line = br.readLine()) != null) {
                // This split pattern avoids breaking fields that contain commas inside quotation marks.
                String[] empData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                employeeData.add(empData);
            }
            
        } catch (IOException e) {
            System.out.println("Error reading employee file");
        }
    }
    
    // This method searches for one employee in the data storage using the employee number.
    public static String[] findEmployee(String empNum) {
        for (int i = 0; i < employeeData.size(); i++) {
            String[] empData = employeeData.get(i);
            
            if (empData[COL_EMP_NUM].trim().equals(empNum)) {
                return empData;
            }
        }
        return null;
    }
    
    // This method checks whether an employee number exists in the loaded employee data.
    public static boolean employeeExists(String empNum) {
        return findEmployee(empNum) != null;
    }
    
    // This method displays the employee details consisting of their employee number, name, and birthday.
    public static boolean displayEmployeeDetails(String empNum) {
        String[] empData = findEmployee(empNum);
        
        if (empData == null) {
            return false;
        }
        
        String fullName = empData[COL_FIRST_NAME].trim() + " " + empData[COL_LAST_NAME].trim();
        
        System.out.println("=========== Employee Details ===========\n");
        System.out.println("Employee Number: " + empData[COL_EMP_NUM].trim());
        System.out.println("Employee Name: " + fullName);
        System.out.println("Birthday: " + empData[COL_BIRTHDAY].trim());
        
        return true;
    }
    
    // This method gets the hourly rate of one employee from the loaded employee data.
    public static double getHourlyRate(String empNum) {
        String[] empData = findEmployee(empNum);
        
        if (empData == null) {
            return 0;
        }
        
        String rateString = empData[COL_HOURLY_RATE].replace("\"", "").trim();
        return Double.parseDouble(rateString);
    }
    
    // This method returns all employee numbers from the loaded employee data.
    public static ArrayList<String> getAllEmployeeNumbers() {
        ArrayList<String> empNums = new ArrayList<String>();

        for (int i = 0; i < employeeData.size(); i++) {
            String[] empData = employeeData.get(i);
            empNums.add(empData[COL_EMP_NUM].trim());
        }

        return empNums;
    }
}
  