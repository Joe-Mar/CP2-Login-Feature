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
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Class: EmployeeInformationPanel
 * Description: Displays an employee’s personal & employment details,
 *              loads attendance from CSV, and computes payslip.
 */
public class EmployeeInformationPanel extends JPanel {

    /** Parent directory panel for refreshing the list */
    private EmployeeDirectoryPanel directoryPanel;
    /** Index of the currently selected employee */
    private int employeeIndex;
    /** Employee number for current record */
    private String selectedEmpNo = "";
    /** Holds time‐record data for payroll calculations */
    private EmployeeTimeRecord employeeTimeRecord;
    /** Reusable form for editing employee details */
    private EditInformation editForm;

    /** CSV file path for attendance records */
    private static final String ATTENDANCE_FILE = "src/resources/attendance_record.csv";
    /** ISO date formatter for parsing pay periods */
    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Constructor: sets up UI, loads employee data & attendance,
     * populates pay‐period dropdown, and instantiates the edit form.
     */
    public EmployeeInformationPanel(EmployeeDirectoryPanel directoryPanel, int employeeIndex) {
        initComponents();

        // Fix panel to 500×500
        setPreferredSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setMaximumSize(new java.awt.Dimension(500, 500));
        jTabbedPane8.setPreferredSize(new java.awt.Dimension(500, 500));

        this.directoryPanel = directoryPanel;
        this.employeeIndex  = employeeIndex;

        // Load personal & employment info, plus attendance
        loadEmployeeDataToFields(employeeIndex, new EmployeeModelFromFile());

        // Fill pay‐period choices
        populateDateRanges();

        // Prepare edit dialog (reused on both tabs)
        editForm = new EditInformation(directoryPanel, this, employeeIndex);
    }

    /**
     * Loads personal & employment data into the UI fields,
     * then reads that employee’s attendance from the CSV.
     */
    public void loadEmployeeDataToFields(int empIndex, EmployeeModelFromFile model) {
        Employee emp = model.getEmployeeModelList()[empIndex];
        selectedEmpNo      = emp.getEmpNo();
        employeeTimeRecord = new EmployeeTimeRecord(emp);

        // Read all of this employee’s attendance
        loadAttendanceFromCSV(ATTENDANCE_FILE, selectedEmpNo);

        // --- Personal info fields ---
        empNumField.setText(emp.getEmpNo());
        nameField.setText(emp.getFirstName() + " " + emp.getLastName());
        birthdayField.setText(emp.getBirthday());
        addressField.setText(emp.getAddress());
        phoneNoField.setText(emp.getPhoneNo());
        sssField.setText(emp.getSssNo());
        philHealthField.setText(emp.getPhilHealthNo());
        pagIbigField.setText(emp.getPagibigNo());
        tinField.setText(emp.getTinNo());

        // --- Employment info fields ---
        statusField.setText(emp.getStatus());
        positionField.setText(emp.getPosition());
        supervisorField.setText(emp.getSupervisor());
        salaryField.setText(String.valueOf(emp.getBasicSalary()));
        riceField.setText(String.valueOf(emp.getRiceSubsidy()));
        phoneField.setText(String.valueOf(emp.getPhoneAllowance()));
        clothingField.setText(String.valueOf(emp.getClothingAllowance()));
    }

