//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static String readFile() {
        try {
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("employees.txt")));
            String line = r.readLine();
            return line;
        } catch (Exception e) {
        }
        return null;
    }

    public static void writeFile(String data) {
        try {
            BufferedWriter w = new BufferedWriter(
                    new FileWriter("employees.txt"));
            w.write(data);
            w.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No argument passed. Application terminated.");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            String line = readFile();
            String employees[] = line.split(",");
            for (String emp : employees) {
                System.out.println(emp);
            }

            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            String line = readFile();
            System.out.println(line);
            String employees[] = line.split(",");
            Random rand = new Random();
            int randomIndex = rand.nextInt(employees.length);
            System.out.println(employees[randomIndex]);

            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            String line = readFile();
            String name = args[0].substring(1);
            line = line + ", " + name;

            writeFile(line);

            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            String line = readFile();
            String employees[] = line.split(",");
            boolean found = false;
            String searchName = args[0].substring(1);
            for (int i = 0; i < employees.length && !found; i++) {
                if (employees[i].equals(searchName)) {
                    System.out.println("Employee found!");
                    found = true;
                }
            }

            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            String line = readFile();
            char[] chars = line.toCharArray();
            boolean inWord = false;
            int count = 0;
            for (char ch : chars) {
                if (ch == ' ') {
                    if (!inWord) {
                        count++;
                        inWord = true;
                    } else {
                        inWord = false;
                    }
                }
            }
            System.out.println(count + " word(s) found " + chars.length);

            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            String line = readFile();
            String employees[] = line.split(",");
            String name = args[0].substring(1);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(name)) {
                    employees[i] = "Updated";
                }
            }

            writeFile(String.join(",", employees));

            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            String line = readFile();
            String employees[] = line.split(",");
            String name = args[0].substring(1);
            List<String> list = new ArrayList<>(Arrays.asList(employees));
            list.remove(name);

            writeFile(String.join(",", list));

            System.out.println("Data Deleted.");
        }
    }
}
