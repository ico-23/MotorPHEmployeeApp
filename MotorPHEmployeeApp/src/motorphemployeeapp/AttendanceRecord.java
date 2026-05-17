/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorphemployeeapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AttendanceRecord {
    
    // CSV file path
    static final String ATTENDANCE_FILE = "attendance.csv";
    
     // These constants represent the column positions in the attendance CSV file.
    static final int ATT_EMP_NUM = 0;
    static final int ATT_DATE = 3;
    static final int ATT_LOGIN = 4;
    static final int ATT_LOGOUT = 5;
    
    // This ArrayLists store the CSV contents after reading and loading the files.
    static ArrayList<String[]> attendanceData = new ArrayList<String[]>();
    
    // This method reads the attendance CSV file once and stores all rows in a data storage.
    public static void loadAttendanceData() {
        attendanceData.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
            String line;
            
            // Read the first line (header) and skip it.
            br.readLine();
            
            //Loop through the remaining lines of the file and add the data gathered to the data storage.
            while((line = br.readLine()) != null) {
                String[] attData = line.split(",");
                attendanceData.add(attData);
            }
            
        } catch (IOException e) {
            System.out.println("Error reading attendance file");
        }
    }
    
    // This method calculates the total works hours of one employee within a cutoff period.
    public static double calculateHours(String empNum, String startDate, String endDate) throws Exception {
        
        // This variable stores accumulated hours worked.
        double totalHours = 0;
        
        // Date and time format in the CSV file
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
        
        // Convert cutoff start and end dates to LocalDate
        LocalDate start = LocalDate.parse(startDate, dateFormat);
        LocalDate end = LocalDate.parse(endDate, dateFormat);
        
        for (int i = 0; i < attendanceData.size(); i++) {
            String[] attData = attendanceData.get(i);

            String rowEmpNum = attData[ATT_EMP_NUM].trim();
            LocalDate workDate = LocalDate.parse(attData[ATT_DATE].trim(), dateFormat);

            // Only process rows that belong to the correct employee and fall within the cutoff dates.
            if (rowEmpNum.equals(empNum) && !workDate.isBefore(start) && !workDate.isAfter(end)) {
                LocalTime login = LocalTime.parse(attData[ATT_LOGIN].trim(), timeFormat);
                LocalTime logout = LocalTime.parse(attData[ATT_LOGOUT].trim(), timeFormat);

                double worked = calculateDailyHours(login, logout);
                totalHours += worked;
            }
        }
        
        return totalHours;
    }
    
    // This method calculates one day's payable hours based on login and logout times.
    public static double calculateDailyHours(LocalTime login, LocalTime logout) {
        LocalTime workStart = LocalTime.of(8, 0);
        LocalTime workEnd = LocalTime.of(17, 0);
        LocalTime graceLimit = LocalTime.of(8, 10);

        // If the employee logs in within the grace period, treat it as exactly 8:00 AM.
        if (!login.isAfter(graceLimit)) {
            login = workStart;
        }

        // Any time after 5:00 PM is not counted because overtime is not included here.
        if (logout.isAfter(workEnd)) {
            logout = workEnd;
        }

        double hours = Duration.between(login, logout).toMinutes() / 60.0;

        // Deduct one hour for lunch break from the daily total.
        hours = hours - 1.0;

        // Prevent negative hours in case of invalid or incomplete time data.
        if (hours < 0) {
            hours = 0;
        }

        return hours;
    }
}
