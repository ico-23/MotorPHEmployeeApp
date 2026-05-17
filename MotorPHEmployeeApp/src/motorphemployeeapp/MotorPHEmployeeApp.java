/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package motorphemployeeapp;

import java.util.Scanner;

public class MotorPHEmployeeApp {

     // Print divider
    public static void printDivider() {

        System.out.println(
                "\n----------------------------------------\n");
    }

    // Payroll processing menu
    public static void processPayrollMenu(
            Scanner scanner) throws Exception {

        System.out.println(
                "============ Process Payroll ===========\n");
        System.out.println("[1] One employee");
        System.out.println("[2] All employees");
        System.out.println("[3] Exit the program");


        System.out.print(
                "\nSelect option (1/2/3): ");

        String choice =
                scanner.nextLine();

        // Process one employee
        if (choice.equals("1")) {
            System.out.print(
                    "\nEnter the employee number: ");

            String empNum =
                    scanner.nextLine();

            printDivider();

            Payroll.processPayrollForEmployee(empNum);
        }


        // Process all employees
        else if (choice.equals("2")) {

            printDivider();

            for (String empNum :
                    Employee.getAllEmployeeNumbers()) {
                Payroll.processPayrollForEmployee(empNum);
            }
        }

        // Exit
        else if (choice.equals("3")) {

            printDivider();

            System.out.println("Program closed.");

            System.exit(0);
        }

        // Invalid input
        else {

            printDivider();

            System.out.println("Invalid input.");
            System.exit(0);
        }
    }

    // Main method
    public static void main(String[] args)
            throws Exception {

        Scanner scanner =
                new Scanner(System.in);

        // Load CSV data
        Employee.loadEmployeeData();
        AttendanceRecord.loadAttendanceData();


        System.out.println(
                "\n======== MotorPH Payroll Portal ========\n");

        // Login
        System.out.print(
                "Enter your username: ");

        String username =
                scanner.nextLine();

        System.out.print(
                "Enter your password: ");

        String password =
                scanner.nextLine();
        printDivider();


        // Employee login
        if (UserAccount.employeeLogin(username,password)) {
            UserAccount.employeeMenu(scanner);
        }

        // Payroll staff login
        else if (UserAccount.payrollStaffLogin(username,password)) {
            
            UserAccount.payrollStaffMenu(scanner);
        }

        // Invalid login
        else {
            System.out.println("Incorrect username and/or password");
            System.exit(0);
        }

        scanner.close();
    }
}
