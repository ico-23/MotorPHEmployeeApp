/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

/**
 *
 * @author ICE
 */
public class Deduction {
     // This method computes the employee's SSS contribution based on the monthly gross salary.
    // The SSS table increases in fixed steps of 500 salary and 22.5 contribution.
    public static double computeSSS(double monthlyGross) {
        
        //Minimum contribution for salaries below 3250
        if (monthlyGross < 3250) {
            return 135.0;
        }
        
        // Maximum contribution cap
        if (monthlyGross >= 24750) {
            return 1125.0;
        }
        
        // Compute which salary bracket the employee falls into
        int bracket = (int)((monthlyGross - 3250) / 500);
        
        // Base value starts at 157.5 for the first bracket
        return 157.5 + (bracket * 22.5);
    }
    
    // This method computes the employee share of PhilHealth based on their monthly gross salary.
    public static double computePhilHealth(double monthlyGross) {
        
        double premium = monthlyGross * 0.03;
        
        // Cap the total premium at 1800, then divide by 2 for the employee share.
        if (premium > 1800) {
            premium = 1800.0;
        }
        
        return premium / 2;
    }
    
    // This method computes the Pag-IBIG contribution based on the employee's monthly gross salary
    public static double computePagIBIG(double monthlyGross) {
        
        double contribution;
        
        if (monthlyGross >= 1000 && monthlyGross <= 1500) {
            contribution = monthlyGross * 0.01;
        } else {
            contribution = monthlyGross * 0.02;
        }
        
        // The employee contribution is capped at 100 pesos.
        if (contribution > 100) {
            contribution = 100;
        }
        
        return contribution;
    }
    
    // This method computes the employee's taxable income.
    // Taxable income = monthly gross salary - mandatory deductions.
    public static double computeTaxableIncome(double monthlyGross, double sss, double philHealth, double pagIBIG) {
        return monthlyGross - (sss + philHealth + pagIBIG);
    }
    
    // This method computes the employee's withholding tax based on the taxable income
    public static double computeTax(double taxableIncome) {
        
        // If taxable income is below 20,832, there is no withholding tax
        if (taxableIncome <= 20832) {
            return 0;
        }
        
        // If taxable income is above 20,833 but not more than 33,333
        // Tax = 20% of the excess over 20,833
        else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        }
        
        // If taxable income is above 33,333 but not more than 66,667
        // Tax = 2,500 + 25% of the excess over 33,333
        else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        }
        
        // If taxable income is above 66,667 but not more than 166,667
        // Tax = 10,833  + 30% of the excess over 66,667
        else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        }
        
        // If taxable income is above 166,667 but not more than 666,667
        // Tax = 40,833.33  + 32% of the excess over 166,667
        else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        }
        
        // If taxable income is above 666,667
        // Tax = 200,833.33  + 35% of the excess over 666,667
        
        else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }
    
    // This method computes the total deductions of one employee.
    public static double computeDeductions(double sss, double philHealth, double pagIBIG, double tax) {
        return sss + philHealth + pagIBIG + tax;
    }
}
