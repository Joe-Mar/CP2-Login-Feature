/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Class: NetSalaryPanel
 * Description: JPanel that allows lookup of an employee by number,
 *              loads their attendance for a selected pay period,
 *              and computes the net salary including allowances.
 */
public class NetSalaryPanel extends javax.swing.JPanel {
    private static EmployeeModel employeeModel;
    private EmployeeTimeRecord employeeTimeRecord;

    /**
     * Constructor: initializes UI components, sets up the
     *              employee model, populates date ranges,
     *              and wires the submit button action.
     */
    public NetSalaryPanel() {
        initComponents();
        employeeModel = new EmployeeModelFromFile();
        populateDateRanges();
        addSubmitAction();
    }

    /**
     * Populates the pay-period combo box with hard-coded date ranges.
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
            comboDateRange.addItem(range);
        }
    }

    /**
     * Attaches the submit button listener to trigger employee lookup.
     */
    private void addSubmitAction() {
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String empNumber = textFieldEmpNo.getText().trim();
                getEmployeeInfo(empNumber);
            }
        });
    }

    /**
     * Looks up the employee by number, loads attendance, and
     * displays basic salary info immediately.
     * @param empNumber the employee number to search for
     */
    private void getEmployeeInfo(String empNumber) {
        Employee[] employees = employeeModel.getEmployeeModelList();
        boolean found = false;

        for (Employee emp : employees) {
            if (emp != null && emp.getEmpNo().equalsIgnoreCase(empNumber)) {
                employeeTimeRecord = new EmployeeTimeRecord(emp);
                loadAttendanceFromCSV("src/resources/attendance_record.csv", empNumber);

                labelOutput1.setText(emp.getEmpNo());
                labelOutput2.setText(emp.getLastName() + ", " + emp.getFirstName());
                labelOutput3.setText(String.format("%.2f", emp.getBasicSalary()));

                computeSalaryBasedOnRange(); // immediately compute net salary
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this,
                "Employee not found: " + empNumber,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Computes the net salary for the selected pay period,
     * including allowances, and updates the output label.
     */
    private void computeSalaryBasedOnRange() {
        if (employeeTimeRecord != null) {
            String[] range = comboDateRange.getSelectedItem().toString().split(" to ");
            int totalHours = employeeTimeRecord.getTotalHoursWorked(range[0], range[1]);

            if (totalHours <= 0) {
                labelOutput4.setText("0.00");
                return;
            }

            double hourlyRate        = employeeTimeRecord.getHourlyRate();
            double salaryOnHours     = totalHours * hourlyRate;
            double riceSubsidy       = employeeTimeRecord.getRiceSubsidy();
            double phoneAllowance    = employeeTimeRecord.getPhoneAllowance();
            double clothingAllowance = employeeTimeRecord.getClothingAllowance();

            double totalAllowances = riceSubsidy + phoneAllowance + clothingAllowance;
            double netSalary       = salaryOnHours + totalAllowances;

            labelOutput4.setText(String.format("%.2f", netSalary));
        }
    }

    /**
     * Reads the attendance CSV for the given employee number,
     * parses times, and records daily hours in employeeTimeRecord.
     * @param filename path to the CSV file
     * @param empNo    employee number filter
     */
    private void loadAttendanceFromCSV(String filename, String empNo) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                String recordEmpNo = data[0].trim();
                if (!recordEmpNo.equals(empNo)) continue;

                String date       = formatDate(data[3].trim());
                String timeInStr  = data[4].trim();
                String timeOutStr = data[5].trim();

                int timeIn      = parseHour(timeInStr);
                int timeOut     = parseHour(timeOutStr);
                int hoursWorked = timeOut - timeIn;

                if (employeeTimeRecord != null && hoursWorked > 0) {
                    employeeTimeRecord.addWorkHours(date, hoursWorked);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "CSV Read Error: " + ex.getMessage());
        }
    }

    /**
     * Parses a "HH:mm" string into an integer hour,
     * rounding up if minutes >= 30.
     * Returns 0 on any parse failure.
     * @param time the time string to parse
     */
    private int parseHour(String time) {
        try {
            String[] parts = time.split(":");
            int hour   = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return (minute >= 30) ? hour + 1 : hour;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Converts a date string from "MM/dd/yyyy" to "yyyy-MM-dd".
     * If parsing fails, returns the original string.
     * @param rawDate the raw date string
     * @return formatted ISO date or original on error
     */
    private String formatDate(String rawDate) {
        try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(rawDate);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            return rawDate;
        }
    }

    /** NetBeans-generated UI setup (components and layout) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelEmpNo = new javax.swing.JLabel();
        textFieldEmpNo = new javax.swing.JTextField();
        buttonSubmit = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        labelEmpNo2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelBasicSalary = new javax.swing.JLabel();
        labelNetSalary = new javax.swing.JLabel();
        labelcolon1 = new javax.swing.JLabel();
        labelOutput1 = new javax.swing.JLabel();
        labelcolon2 = new javax.swing.JLabel();
        labelcolon3 = new javax.swing.JLabel();
        labelcolon4 = new javax.swing.JLabel();
        labelOutput2 = new javax.swing.JLabel();
        labelOutput3 = new javax.swing.JLabel();
        labelOutput4 = new javax.swing.JLabel();
        comboDateRange = new javax.swing.JComboBox<>();

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

        labelEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo2.setText("Employee NO. ");

        labelName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelName.setText("Name");

        labelBasicSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelBasicSalary.setText("Basic Salary ");

        labelNetSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelNetSalary.setText("Net Salary");

        labelcolon1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon1.setText(":");

        labelOutput1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelcolon2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon2.setText(":");

        labelcolon3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon3.setText(":");

        labelcolon4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelcolon4.setText(":");

        labelOutput2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelOutput3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelOutput4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        comboDateRange.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        comboDateRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Pay Period..." }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelBasicSalary, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEmpNo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNetSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelcolon1)
                            .addComponent(labelcolon2)
                            .addComponent(labelcolon3)
                            .addComponent(labelcolon4))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelOutput4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutput3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutput1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOutput2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelEmpNo)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textFieldEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSubmit))
                            .addComponent(comboDateRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separator)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEmpNo)
                            .addComponent(textFieldEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSubmit))
                        .addGap(5, 5, 5)
                        .addComponent(comboDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelOutput1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelEmpNo2)
                                        .addComponent(labelcolon1)))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelName)
                                    .addComponent(labelcolon2)))
                            .addComponent(labelOutput2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelBasicSalary)
                            .addComponent(labelcolon3)))
                    .addComponent(labelOutput3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNetSalary)
                        .addComponent(labelcolon4))
                    .addComponent(labelOutput4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSubmitActionPerformed
        getEmployeeInfo(textFieldEmpNo.getText());
    }//GEN-LAST:event_buttonSubmitActionPerformed

    /**
     * Main method for standalone testing of NetSalaryPanel.
     * @param args command line arguments (unused)
     */
    public static void main(String args[]) {
        employeeModel = new EmployeeModelFromFile(); // Make sure this class is correctly implemented

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Net Salary Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new NetSalaryPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JComboBox<String> comboDateRange;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBasicSalary;
    private javax.swing.JLabel labelEmpNo;
    private javax.swing.JLabel labelEmpNo2;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNetSalary;
    private javax.swing.JLabel labelOutput1;
    private javax.swing.JLabel labelOutput2;
    private javax.swing.JLabel labelOutput3;
    private javax.swing.JLabel labelOutput4;
    private javax.swing.JLabel labelcolon1;
    private javax.swing.JLabel labelcolon2;
    private javax.swing.JLabel labelcolon3;
    private javax.swing.JLabel labelcolon4;
    private javax.swing.JSeparator separator;
    private javax.swing.JTextField textFieldEmpNo;
    // End of variables declaration//GEN-END:variables
}
