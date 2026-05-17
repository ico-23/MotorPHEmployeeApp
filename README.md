# MotorPH Employee Payroll Application

A Java-based console application that manages employee records and processes payroll for MotorPH, covering the period June–December 2024.

## Project Structure

```
MotorPHEmployeeApp/
├── src/motorphemployeeapp/
│   ├── MotorPHEmployeeApp.java   # Entry point and main menu
│   ├── Employee.java             # Employee data loading and lookup
│   ├── AttendanceRecord.java     # Attendance parsing and hours calculation
│   ├── Payroll.java              # Gross salary and payroll processing
│   ├── Deduction.java            # SSS, PhilHealth, Pag-IBIG, and tax computation
│   └── UserAccount.java          # Login and role-based menu routing
├── employee.csv                  # Employee master data
├── attendance.csv                # Daily attendance logs
└── README.md
```

## Features

- **Role-based login** — separate access for employees and payroll staff
- **Employee lookup** — view name, employee number, and birthday by employee ID
- **Payroll processing** — compute gross salary, deductions, and net pay per cutoff period
- **Batch payroll** — process all employees at once or one at a time
- **Statutory deductions** — automatically calculates SSS, PhilHealth, Pag-IBIG, and withholding tax

## How to Run

Built with **NetBeans IDE** using **Java 25**. To run:

1. Open the project in NetBeans (File → Open Project).
2. Ensure `employee.csv` and `attendance.csv` are in the project root directory.
3. Run the project (Shift+F6 or the Run button).

To build a JAR from the command line:

```bash
ant jar
java -jar dist/MotorPHEmployeeApp.jar
```

## Login Credentials

| Role          | Username       | Password |
|---------------|----------------|----------|
| Employee      | `employee`     | `12345`  |
| Payroll Staff | `payroll_staff`| `12345`  |

## Payroll Logic

### Cutoff Periods
Payroll is split into two periods per month (1st–15th and 16th–end of month), from June to December 2024.

### Hours Calculation
- Work hours are counted from 8:00 AM to 5:00 PM.
- A 10-minute grace period applies (logins at or before 8:10 AM are treated as 8:00 AM).
- One hour is deducted daily for lunch break.
- Overtime is not included.

### Deductions (applied to combined monthly gross)

| Deduction   | Basis |
|-------------|-------|
| SSS         | Salary bracket table (₱135 minimum, ₱1,125 maximum) |
| PhilHealth  | 3% of monthly gross, employee share is half (capped at ₱900) |
| Pag-IBIG    | 1–2% of monthly gross, capped at ₱100 |
| Withholding Tax | Progressive tax on taxable income (gross minus mandatory deductions) |

### Net Pay
Deductions are applied against the **second cutoff's gross salary**. The first cutoff is paid in full.

## Data Files

### employee.csv
Contains employee master records including name, position, supervisor, and compensation details. The hourly rate is in column 19 (index 18).

### attendance.csv
Contains daily login/logout records per employee. Columns: Employee #, Last Name, First Name, Date, Log In, Log Out.
