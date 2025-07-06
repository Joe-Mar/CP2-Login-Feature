/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */
package motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Class: HoursWorkedPanel
 * Description: Panel for loading employee master data, parsing attendance records,
 *              and computing salary based on hours worked in a selected pay period.
 */
public class HoursWorkedPanel extends javax.swing.JPanel {

    /** Holds all employees keyed by employee number for quick lookup */
    private Map<String, EmployeeTimeRecord> employees = new HashMap<>();

    /**
     * Constructor: initializes UI components, loads employees and attendance data,
     *              and populates the pay‐period dropdown.
     */
    public HoursWorkedPanel() {
        initComponents();
        loadEmployeesFromCSV("src/resources/employee_record.csv");
        loadAttendanceFromCSV("src/resources/attendance_record.csv");
        populateDateRanges();
    }

    /**
     * Parses a numeric string safely into a double, stripping quotes and commas.
     * Returns 0.0 on failure.
     * @param value raw string potentially containing quotes/commas
     * @return parsed double or 0.0 if invalid
     */
    private double parseDoubleSafe(String value) {
        try {
            // Remove quotes and commas before parsing
            value = value.replace("\"", "").replace(",", "").trim();
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number: [" + value + "]");
            return 0.0;
        }
    }

    /**
     * Loads employee master records from a CSV file into the employees map.
     * Expects at least 19 columns per row.
     * @param filename path to the employee CSV file
     */
    private void loadEmployeesFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                List<String> data = splitCSVLine(line);
                if (data.size() < 19) continue; // skip malformed rows

                EmployeeTimeRecord emp = new EmployeeTimeRecord();
                emp.setEmpNo(data.get(0));
                emp.setLastName(data.get(1));
                emp.setFirstName(data.get(2));
                emp.setBirthday(data.get(3));
                emp.setAddress(data.get(4));
                emp.setPhoneNo(data.get(5));
                emp.setSssNo(data.get(6));
                emp.setPhilHealthNo(data.get(7));
                emp.setTinNo(data.get(8));
                emp.setPagibigNo(data.get(9));
                emp.setStatus(data.get(10));
                emp.setPosition(data.get(11));
                emp.setSupervisor(data.get(12));
                emp.setBasicSalary(parseDoubleSafe(data.get(13)));
                emp.setRiceSubsidy(parseDoubleSafe(data.get(14)));
                emp.setPhoneAllowance(parseDoubleSafe(data.get(15)));
                emp.setClothingAllowance(parseDoubleSafe(data.get(16)));
                emp.setSemiMonthlyRate(parseDoubleSafe(data.get(17)));
                emp.setHourlyRate(parseDoubleSafe(data.get(18)));

                employees.put(emp.getEmpNo(), emp);

