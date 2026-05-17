/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

import java.util.Scanner;

public class UserAccount {
    // Default usernames and passwords
    static final String EMPLOYEE_USERNAME = "employee";
    static final String EMPLOYEE_PASSWORD = "12345";
    static final String PAYROLL_USERNAME = "payroll_staff";
    static final String PAYROLL_PASSWORD =  "12345";
    
    // Validate employee login
    public static boolean employeeLogin(String username,String password) {
        
        return username.equals(EMPLOYEE_USERNAME)
                && password.equals(EMPLOYEE_PASSWORD);
    }

    // Validate payroll staff login
    public static boolean payrollStaffLogin(String username,String password) {

        return username.equals(PAYROLL_USERNAME)
                && password.equals(PAYROLL_PASSWORD);
    }
    
     // This method handles the employee menu after successful login.
    public static void employeeMenu(Scanner scanner) {
        System.out.println("========== Employee logged in ==========\n");
        
        System.out.println("[1] Enter your employee number: ");
        System.out.println("[2] Exit the program");
        
        // Takes user input for the next action.
        System.out.print("\nSelect option (1/2): ");
        String choice = scanner.nextLine();
        
        MotorPHEmployeeApp.printDivider();
        
        // If the user chose "1", user input will be taken to verify their employee number.
        if (choice.equals("1")) {
            System.out.print("Enter your employee number: ");
            String empNum = scanner.nextLine();
            
            MotorPHEmployeeApp.printDivider();
            
            if (!Employee.displayEmployeeDetails(empNum)) {
                System.out.println("Employee number does not exist.");
            }
            
        // If the user chose "2", the program will be closed.
        } else if (choice.equals("2")) {
            System.out.println("Program closed.");
            System.exit(0);
            
        // The program informs the user if their input is invalid.
        } else {
            System.out.println("Invalid input.");
            System.exit(0);
        }
    }
    
    // This method handles the payroll staff menu after successful log in.
    public static void payrollStaffMenu(Scanner scanner) throws Exception {
        System.out.println("======== Payroll staff logged in =======\n");
        
        System.out.println("[1] Process Payroll");
        System.out.println("[2] Exit the program");
        
        // Takes user input for the next action.
        System.out.print("\nSelect option (1/2): ");
        String choice = scanner.nextLine();
        
        MotorPHEmployeeApp.printDivider();
        
        // The user will be taken to the process payroll menu if they choose "1".
        if (choice.equals("1")) {
            MotorPHEmployeeApp.processPayrollMenu(scanner);
            
        // The program will close if the user input is "2".
        } else if (choice.equals("2")) {
            System.out.println("Program closed.");
            System.exit(0);
            
        // The program informs the user if their input is invalid.
        } else {
            System.out.println("Invalid input.");
            System.exit(0);
        }
    }
}
