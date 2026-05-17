/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

/**
 *
 * @author ICE
 */
public class Payroll {
    // This array stores the cutoff start dates for the month of June to December.
    static String[] cutoffStart = {
        "06/01/2024", "06/16/2024",
        "07/01/2024", "07/16/2024",
        "08/01/2024", "08/16/2024",
        "09/01/2024", "09/16/2024",
        "10/01/2024", "10/16/2024",
        "11/01/2024", "11/16/2024",
        "12/01/2024", "12/16/2024"
    };
    
    // This array stores the cutoff end dates for the month of June to December.
    static String[] cutoffEnd = {
        "06/15/2024", "06/30/2024",
        "07/15/2024", "07/31/2024",
        "08/15/2024", "08/31/2024",
        "09/15/2024", "09/30/2024",
        "10/15/2024", "10/31/2024",
        "11/15/2024", "11/30/2024",
        "12/15/2024", "12/31/2024"
    };
    
    // This method calculates the gross salary using hours worked and hourly rate.
    public static double calculateGrossSalary(String empNum, double hoursWorked) throws Exception {
        
        // Get the employee hourly rate from the employee data.
        double hourlyRate = Employee.getHourlyRate(empNum);
        
        // Gross salary = hours worked * hourly rate
        return hoursWorked * hourlyRate;
    }
    
    // This method calculates the combined monthly gross salary grom two cutoffs.
    public static double calculateMonthlyGross(double firstGross, double secondGross) {
        return firstGross + secondGross;
    }
    
    // This method computes the total deductions of one employee.
    public static double computeDeductions(double sss, double philHealth, double pagIBIG, double tax) {
        return sss + philHealth + pagIBIG + tax;
    }
    
    // This method displays the payroll details for both cutoffs in one month.
    public static void displayPayroll(String start1, String end1, String start2, String end2,
                                      double hours1, double gross1, double hours2, double gross2,
                                      double sss, double philHealth, double pagIBIG, double tax,
                                      double totalDed, double net2) {

        System.out.println("Cutoff Date: " + start1 + " to " + end1);
        System.out.println("Total Hours Worked: " + hours1);
        System.out.println("Gross Salary: " + gross1);
        System.out.println("Net Salary: " + gross1);

        System.out.println();

        System.out.println("Cutoff Date: " + start2 + " to " + end2);
        System.out.println("Total Hours Worked: " + hours2);
        System.out.println("Gross Salary: " + gross2);
        System.out.println("Each Deduction:");
        System.out.println("  SSS: " + sss);
        System.out.println("  PhilHealth: " + philHealth);
        System.out.println("  Pag-IBIG: " + pagIBIG);
        System.out.println("  Tax: " + tax);
        System.out.println("Total Deductions: " + totalDed);
        System.out.println("Net Salary: " + net2);

        MotorPHEmployeeApp.printDivider();
    }
    
    // This method processes payroll for one employee across all monthly cutoffs.
    public static void processPayrollForEmployee(String empNum) throws Exception {
        
        if (!Employee.employeeExists(empNum)) {
            System.out.println("Employee number does not exist.");
            return;
        }

        Employee.displayEmployeeDetails(empNum);

        // Process cutoffs two at a time.
        // The loop moves two cutoffs at a time because one month has two payroll periods.
        for (int i = 0; i < cutoffStart.length; i += 2) {
            String start1 = cutoffStart[i];
            String end1 = cutoffEnd[i];
            String start2 = cutoffStart[i + 1];
            String end2 = cutoffEnd[i + 1];

            double hours1 = AttendanceRecord.calculateHours(empNum, start1, end1);
            double hours2 = AttendanceRecord.calculateHours(empNum, start2, end2);

            double gross1 = calculateGrossSalary(empNum, hours1);
            double gross2 = calculateGrossSalary(empNum, hours2);

            double monthlyGross = calculateMonthlyGross(gross1, gross2);

            double sss = Deduction.computeSSS(monthlyGross);
            double philHealth = Deduction.computePhilHealth(monthlyGross);
            double pagIBIG = Deduction.computePagIBIG(monthlyGross);
            double taxable = Deduction.computeTaxableIncome(monthlyGross, sss, philHealth, pagIBIG);
            double tax = Deduction.computeTax(taxable);
            double totalDed = Deduction.computeDeductions(sss, philHealth, pagIBIG, tax);
            double net2 = gross2 - totalDed;

            displayPayroll(start1, end1, start2, end2,
                    hours1, gross1, hours2, gross2,
                    sss, philHealth, pagIBIG, tax,
                    totalDed, net2);
        }
    }
}
