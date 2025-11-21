// File Name: EmployeeManager.java
// Purpose: Manage employee list through command-line arguments

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reads the entire employee list from file
    public static String loadEmployeeData() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Constants.FILE_NAME)));
            return reader.readLine();
        } catch (Exception e) {
        }
        return null;
    }

    // Writes updated employee data back to file
    public static void saveEmployeeData(String updatedData) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.FILE_NAME));
            writer.write(updatedData);
            writer.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        // No arguments passed
        if (args.length == 0) {
            System.out.println("No argument passed. Application terminated.");
            System.out.println("Date: " + new Date());
            return;
        }

        // OPTION: l → Load and print entire employee list

        if (args[0].equals("l")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String[] employeeNames = loadEmployeeData().split(",");
            for (String emp : employeeNames) {
                System.out.println(emp);
            }

            System.out.println("Data Loaded.");
            System.out.println("Date: " + new Date());
        }


        // OPTION: s → Show a random employee
        else if (args[0].equals("s")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String[] employeeNames = loadEmployeeData().split(",");
            Random random = new Random();

            System.out.println(employeeNames[random.nextInt(employeeNames.length)]);

            System.out.println("Data Loaded.");
            System.out.println("Date: " + new Date());
        }

        // OPTION: +Name → Add new employee
        else if (args[0].startsWith("+")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String newEmployee = args[0].substring(1);
            String updatedList = loadEmployeeData() + ", " + newEmployee;

            saveEmployeeData(updatedList);

            System.out.println("Data Loaded.");
            System.out.println("Date: " + new Date());
        }

        // OPTION: ?Name → Search employee
        else if (args[0].startsWith("?")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String searchQuery = args[0].substring(1);
            String[] employeeNames = loadEmployeeData().split(",");

            boolean found = Arrays.asList(employeeNames).contains(searchQuery);

            if (found)
                System.out.println("Employee found!");
            else
                System.out.println("Employee not found.");

            System.out.println("Data Loaded.");
            System.out.println("Date: " + new Date());
        }

        
        // OPTION: c → Count words in file (employee names)
        else if (args[0].equals("c")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String fileData = loadEmployeeData();

            // Split by one or more spaces → counts words correctly
            String[] words = fileData.trim().split("\\s+");

            System.out.println(words.length + " word(s) found " + fileData.length());

            System.out.println("Data Loaded.");
            System.out.println("Date: " + new Date());
        }

        // OPTION: uName → Update employee name to "Updated"
        else if (args[0].startsWith("u")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String targetName = args[0].substring(1);
            String[] employeeNames = loadEmployeeData().split(",");

            // Replace matching name with "Updated"
            for (int i = 0; i < employeeNames.length; i++) {
                if (employeeNames[i].equals(targetName)) {
                    employeeNames[i] = "Updated";
                }
            }

            saveEmployeeData(String.join(",", employeeNames));

            System.out.println("Data Updated.");
            System.out.println("Date: " + new Date());
        }

        
        // OPTION: dName → Delete employee
        else if (args[0].startsWith("d")) {

            System.out.println("Loading data ...");
            System.out.println("Date: " + new Date());

            String deleteName = args[0].substring(1);
            List<String> list = new ArrayList<>(Arrays.asList(loadEmployeeData().split(",")));

            list.remove(deleteName);

            saveEmployeeData(String.join(",", list));

            System.out.println("Data Deleted.");
            System.out.println("Date: " + new Date());
        }

        // INVALID OPTION
        else {
            System.out.println("Invalid argument. Application terminated.");
            System.out.println("Date: " + new Date());
        }
    }
}
