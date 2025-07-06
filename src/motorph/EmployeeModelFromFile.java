/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */
package motorph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class EmployeeModelFromFile extends EmployeeModel {

    /* 
 * OOP PRINCIPLE: INHERITANCE
 * EmployeeModelFromFile extends EmployeeModel, inheriting its attributes and behavior.
     */
    private static final String CSV_PATH = "src/resources/employee_record.csv";

    // CONSTRUCTOR
    public EmployeeModelFromFile() {
        File file = new File(CSV_PATH);
        getDataFromFile(file);
        if (employees == null) {
            employees = new Employee[0]; //null check
        }
        /* 
         * OOP PRINCIPLE: ABSTRACTION
         * Calls getDataFromFile(), which handles the complexity of file reading.
         */
    }

    @Override
    public Employee[] getEmployeeModelList() {
        return employees;
    }

    /* 
     * OOP PRINCIPLE: POLYMORPHISM
     * getEmployeeModelList overrides the method to provide a specific implementation.
     */
    // READING DATA FROM FILE
    private void getDataFromFile(File textFile) {
        try {
            if (!textFile.exists()) { //check if file exists to avoid FileNotFoundException
                System.out.println("Error: CSV file not found!");
                employees = new Employee[0];
                return;
            }

            Scanner lineCounter = new Scanner(textFile);
            int lines = 0;
            while (lineCounter.hasNextLine()) { // determine the number of employees through the number of lines
                lineCounter.nextLine();
                lines++;
            }
            lineCounter.close();

            employees = new Employee[lines - 1];

            Scanner scannerToGetFile = new Scanner(textFile);

            if (scannerToGetFile.hasNextLine()) {
                scannerToGetFile.nextLine(); // skip line 1 (header) on csv file
            }

            int counter = 0;
            while (scannerToGetFile.hasNextLine()) {
                String data = scannerToGetFile.nextLine();
                Scanner scannerForParsing = new Scanner(data);
                parseDataFromScanner(scannerForParsing, counter);
                counter++;
            }
            scannerToGetFile.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
            employees = new Employee[0]; // null check
        }
    }

    // PARSING CSV DATA
    private void parseDataFromScanner(Scanner scanner, int counter) {
        scanner.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // custom delimiter: handles commas inside quotes

        if (!scanner.hasNext()) {
            System.out.println("Error: Empty line detected!");
            return;
        }

        Employee employee = new Employee();
        employee.setEmpNo(cleanString(scanner.next()));
        employee.setLastName(cleanString(scanner.next()));
        employee.setFirstName(cleanString(scanner.next()));
        employee.setBirthday(cleanString(scanner.next()));
        employee.setAddress(cleanString(scanner.next()));
        employee.setPhoneNo(cleanString(scanner.next()));
        employee.setSssNo(cleanString(scanner.next()));
        employee.setPhilHealthNo(cleanString(scanner.next()));
        employee.setTinNo(cleanString(scanner.next()));
        employee.setPagibigNo(cleanString(scanner.next()));
        employee.setStatus(cleanString(scanner.next()));
        employee.setPosition(cleanString(scanner.next()));
        employee.setSupervisor(cleanString(scanner.next()));

        employee.setBasicSalary(parseDoubleOrZero(scanner.next()));
        employee.setRiceSubsidy(parseDoubleOrZero(scanner.next()));
        employee.setPhoneAllowance(parseDoubleOrZero(scanner.next()));
        employee.setClothingAllowance(parseDoubleOrZero(scanner.next()));
        employee.setSemiMonthlyRate(parseDoubleOrZero(scanner.next()));
        employee.setHourlyRate(parseDoubleOrZero(scanner.next()));

        employees[counter] = employee;
    }

    // HELPER METHOD
    private String cleanString(String value) {
        return value.replaceAll("^\"|\"$", "").trim();
    }

    private double parseDoubleOrZero(String value) {
        if (value.equalsIgnoreCase("N/A") || value.trim().isEmpty()) {
            return 0.0; // converts N/A to 0.0
        }
        value = value.replaceAll("^\"|\"$", "").replace(",", ""); //remove quotes and commas
        return Double.parseDouble(value);
    }

    // ADD EMPLOYEE
    public boolean addEmployee(Employee employee) {
        System.err.println(">>> addEmployee called with EmpNo=" + employee.getEmpNo());
        int empNoInt;
    try {
        empNoInt = Integer.parseInt(employee.getEmpNo().trim());
    } catch (NumberFormatException e) {
        System.err.println(">>> addEmployee: invalid numeric EmpNo “" + employee.getEmpNo() + "”");
        return false;
    }
    if (empNoInt >= 10001 && empNoInt <= 10034) {
       System.err.println(">>> addEmployee: REFUSING reserved EmpNo " + empNoInt);
        return false;
    }
    // ── END BLOCK ───────────────────────────────────────────────

    try {
        File file = new File(CSV_PATH);
        boolean endsWithNewline = true;

        if (file.exists() && file.length() > 0) {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            raf.seek(file.length() - 1);
            endsWithNewline = raf.readByte() == '\n';
            raf.close();
        }

        try (FileWriter fw = new FileWriter(CSV_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            if (!endsWithNewline) {
                out.println();
            }

                String row = String.join(",", new String[]{
                    employee.getEmpNo(),
                    employee.getLastName(),
                    employee.getFirstName(),
                    employee.getBirthday(),
                    "\"" + employee.getAddress() + "\"",
                    employee.getPhoneNo(),
                    employee.getSssNo(),
                    employee.getPhilHealthNo(),
                    employee.getTinNo(),
                    employee.getPagibigNo(),
                    employee.getStatus(),
                    employee.getPosition(),
                    employee.getSupervisor(),
                    String.format("\"%.2f\"", employee.getBasicSalary()),
                    String.format("\"%.2f\"", employee.getRiceSubsidy()),
                    String.format("\"%.2f\"", employee.getPhoneAllowance()),
                    String.format("\"%.2f\"", employee.getClothingAllowance()),
                    String.format("\"%.2f\"", employee.getSemiMonthlyRate()),
                    String.format("%.2f", employee.getHourlyRate())
                });

                out.println(row);
            }

        } catch (IOException e) {
            System.out.println("Error writing employee to file.");
            e.printStackTrace();
            return false;
        }

        // Update internal array
        Employee[] newEmployees = new Employee[employees.length + 1];
        System.arraycopy(employees, 0, newEmployees, 0, employees.length);
        newEmployees[employees.length] = employee;
        employees = newEmployees;

        return true;
    }
    
    public boolean updateEmployee(int index, Employee emp) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CSV_PATH), StandardCharsets.UTF_8);
            int target = index + 1;
            if (target < lines.size()) {
                String row = String.join(",", new String[]{
                    emp.getEmpNo(),
                    emp.getLastName(),
                    emp.getFirstName(),
                    emp.getBirthday(),
                    "\"" + emp.getAddress() + "\"",
                    emp.getPhoneNo(),
                    emp.getSssNo(),
                    emp.getPhilHealthNo(),
                    emp.getTinNo(),
                    emp.getPagibigNo(),
                    emp.getStatus(),
                    emp.getPosition(),
                    emp.getSupervisor(),
                    String.format("\"%.2f\"", emp.getBasicSalary()),
                    String.format("\"%.2f\"", emp.getRiceSubsidy()),
                    String.format("\"%.2f\"", emp.getPhoneAllowance()),
                    String.format("\"%.2f\"", emp.getClothingAllowance()),
                    String.format("\"%.2f\"", emp.getSemiMonthlyRate()),
                    String.format("%.2f", emp.getHourlyRate())
                });
                lines.set(target, row);
                Files.write(Paths.get(CSV_PATH), lines, StandardCharsets.UTF_8);

                employees = lines.stream()
                    .skip(1)
                    .map(this::parseLineToEmployee)
                    .toArray(Employee[]::new);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE EMPLOYEE
    public boolean deleteEmployee(int index) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CSV_PATH), StandardCharsets.UTF_8);
            int target = index + 1;
            if (target < lines.size()) {
                lines.remove(target);
                Files.write(Paths.get(CSV_PATH), lines, StandardCharsets.UTF_8);

                employees = lines.stream()
                    .skip(1)
                    .map(this::parseLineToEmployee)
                    .toArray(Employee[]::new);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // HELPER: parse one CSV line to Employee
    private Employee parseLineToEmployee(String csvLine) {
        Scanner sc = new Scanner(csvLine);
        sc.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        Employee emp = new Employee();
        emp.setEmpNo(cleanString(sc.next()));
        emp.setLastName(cleanString(sc.next()));
        emp.setFirstName(cleanString(sc.next()));
        emp.setBirthday(cleanString(sc.next()));
        emp.setAddress(cleanString(sc.next()));
        emp.setPhoneNo(cleanString(sc.next()));
        emp.setSssNo(cleanString(sc.next()));
        emp.setPhilHealthNo(cleanString(sc.next()));
        emp.setTinNo(cleanString(sc.next()));
        emp.setPagibigNo(cleanString(sc.next()));
        emp.setStatus(cleanString(sc.next()));
        emp.setPosition(cleanString(sc.next()));
        emp.setSupervisor(cleanString(sc.next()));
        emp.setBasicSalary(parseDoubleOrZero(sc.next()));
        emp.setRiceSubsidy(parseDoubleOrZero(sc.next()));
        emp.setPhoneAllowance(parseDoubleOrZero(sc.next()));
        emp.setClothingAllowance(parseDoubleOrZero(sc.next()));
        emp.setSemiMonthlyRate(parseDoubleOrZero(sc.next()));
        emp.setHourlyRate(parseDoubleOrZero(sc.next()));
        sc.close();
        return emp;
    }
}