    /**
     * Reads attendance CSV, filters by empNo, converts dates & times,
     * and adds each day’s hours into employeeTimeRecord.
     */
    public void loadAttendanceFromCSV(String filename, String empNo) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6 || !data[0].trim().equals(empNo)) {
                    continue; // not this employee or malformed
                }
                String date       = formatDate(data[3].trim());
                int timeIn        = parseHour(data[4].trim());
                int timeOut       = parseHour(data[5].trim());
                int hoursWorked   = timeOut - timeIn;
                if (employeeTimeRecord != null && hoursWorked > 0) {
                    employeeTimeRecord.addWorkHours(date, hoursWorked);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error reading attendance CSV: " + ex.getMessage());
        }
    }

    /**
     * Parses "HH:mm" into an integer hour, rounding up if minutes ≥30.
     * Returns 0 on parse failure.
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
     * Converts dates from "MM/dd/yyyy" to "yyyy-MM-dd".
     * If parse fails, returns the original string.
     */
    private String formatDate(String rawDate) {
        try {
            Date parsed = new SimpleDateFormat("MM/dd/yyyy").parse(rawDate);
            return new SimpleDateFormat("yyyy-MM-dd").format(parsed);
        } catch (ParseException e) {
            return rawDate;
        }
    }

    /** Clears all UI fields and resets internal state */
    public void clearFields() {
        empNumField.setText("");
        nameField.setText("");
        birthdayField.setText("");
        addressField.setText("");
        phoneNoField.setText("");
        sssField.setText("");
        philHealthField.setText("");
        pagIbigField.setText("");
        tinField.setText("");
        statusField.setText("");
        positionField.setText("");
        supervisorField.setText("");
        salaryField.setText("");
        riceField.setText("");
        phoneField.setText("");
        clothingField.setText("");

        // Clear computed outputs
        txtMonthlyRateOutput.setText("");
        txtDailyRateOutput.setText("");
        txtDaysWorkedOutput.setText("");
        txtOvertimeOutput.setText("");
        txtEarningsGrossIncomeOutput.setText("");
        txtRiceSubsidyOutput.setText("");
        txtPhoneAllowanceOutput.setText("");
        txtClothingAllowanceOutput.setText("");
        txtTotalOutput1.setText("");
        txtSSSOutput.setText("");
        txtPhHealthOutput.setText("");
        txtPagIbigOutput.setText("");
        txtWithholdingTaxOutput.setText("");
        txtTotalDeductionsOutput.setText("");
        txtGrossIncomeOutput.setText("");
        txtSummaryBenefitsOutput.setText("");
        txtSummaryDeductionsOutput.setText("");
        txtTakeHomePayOutput.setText("0.00");

        employeeTimeRecord = null;
        selectedEmpNo      = "";
    }

    /**
     * Refreshes the panel to show details for a different employee.
     * @param empIndex the index of the employee to load
     */
    public void loadEmployeeDetails(int empIndex) {
        this.employeeIndex = empIndex;
        loadEmployeeDataToFields(empIndex, new EmployeeModelFromFile());
    }

    /** Fills the pay‐period combo box with hard‐coded date ranges */
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

    /** NetBeans-generated UI setup (do not modify) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        personalInformationTab7 = new javax.swing.JPanel();
        empNumLabel = new javax.swing.JLabel();
        empNumField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        birthdayLabel = new javax.swing.JLabel();
        birthdayField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        phoneNoLabel = new javax.swing.JLabel();
        phoneNoField = new javax.swing.JTextField();
        sssLabel = new javax.swing.JLabel();
        sssField = new javax.swing.JTextField();
        philHealthLabel = new javax.swing.JLabel();
        philHealthField = new javax.swing.JTextField();
        tinLabel = new javax.swing.JLabel();
        tinField = new javax.swing.JTextField();
        pagIbigLabel = new javax.swing.JLabel();
        pagIbigField = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();
        employmentInformationTab = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        positionLabel = new javax.swing.JLabel();
        positionField = new javax.swing.JTextField();
        supervisorLabel = new javax.swing.JLabel();
        supervisorField = new javax.swing.JTextField();
        salaryLabel = new javax.swing.JLabel();
        salaryField = new javax.swing.JTextField();
        riceLabel = new javax.swing.JLabel();
        riceField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        clothingLabel = new javax.swing.JLabel();
        clothingField = new javax.swing.JTextField();
        editBtn1 = new javax.swing.JButton();
        hoursComputationTab = new javax.swing.JPanel();
        labelPayCoverage = new javax.swing.JLabel();
        jcbDateRange = new javax.swing.JComboBox<>();
        btnCompute = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPaneloverScrollPane = new javax.swing.JPanel();
        lblEarnings = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblMonthlyRate = new javax.swing.JLabel();
        lblDailyRate = new javax.swing.JLabel();
        lblDaysWorked = new javax.swing.JLabel();
        lblOvertime = new javax.swing.JLabel();
        lblEarningsGrossIncome = new javax.swing.JLabel();
        lblBenefits = new javax.swing.JLabel();
        txtMonthlyRateOutput = new javax.swing.JTextField();
        txtDailyRateOutput = new javax.swing.JTextField();
        txtDaysWorkedOutput = new javax.swing.JTextField();
        txtEarningsGrossIncomeOutput = new javax.swing.JTextField();
        txtOvertimeOutput = new javax.swing.JTextField();
        lblRiceSubsidy = new javax.swing.JLabel();
        lblPhoneAllowance = new javax.swing.JLabel();
        lblClothingAllowance = new javax.swing.JLabel();
        lblGrossIncome1 = new javax.swing.JLabel();
        txtClothingAllowanceOutput = new javax.swing.JTextField();
        txtRiceSubsidyOutput = new javax.swing.JTextField();
        txtPhoneAllowanceOutput = new javax.swing.JTextField();
        txtWithholdingTaxOutput = new javax.swing.JTextField();
        lblDeductions = new javax.swing.JLabel();
        lblSSS = new javax.swing.JLabel();
        lblPhilhealth = new javax.swing.JLabel();
        lblPagIbig = new javax.swing.JLabel();
        lblWithholdingTax = new javax.swing.JLabel();
        lblTtlDeductions = new javax.swing.JLabel();
        txtTotalOutput1 = new javax.swing.JTextField();
        txtSSSOutput = new javax.swing.JTextField();
        txtPhHealthOutput = new javax.swing.JTextField();
        txtPagIbigOutput = new javax.swing.JTextField();
        txtTotalDeductionsOutput = new javax.swing.JTextField();
        lblSummary = new javax.swing.JLabel();
        lblGrossIncome = new javax.swing.JLabel();
        lblSummaryBenefits = new javax.swing.JLabel();
        lblSummaryDeductions = new javax.swing.JLabel();
        lblTakeHomePay = new javax.swing.JLabel();
        txtSummaryBenefitsOutput = new javax.swing.JTextField();
        txtGrossIncomeOutput = new javax.swing.JTextField();
        txtSummaryDeductionsOutput = new javax.swing.JTextField();
        txtTakeHomePayOutput = new javax.swing.JTextField();
        lblEmployeePayslip = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        jLabel3.setText("jLabel3");

        setMaximumSize(null);

        jPanel1.setMaximumSize(null);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane8.setMaximumSize(null);

        personalInformationTab7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        personalInformationTab7.setMaximumSize(null);

        empNumLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        empNumLabel.setText("Employee NO.");

        empNumField.setEditable(false);
        empNumField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        empNumField.setToolTipText("");
        empNumField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        empNumField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        empNumField.setEnabled(false);
        empNumField.setFocusable(false);

        nameLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nameLabel.setText("Name");

        nameField.setEditable(false);
        nameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nameField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nameField.setEnabled(false);
        nameField.setFocusable(false);

        birthdayLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        birthdayLabel.setText("Birthday");

        birthdayField.setEditable(false);
        birthdayField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        birthdayField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        birthdayField.setEnabled(false);
        birthdayField.setFocusable(false);

        addressLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addressLabel.setText("Address");

        addressField.setEditable(false);
        addressField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addressField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        addressField.setEnabled(false);
        addressField.setFocusable(false);

        phoneNoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNoLabel.setText("Phone NO.");

        phoneNoField.setEditable(false);
        phoneNoField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNoField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        phoneNoField.setEnabled(false);
        phoneNoField.setFocusable(false);

        sssLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sssLabel.setText("SSS NO.");

        sssField.setEditable(false);
        sssField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sssField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sssField.setEnabled(false);
        sssField.setFocusable(false);

        philHealthLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        philHealthLabel.setText("PhilHealth NO.");

        philHealthField.setEditable(false);
        philHealthField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        philHealthField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        philHealthField.setEnabled(false);
        philHealthField.setFocusable(false);

        tinLabel.setText("TIN NO.");

        tinField.setEditable(false);
        tinField.setToolTipText("");
        tinField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tinField.setEnabled(false);
        tinField.setFocusable(false);

        pagIbigLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pagIbigLabel.setText("Pag-IBIG NO.");

        pagIbigField.setEditable(false);
        pagIbigField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pagIbigField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pagIbigField.setEnabled(false);
        pagIbigField.setFocusable(false);

        editBtn.setBackground(new java.awt.Color(0, 51, 0));
        editBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout personalInformationTab7Layout = new javax.swing.GroupLayout(personalInformationTab7);
        personalInformationTab7.setLayout(personalInformationTab7Layout);
        personalInformationTab7Layout.setHorizontalGroup(
            personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationTab7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editBtn)
                    .addGroup(personalInformationTab7Layout.createSequentialGroup()
                        .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tinLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(philHealthLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(sssLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phoneNoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(birthdayLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(empNumLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pagIbigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tinField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(philHealthField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sssField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneNoField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthdayField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(empNumField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pagIbigField))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        personalInformationTab7Layout.setVerticalGroup(
            personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationTab7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empNumLabel)
                    .addComponent(empNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthdayLabel))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNoLabel)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sssLabel)
                    .addComponent(sssField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(philHealthLabel)
                    .addComponent(philHealthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tinLabel)
                    .addComponent(tinField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(personalInformationTab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagIbigField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pagIbigLabel))
                .addGap(20, 20, 20)
                .addComponent(editBtn)
                .addGap(115, 115, 115))
        );

        jTabbedPane8.addTab("Personal Information", personalInformationTab7);

        employmentInformationTab.setMaximumSize(null);

        statusLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        statusLabel.setText("Status");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        statusField.setToolTipText("");
        statusField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        statusField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        statusField.setEnabled(false);
        statusField.setFocusable(false);

        positionLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        positionLabel.setText("Position");

        positionField.setEditable(false);
        positionField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        positionField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        positionField.setEnabled(false);
        positionField.setFocusable(false);

        supervisorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        supervisorLabel.setText("Supervisor");

        supervisorField.setEditable(false);
        supervisorField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        supervisorField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        supervisorField.setEnabled(false);
        supervisorField.setFocusable(false);

        salaryLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salaryLabel.setText("Basic Salary");

        salaryField.setEditable(false);
        salaryField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salaryField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        salaryField.setEnabled(false);
        salaryField.setFocusable(false);

        riceLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        riceLabel.setText("Rice Subsidy");

        riceField.setEditable(false);
        riceField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        riceField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        riceField.setEnabled(false);
        riceField.setFocusable(false);

        phoneLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneLabel.setText("Phone Allowance");

        phoneField.setEditable(false);
        phoneField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        phoneField.setEnabled(false);
        phoneField.setFocusable(false);

        clothingLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clothingLabel.setText("Clothing Allowance");

        clothingField.setEditable(false);
        clothingField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clothingField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        clothingField.setEnabled(false);
        clothingField.setFocusable(false);

        editBtn1.setBackground(new java.awt.Color(0, 51, 0));
        editBtn1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editBtn1.setForeground(new java.awt.Color(255, 255, 255));
        editBtn1.setText("Edit");
        editBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout employmentInformationTabLayout = new javax.swing.GroupLayout(employmentInformationTab);
        employmentInformationTab.setLayout(employmentInformationTabLayout);
        employmentInformationTabLayout.setHorizontalGroup(
            employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employmentInformationTabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editBtn1)
                    .addGroup(employmentInformationTabLayout.createSequentialGroup()
                        .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clothingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(riceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salaryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supervisorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(positionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(phoneField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(riceField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salaryField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supervisorField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(positionField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clothingField))))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        employmentInformationTabLayout.setVerticalGroup(
            employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employmentInformationTabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel)
                    .addComponent(positionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supervisorLabel))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryLabel)
                    .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(riceLabel)
                    .addComponent(riceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(employmentInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clothingLabel)
                    .addComponent(clothingField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(editBtn1)
                .addContainerGap())
        );

        jTabbedPane8.addTab("Employment Information", employmentInformationTab);

        hoursComputationTab.setMaximumSize(null);

        labelPayCoverage.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelPayCoverage.setText("Pay Coverage");

        jcbDateRange.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jcbDateRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Pay Period..." }));

        btnCompute.setBackground(new java.awt.Color(0, 51, 0));
        btnCompute.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCompute.setForeground(new java.awt.Color(255, 255, 255));
        btnCompute.setText("Compute");
        btnCompute.setToolTipText("");
        btnCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComputeActionPerformed(evt);
            }
        });

        jScrollPane1.setMaximumSize(new java.awt.Dimension(460, 750));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(460, 750));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(460, 750));

        jPaneloverScrollPane.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPaneloverScrollPane.setMaximumSize(new java.awt.Dimension(570, 750));
        jPaneloverScrollPane.setMinimumSize(new java.awt.Dimension(570, 750));
        jPaneloverScrollPane.setPreferredSize(new java.awt.Dimension(570, 750));
        jPaneloverScrollPane.setRequestFocusEnabled(false);

        lblEarnings.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEarnings.setText("EARNINGS");

        lblMonthlyRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMonthlyRate.setText("Monthly Rate");

        lblDailyRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDailyRate.setText("Daily Rate");

        lblDaysWorked.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDaysWorked.setText("Days Worked");

        lblOvertime.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblOvertime.setText("Overtime");

        lblEarningsGrossIncome.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEarningsGrossIncome.setText("GROSS INCOME");

        lblBenefits.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblBenefits.setText("BENEFITS");

        txtMonthlyRateOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtDailyRateOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtDaysWorkedOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtEarningsGrossIncomeOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtOvertimeOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblRiceSubsidy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblRiceSubsidy.setText("Rice Subsidy");

        lblPhoneAllowance.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPhoneAllowance.setText("Phone Allowance");

        lblClothingAllowance.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblClothingAllowance.setText("Clothing Allowance");

        lblGrossIncome1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblGrossIncome1.setText("TOTAL");

        txtClothingAllowanceOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtRiceSubsidyOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtPhoneAllowanceOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtWithholdingTaxOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblDeductions.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblDeductions.setText("DEDUCTIONS");

        lblSSS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblSSS.setText("Social Security System");

        lblPhilhealth.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPhilhealth.setText("Philhealth");

        lblPagIbig.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPagIbig.setText("Pag-Ibig");

        lblWithholdingTax.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblWithholdingTax.setText("Withholding Tax");

        lblTtlDeductions.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTtlDeductions.setText("TOTAL DEDUCTIONS");

        txtTotalOutput1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSSSOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtPhHealthOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtPagIbigOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTotalDeductionsOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblSummary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSummary.setText("SUMMARY");

        lblGrossIncome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGrossIncome.setText("Gross Income");

        lblSummaryBenefits.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblSummaryBenefits.setText("Benefits");

        lblSummaryDeductions.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblSummaryDeductions.setText("Deductions");

        lblTakeHomePay.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTakeHomePay.setText("TAKE HOME PAY");

        txtSummaryBenefitsOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtGrossIncomeOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtSummaryDeductionsOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTakeHomePayOutput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblEmployeePayslip.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEmployeePayslip.setText("EMPLOYEE PAYSLIP");

        javax.swing.GroupLayout jPaneloverScrollPaneLayout = new javax.swing.GroupLayout(jPaneloverScrollPane);
        jPaneloverScrollPane.setLayout(jPaneloverScrollPaneLayout);
        jPaneloverScrollPaneLayout.setHorizontalGroup(
            jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(lblEmployeePayslip)
                .addGap(231, 231, 231))
            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneloverScrollPaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblOvertime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDaysWorked, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDailyRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMonthlyRate, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblEarningsGrossIncome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhoneAllowance, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblClothingAllowance, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblGrossIncome1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblPhilhealth)
                            .addComponent(lblSSS, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblPagIbig)
                            .addComponent(lblWithholdingTax)
                            .addComponent(lblTtlDeductions)
                            .addComponent(lblSummaryBenefits)
                            .addComponent(lblGrossIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblSummaryDeductions)
                            .addComponent(lblTakeHomePay)
                            .addComponent(lblRiceSubsidy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMonthlyRateOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtDailyRateOutput)
                            .addComponent(txtDaysWorkedOutput)
                            .addComponent(txtOvertimeOutput)
                            .addComponent(txtEarningsGrossIncomeOutput)
                            .addComponent(txtRiceSubsidyOutput)
                            .addComponent(txtPhoneAllowanceOutput)
                            .addComponent(txtClothingAllowanceOutput)
                            .addComponent(txtTotalOutput1)
                            .addComponent(txtSSSOutput)
                            .addComponent(txtPhHealthOutput)
                            .addComponent(txtPagIbigOutput)
                            .addComponent(txtWithholdingTaxOutput)
                            .addComponent(txtTotalDeductionsOutput)
                            .addComponent(txtGrossIncomeOutput)
                            .addComponent(txtSummaryBenefitsOutput)
                            .addComponent(txtSummaryDeductionsOutput)
                            .addComponent(txtTakeHomePayOutput))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                        .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                                .addComponent(lblSummary)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator4))
                            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                                .addComponent(lblDeductions)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator3))
                            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                                .addComponent(lblBenefits)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator2))
                            .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                                .addComponent(lblEarnings)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPaneloverScrollPaneLayout.setVerticalGroup(
            jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneloverScrollPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEmployeePayslip)
                .addGap(20, 20, 20)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEarnings)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonthlyRateOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonthlyRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDailyRate)
                    .addComponent(txtDailyRateOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDaysWorked)
                    .addComponent(txtDaysWorkedOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOvertime)
                    .addComponent(txtOvertimeOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEarningsGrossIncome)
                    .addComponent(txtEarningsGrossIncomeOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBenefits)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRiceSubsidy)
                    .addComponent(txtRiceSubsidyOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneAllowance)
                    .addComponent(txtPhoneAllowanceOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClothingAllowanceOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClothingAllowance))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrossIncome1)
                    .addComponent(txtTotalOutput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDeductions)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSSS)
                    .addComponent(txtSSSOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhilhealth)
                    .addComponent(txtPhHealthOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagIbig)
                    .addComponent(txtPagIbigOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWithholdingTax)
                    .addComponent(txtWithholdingTaxOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTtlDeductions)
                    .addComponent(txtTotalDeductionsOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSummary)
                    .addGroup(jPaneloverScrollPaneLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrossIncome)
                    .addComponent(txtGrossIncomeOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSummaryBenefits)
                    .addComponent(txtSummaryBenefitsOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSummaryDeductions)
                    .addComponent(txtSummaryDeductionsOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPaneloverScrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTakeHomePay)
                    .addComponent(txtTakeHomePayOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jScrollPane1.setViewportView(jPaneloverScrollPane);

        javax.swing.GroupLayout hoursComputationTabLayout = new javax.swing.GroupLayout(hoursComputationTab);
        hoursComputationTab.setLayout(hoursComputationTabLayout);
        hoursComputationTabLayout.setHorizontalGroup(
            hoursComputationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoursComputationTabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(hoursComputationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addGroup(hoursComputationTabLayout.createSequentialGroup()
                        .addComponent(labelPayCoverage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDateRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        hoursComputationTabLayout.setVerticalGroup(
            hoursComputationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoursComputationTabLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(hoursComputationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPayCoverage)
                    .addComponent(jcbDateRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompute))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Salary Computation", hoursComputationTab);

        jPanel1.add(jTabbedPane8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Computes all payroll fields for the selected pay period,
     * then displays them in the corresponding text fields.
     */
    private void btnComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputeActionPerformed
        String sel = (String) jcbDateRange.getSelectedItem();
        if (employeeTimeRecord == null) {
            JOptionPane.showMessageDialog(this, "Employee data not loaded.");
            return;
        }
        if (sel == null || !sel.contains(" to ")) {
            JOptionPane.showMessageDialog(this, "Please select a valid pay period.");
            return;
        }

        try {
            String[] parts    = sel.split(" to ");
            LocalDate start   = LocalDate.parse(parts[0], ISO_DATE);
            // (end date parsed but not used directly)
            LocalDate end     = LocalDate.parse(parts[1], ISO_DATE);

            double monthlyRate = Double.parseDouble(salaryField.getText());
            double dailyRate   = monthlyRate / start.lengthOfMonth();

            int totalHours     = employeeTimeRecord.getTotalHoursWorked(parts[0], parts[1]);
            double daysWorked  = totalHours / 8.0;
            double overtime    = totalHours % 8;
            double hourlyRate  = employeeTimeRecord.getHourlyRate();
            double grossIncome = totalHours * hourlyRate;

            double rice        = employeeTimeRecord.getRiceSubsidy();
            double phone       = employeeTimeRecord.getPhoneAllowance();
            double cloth       = employeeTimeRecord.getClothingAllowance();
            double benefits    = rice + phone + cloth;

            double sssDed      = grossIncome * 0.045;
            double phDed       = grossIncome * 0.035;
            double piDed       = grossIncome * 0.02;
            double taxDed      = grossIncome * 0.10;
            double totalDed    = sssDed + phDed + piDed + taxDed;

            double takeHome    = grossIncome + benefits - totalDed;

            // Populate UI outputs (two‐decimal format)
            txtMonthlyRateOutput.setText(String.format("%.2f", monthlyRate));
            txtDailyRateOutput.setText(  String.format("%.2f", dailyRate));
            txtDaysWorkedOutput.setText( String.format("%.2f", daysWorked));
            txtOvertimeOutput.setText(   String.format("%.2f", overtime));
            txtEarningsGrossIncomeOutput.setText(String.format("%.2f", grossIncome));

            txtRiceSubsidyOutput.setText(      String.format("%.2f", rice));
            txtPhoneAllowanceOutput.setText(   String.format("%.2f", phone));
            txtClothingAllowanceOutput.setText(String.format("%.2f", cloth));
            txtTotalOutput1.setText(           String.format("%.2f", benefits));

            txtSSSOutput.setText(            String.format("%.2f", sssDed));
            txtPhHealthOutput.setText(       String.format("%.2f", phDed));
            txtPagIbigOutput.setText(        String.format("%.2f", piDed));
            txtWithholdingTaxOutput.setText( String.format("%.2f", taxDed));
            txtTotalDeductionsOutput.setText(String.format("%.2f", totalDed));

            txtGrossIncomeOutput.setText(      String.format("%.2f", grossIncome));
            txtSummaryBenefitsOutput.setText(  String.format("%.2f", benefits));
            txtSummaryDeductionsOutput.setText(String.format("%.2f", totalDed));
            txtTakeHomePayOutput.setText(      String.format("%.2f", takeHome));

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Invalid numeric value in salary.\n" + nfe.getMessage());
        }
    }//GEN-LAST:event_btnComputeActionPerformed

    /** Opens the edit‐details dialog for this employee */
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        EditInformation editForm = new EditInformation(directoryPanel, this, employeeIndex);
        editForm.setVisible(true);
    }//GEN-LAST:event_editBtnActionPerformed

    /** Same edit handler for the Employment tab’s button */
    private void editBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtn1ActionPerformed
        EditInformation editForm = new EditInformation(directoryPanel, this, employeeIndex);
        editForm.setVisible(true);
    }//GEN-LAST:event_editBtn1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    public javax.swing.JTextField birthdayField;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JButton btnCompute;
    public javax.swing.JTextField clothingField;
    private javax.swing.JLabel clothingLabel;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton editBtn1;
    public javax.swing.JTextField empNumField;
    private javax.swing.JLabel empNumLabel;
    private javax.swing.JPanel employmentInformationTab;
    private javax.swing.JPanel hoursComputationTab;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPaneloverScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JComboBox<String> jcbDateRange;
    private javax.swing.JLabel labelPayCoverage;
    private javax.swing.JLabel lblBenefits;
    private javax.swing.JLabel lblClothingAllowance;
    private javax.swing.JLabel lblDailyRate;
    private javax.swing.JLabel lblDaysWorked;
    private javax.swing.JLabel lblDeductions;
    private javax.swing.JLabel lblEarnings;
    private javax.swing.JLabel lblEarningsGrossIncome;
    private javax.swing.JLabel lblEmployeePayslip;
    private javax.swing.JLabel lblGrossIncome;
    private javax.swing.JLabel lblGrossIncome1;
    private javax.swing.JLabel lblMonthlyRate;
    private javax.swing.JLabel lblOvertime;
    private javax.swing.JLabel lblPagIbig;
    private javax.swing.JLabel lblPhilhealth;
    private javax.swing.JLabel lblPhoneAllowance;
    private javax.swing.JLabel lblRiceSubsidy;
    private javax.swing.JLabel lblSSS;
    private javax.swing.JLabel lblSummary;
    private javax.swing.JLabel lblSummaryBenefits;
    private javax.swing.JLabel lblSummaryDeductions;
    private javax.swing.JLabel lblTakeHomePay;
    private javax.swing.JLabel lblTtlDeductions;
    private javax.swing.JLabel lblWithholdingTax;
    public javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    public javax.swing.JTextField pagIbigField;
    private javax.swing.JLabel pagIbigLabel;
    private javax.swing.JPanel personalInformationTab7;
    public javax.swing.JTextField philHealthField;
    private javax.swing.JLabel philHealthLabel;
    public javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    public javax.swing.JTextField phoneNoField;
    private javax.swing.JLabel phoneNoLabel;
    public javax.swing.JTextField positionField;
    private javax.swing.JLabel positionLabel;
    public javax.swing.JTextField riceField;
    private javax.swing.JLabel riceLabel;
    public javax.swing.JTextField salaryField;
    private javax.swing.JLabel salaryLabel;
    public javax.swing.JTextField sssField;
    private javax.swing.JLabel sssLabel;
    public javax.swing.JTextField statusField;
    private javax.swing.JLabel statusLabel;
    public javax.swing.JTextField supervisorField;
    private javax.swing.JLabel supervisorLabel;
    private javax.swing.JTextField tinField;
    private javax.swing.JLabel tinLabel;
    private javax.swing.JTextField txtClothingAllowanceOutput;
    private javax.swing.JTextField txtDailyRateOutput;
    private javax.swing.JTextField txtDaysWorkedOutput;
    private javax.swing.JTextField txtEarningsGrossIncomeOutput;
    private javax.swing.JTextField txtGrossIncomeOutput;
    private javax.swing.JTextField txtMonthlyRateOutput;
    private javax.swing.JTextField txtOvertimeOutput;
    private javax.swing.JTextField txtPagIbigOutput;
    private javax.swing.JTextField txtPhHealthOutput;
    private javax.swing.JTextField txtPhoneAllowanceOutput;
    private javax.swing.JTextField txtRiceSubsidyOutput;
    private javax.swing.JTextField txtSSSOutput;
    private javax.swing.JTextField txtSummaryBenefitsOutput;
    private javax.swing.JTextField txtSummaryDeductionsOutput;
    private javax.swing.JTextField txtTakeHomePayOutput;
    private javax.swing.JTextField txtTotalDeductionsOutput;
    private javax.swing.JTextField txtTotalOutput1;
    private javax.swing.JTextField txtWithholdingTaxOutput;
    // End of variables declaration//GEN-END:variables
}