                // Debug log
                System.out.println("Loaded: " + emp.getEmpNo()
                    + " | " + emp.getFirstName() + " " + emp.getLastName()
                    + " | Salary: " + emp.getBasicSalary()
                    + " | Hourly Rate: " + emp.getHourlyRate());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error reading employee CSV: " + ex.getMessage());
        }
    }

    /**
     * Splits a CSV line into fields, correctly handling quoted commas.
     * @param line raw CSV line
     * @return list of trimmed field values
     */
    private List<String> splitCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\"') {
                inQuotes = !inQuotes;              // toggle quote state
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim()); // end of field
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString().trim());         // add last field
        return result;
    }

    /**
     * Reads attendance records from a CSV file, filters by employee number,
     * converts date and time formats, and adds the hours worked into each
     * employee's time record.
     * @param filename path to the attendance CSV file
     */
    private void loadAttendanceFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;   // basic validation

                String empNo      = data[0].trim();
                String date       = formatDate(data[3].trim());  // convert to yyyy-MM-dd
                String timeInStr  = data[4].trim();
                String timeOutStr = data[5].trim();

                int timeIn      = parseHour(timeInStr);
                int timeOut     = parseHour(timeOutStr);
                int hoursWorked = timeOut - timeIn;

                EmployeeTimeRecord emp = employees.get(empNo);
                if (emp != null && hoursWorked > 0) {
                    emp.addWorkHours(date, hoursWorked);
                    System.out.println("Parsing: " + empNo
                        + " | " + date
                        + " | " + timeIn + " to " + timeOut
                        + " = " + hoursWorked);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error reading attendance CSV: " + ex.getMessage());
        }
    }

    /**
     * Parses a time string "HH:mm" into an integer hour,
     * rounding up when minutes are 30 or more.
     * Returns 0 on parse failure.
     * @param time string in "HH:mm" format
     * @return integer hour value
     */
    private int parseHour(String time) {
        try {
            String[] parts = time.split(":");
            int hour   = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return (minute >= 30) ? hour + 1 : hour;
        } catch (Exception e) {
            System.err.println("Failed to parse time: [" + time + "]");
            return 0;
        }
    }

    /**
     * Converts a date string from "MM/dd/yyyy" format to ISO "yyyy-MM-dd".
     * Falls back to the raw input on parse failure.
     * @param rawDate date string in "MM/dd/yyyy"
     * @return formatted date string
     */
    private String formatDate(String rawDate) {
        try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(rawDate);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + rawDate);
            return rawDate;  // fallback
        }
    }

    /**
     * Populates the pay‐period combo box with predefined semi-monthly ranges.
     */
    private void populateDateRanges() {
        String[] ranges = {
            "2024-06-01 to 2024-06-15", "2024-06-16 to 2024-06-30",
            "2024-07-01 to 2024-07-15", "2024-07-16 to 2024-07-31",
            "2024-08-01 to 2024-08-15", "2024-08-16 to 2024-08-31",
            "2024-09-01 to 2024-09-15", "2024-09-16 to 2024-09-30",
            "2024-10-01 to 2024-10-15", "2024-10-16 to 2024-10-31",
            "2024-11-01 to 2024-11-15", "2024-11-16 to 2024-11-30",
            "2024-12-01 to 2024-12-15", "2024-12-16 to 2024-12-31"
        };
        for (String range : ranges) {
            jcbDateRange.addItem(range);
        }
    }

    /** NetBeans-generated UI setup (components and layout) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHoursWorked = new javax.swing.JPanel();
        labelPayCoverage = new javax.swing.JLabel();
        btnCalculateSalary = new javax.swing.JButton();
        labelEmpNo2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelBasicSalary = new javax.swing.JLabel();
        labelHoursWorked = new javax.swing.JLabel();
        jcbDateRange = new javax.swing.JComboBox<>();
        labelOutputEmpNo = new javax.swing.JLabel();
        labelOutputBasic = new javax.swing.JLabel();
        labelcolon1 = new javax.swing.JLabel();
        labelOutputHoursWorked = new javax.swing.JLabel();
        labelcolon2 = new javax.swing.JLabel();
        labelcolon3 = new javax.swing.JLabel();
        labelcolon4 = new javax.swing.JLabel();
        labelOutputName = new javax.swing.JLabel();
        labelEmpNo = new javax.swing.JLabel();
        textFieldEmpNo = new javax.swing.JTextField();
        buttonSubmit = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();

        labelPayCoverage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelPayCoverage.setText("Pay Coverage:");

        btnCalculateSalary.setBackground(new java.awt.Color(0, 51, 0));
        btnCalculateSalary.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnCalculateSalary.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculateSalary.setText("Calculate Salary");
        btnCalculateSalary.setToolTipText("");
        btnCalculateSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateSalaryActionPerformed(evt);
            }
        });

        labelEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo2.setText("Employee NO.");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("Name");

        labelBasicSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary.setText("Basic Salary");

        labelHoursWorked.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelHoursWorked.setText("Salary on Hours Worked");

        jcbDateRange.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jcbDateRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Pay Period..." }));

        labelOutputEmpNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelOutputBasic.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelcolon1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon1.setText(":");

        labelOutputHoursWorked.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelcolon2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon2.setText(":");

        labelcolon3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon3.setText(":");

        labelcolon4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon4.setText(":");

        labelOutputName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelEmpNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo.setText("Employee NO.:");

        textFieldEmpNo.setBackground(new java.awt.Color(204, 204, 204));
        textFieldEmpNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        buttonSubmit.setBackground(new java.awt.Color(0, 51, 0));
        buttonSubmit.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        buttonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        buttonSubmit.setText("Submit");
        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHoursWorkedLayout = new javax.swing.GroupLayout(panelHoursWorked);
        panelHoursWorked.setLayout(panelHoursWorkedLayout);
        panelHoursWorkedLayout.setHorizontalGroup(
            panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName)
                            .addComponent(labelBasicSalary)
                            .addComponent(labelHoursWorked)
                            .addComponent(labelEmpNo2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelcolon1)
                            .addComponent(labelcolon2)
                            .addComponent(labelcolon3)
                            .addComponent(labelcolon4))
                        .addGap(75, 75, 75)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelOutputHoursWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutputBasic, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutputEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutputName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85))
                    .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelPayCoverage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEmpNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbDateRange, 0, 184, Short.MAX_VALUE)
                            .addComponent(textFieldEmpNo))
                        .addGap(18, 18, 18)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCalculateSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoursWorkedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separator)
                .addContainerGap())
        );
        panelHoursWorkedLayout.setVerticalGroup(
            panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo)
                    .addComponent(textFieldEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSubmit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPayCoverage)
                    .addComponent(btnCalculateSalary)
                    .addComponent(jcbDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmpNo2)
                    .addComponent(labelOutputEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelcolon1))
                .addGap(40, 40, 40)
                .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName)
                            .addComponent(labelcolon2))
                        .addGap(40, 40, 40)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelBasicSalary)
                            .addComponent(labelcolon3))
                        .addGap(40, 40, 40)
                        .addGroup(panelHoursWorkedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHoursWorked)
                            .addComponent(labelcolon4)))
                    .addGroup(panelHoursWorkedLayout.createSequentialGroup()
                        .addComponent(labelOutputName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelOutputBasic, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(labelOutputHoursWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHoursWorked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHoursWorked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Computes salary based on hours worked in the selected pay period,
     * then displays the basic salary and computed amount.
     */
    private void btnCalculateSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateSalaryActionPerformed
        String empNo = textFieldEmpNo.getText().trim();
        EmployeeTimeRecord emp = employees.get(empNo);

        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Please fetch an employee first.");
            return;
        }

        // Parse the “start to end” date range
        String[] range       = ((String) jcbDateRange.getSelectedItem()).split(" to ");
        int totalHours       = emp.getTotalHoursWorked(range[0], range[1]);
        double hourlyRate    = emp.getHourlyRate();
        double salaryOnHours = totalHours * hourlyRate;

        // Display outputs
        labelOutputBasic.setText(String.format("%.2f", emp.getBasicSalary()));
        labelOutputHoursWorked.setText(String.format("%.2f", salaryOnHours));

        // Debug logs
        System.out.println("Basic Salary: " + emp.getBasicSalary());
        System.out.println("Hourly Rate: " + emp.getHourlyRate());
        System.out.println("Hours: " + totalHours);
        System.out.println("Salary on Hours Worked: " + salaryOnHours);
    }//GEN-LAST:event_btnCalculateSalaryActionPerformed

    /**
     * Fetches an employee’s details by EmpNo, displays number and name,
     * and clears any previous salary‐on‐hours output.
     */
    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSubmitActionPerformed
        String empNo = textFieldEmpNo.getText().trim();
        EmployeeTimeRecord emp = employees.get(empNo);

        if (emp != null) {
            labelOutputEmpNo.setText(emp.getEmpNo());
            labelOutputName.setText(emp.getFirstName() + " " + emp.getLastName());
            labelOutputBasic.setText(String.format("%.2f", emp.getBasicSalary()));
            labelOutputHoursWorked.setText("");  // reset previous calculation
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }
    }//GEN-LAST:event_buttonSubmitActionPerformed

    /**
     * Main entry point for standalone testing of the HoursWorkedPanel.
     * @param args command‐line arguments (unused)
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            HoursWorkedPanel panel = new HoursWorkedPanel();
            panel.setVisible(true);
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculateSalary;
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JComboBox<String> jcbDateRange;
    private javax.swing.JLabel labelBasicSalary;
    private javax.swing.JLabel labelEmpNo;
    private javax.swing.JLabel labelEmpNo2;
    private javax.swing.JLabel labelHoursWorked;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelOutputBasic;
    private javax.swing.JLabel labelOutputEmpNo;
    private javax.swing.JLabel labelOutputHoursWorked;
    private javax.swing.JLabel labelOutputName;
    private javax.swing.JLabel labelPayCoverage;
    private javax.swing.JLabel labelcolon1;
    private javax.swing.JLabel labelcolon2;
    private javax.swing.JLabel labelcolon3;
    private javax.swing.JLabel labelcolon4;
    private javax.swing.JPanel panelHoursWorked;
    private javax.swing.JSeparator separator;
    private javax.swing.JTextField textFieldEmpNo;
    // End of variables declaration//GEN-END:variables

}

