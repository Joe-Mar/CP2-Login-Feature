/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import javax.swing.JOptionPane;

/*
 * Class: AddNewEmployeeForm
 * Description: GUI for capturing and validating a new employee’s details before saving.
 */
public class AddNewEmployeeForm extends javax.swing.JFrame {

    /** Reference to parent directory for table refresh */
    private EmployeeDirectoryPanel directoryPanel;

    /** Constructor that initializes the form with a directory panel reference */
    public AddNewEmployeeForm(EmployeeDirectoryPanel directoryPanel) {
        this.directoryPanel = directoryPanel;
        initComponents();
    }

    /** Default constructor that initializes the form without a directory panel reference */
    public AddNewEmployeeForm() {
        initComponents();
    }

    /** NetBeans-generated UI setup */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEmpNo2 = new javax.swing.JLabel();
        jTextFieldEmployeeNumber = new javax.swing.JTextField();
        labelEmpNo3 = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        labelEmpNo4 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        labelEmpNo5 = new javax.swing.JLabel();
        jTextFieldBirthDate = new javax.swing.JTextField();
        labelEmpNo24 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        labelEmpNo6 = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        labelEmpNo9 = new javax.swing.JLabel();
        jTextFieldSSS = new javax.swing.JTextField();
        labelEmpNo10 = new javax.swing.JLabel();
        jTextFieldPhilHealth = new javax.swing.JTextField();
        labelEmpNo11 = new javax.swing.JLabel();
        jTextFieldTIN = new javax.swing.JTextField();
        labelEmpNo12 = new javax.swing.JLabel();
        jTextFieldPagIbig = new javax.swing.JTextField();
        labelEmpNo13 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        labelEmpNo14 = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();
        labelEmpNo15 = new javax.swing.JLabel();
        jTextFieldImmediateSupervisor = new javax.swing.JTextField();
        labelEmpNo16 = new javax.swing.JLabel();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        labelEmpNo17 = new javax.swing.JLabel();
        jTextFieldRiceSubsidy = new javax.swing.JTextField();
        labelEmpNo18 = new javax.swing.JLabel();
        jTextFieldPhoneAllowance = new javax.swing.JTextField();
        labelEmpNo19 = new javax.swing.JLabel();
        jTextFieldClothingAllowance = new javax.swing.JTextField();
        labelEmpNo20 = new javax.swing.JLabel();
        jTextFieldGrossSemiMonthlyRate = new javax.swing.JTextField();
        jButtonSubmit = new javax.swing.JButton();
        labelEmpNo7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelEmpNo8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labelEmpNo22 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        labelEmpNo23 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 850));
        setMinimumSize(new java.awt.Dimension(500, 850));
        setPreferredSize(new java.awt.Dimension(500, 850));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 850));

        labelEmpNo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo2.setText("PERSONAL INFORMATION");

        jTextFieldEmployeeNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo3.setText("First Name");

        jTextFieldFirstName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo4.setText("Last Name");

        jTextFieldLastName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo5.setText("Birthday");

        jTextFieldBirthDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo24.setText("Address");

        jTextFieldAddress.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo6.setText("Phone NO.");

        jTextFieldPhoneNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEmpNo9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo9.setText("SSS NO.");

        labelEmpNo10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo10.setText("PhilHealth NO.");

        labelEmpNo11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo11.setText("TIN NO.");

        jTextFieldTIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTINActionPerformed(evt);
            }
        });

        labelEmpNo12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo12.setText("Pag-IBIG NO.");

        jTextFieldPagIbig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIbigActionPerformed(evt);
            }
        });

        labelEmpNo13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo13.setText("Status");

        labelEmpNo14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo14.setText("Position");

        labelEmpNo15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo15.setText("Immediate Supervisor");

        labelEmpNo16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo16.setText("Basic Salary");

        labelEmpNo17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo17.setText("Rice Subsidy");

        labelEmpNo18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo18.setText("Phone Allowance");

        labelEmpNo19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo19.setText("Clothing Allowance");

        labelEmpNo20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo20.setText("Gross Semi-Monthly Rate");

        jTextFieldGrossSemiMonthlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGrossSemiMonthlyRateActionPerformed(evt);
            }
        });

        jButtonSubmit.setBackground(new java.awt.Color(0, 51, 0));
        jButtonSubmit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Add Employee");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        labelEmpNo7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmpNo7.setText("Employee NO.");

        labelEmpNo8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo8.setText("GOVERNMENT BENEFITS");

        labelEmpNo22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo22.setText("EMPLOYMENT INFORMATION");

        labelEmpNo23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelEmpNo23.setText("SALARY INFORMATION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelEmpNo23)
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmpNo18)
                                    .addComponent(labelEmpNo19)
                                    .addComponent(labelEmpNo20)
                                    .addComponent(labelEmpNo17)
                                    .addComponent(labelEmpNo16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldGrossSemiMonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEmpNo5)
                                            .addComponent(labelEmpNo24)
                                            .addComponent(labelEmpNo6))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldPhoneNumber)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(jTextFieldBirthDate))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEmpNo3)
                                            .addComponent(labelEmpNo4)
                                            .addComponent(labelEmpNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldEmployeeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jTextFieldFirstName)
                                            .addComponent(jTextFieldLastName)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEmpNo10)
                                            .addComponent(labelEmpNo11)
                                            .addComponent(labelEmpNo12)
                                            .addComponent(labelEmpNo9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldSSS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPhilHealth, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldTIN, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldPagIbig))))
                                .addGap(20, 20, 20))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmpNo22)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEmpNo14)
                                            .addComponent(labelEmpNo15)
                                            .addComponent(labelEmpNo13))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jSeparator3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldPhoneAllowance, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jTextFieldRiceSubsidy)
                                            .addComponent(jTextFieldPosition)
                                            .addComponent(jTextFieldStatus)
                                            .addComponent(jTextFieldImmediateSupervisor, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldBasicSalary)
                                            .addComponent(jTextFieldClothingAllowance))
                                        .addGap(19, 19, 19))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEmpNo8)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEmpNo2)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator1)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmpNo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelEmpNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPhilHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPagIbig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmpNo22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldImmediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelEmpNo23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldGrossSemiMonthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmpNo20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButtonSubmit)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Invoked when the Add Employee button is clicked */
    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // Validate Employee Number input
        String empNoText = jTextFieldEmployeeNumber.getText().trim();
        int empNoInt;
        try {
            empNoInt = Integer.parseInt(empNoText);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric Employee Number.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        if (empNoInt >= 10001 && empNoInt <= 10034) {
            JOptionPane.showMessageDialog(
                this,
                "Employee numbers 10001–10034 are reserved. Please enter 10035 or higher.",
                "Invalid Employee Number",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
            
        // Validate Phone Number input
        String phoneText = jTextFieldPhoneNumber.getText().trim();
        if (!phoneText.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric Phone Number.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;

        }
            
        // Validate Government Benefit Numbers input
        String sssStr     = jTextFieldSSS.getText().trim();
        String philStr    = jTextFieldPhilHealth.getText().trim();
        String tinStr     = jTextFieldTIN.getText().trim();
        String pagibigStr = jTextFieldPagIbig.getText().trim();
        if (!sssStr.matches("\\d+") ||
            !philStr.matches("\\d+") ||
            !tinStr.matches("\\d+") ||
            !pagibigStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter valid numeric Government Benefit Numbers.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Populate new Employee model
        EmployeeModelFromFile model = new EmployeeModelFromFile();
        Employee newEmp = new Employee();
        newEmp.setEmpNo(empNoText);
        newEmp.setLastName(jTextFieldLastName.getText());
        newEmp.setFirstName(jTextFieldFirstName.getText());
        newEmp.setBirthday(jTextFieldBirthDate.getText());
        newEmp.setAddress(jTextFieldAddress.getText());
        newEmp.setPhoneNo(phoneText);
        newEmp.setSssNo(sssStr);
        newEmp.setPhilHealthNo(philStr);
        newEmp.setTinNo(tinStr);
        newEmp.setPagibigNo(pagibigStr);
        newEmp.setStatus(jTextFieldStatus.getText());
        newEmp.setPosition(jTextFieldPosition.getText());
        newEmp.setSupervisor(jTextFieldImmediateSupervisor.getText());

        try {
            // Parse and set salary fields
            double basicSalary        = Double.parseDouble(jTextFieldBasicSalary.getText().replace(",", ""));
            double riceSubsidy        = Double.parseDouble(jTextFieldRiceSubsidy.getText().replace(",", ""));
            double phoneAllowance     = Double.parseDouble(jTextFieldPhoneAllowance.getText().replace(",", ""));
            double clothingAllowance  = Double.parseDouble(jTextFieldClothingAllowance.getText().replace(",", ""));
            double semiMonthlyRate    = Double.parseDouble(jTextFieldGrossSemiMonthlyRate.getText().replace(",", ""));
            double hourlyRate         = semiMonthlyRate / 84.0;

            newEmp.setBasicSalary(basicSalary);
            newEmp.setRiceSubsidy(riceSubsidy);
            newEmp.setPhoneAllowance(phoneAllowance);
            newEmp.setClothingAllowance(clothingAllowance);
            newEmp.setSemiMonthlyRate(semiMonthlyRate);
            newEmp.setHourlyRate(hourlyRate);

            // Attempt to add employee
            boolean success = model.addEmployee(newEmp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Employee record added successfully.");
                if (directoryPanel != null) {
                    directoryPanel.loadEmployeeDataToTable();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add employee. Please try again.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter valid numbers in the Salary Information fields.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "An unexpected error occurred while adding the employee.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jTextFieldPagIbigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPagIbigActionPerformed
    }//GEN-LAST:event_jTextFieldPagIbigActionPerformed

    private void jTextFieldTINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTINActionPerformed
    }//GEN-LAST:event_jTextFieldTINActionPerformed

    private void jTextFieldGrossSemiMonthlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGrossSemiMonthlyRateActionPerformed
    }//GEN-LAST:event_jTextFieldGrossSemiMonthlyRateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewEmployeeForm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldBirthDate;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldEmployeeNumber;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldGrossSemiMonthlyRate;
    private javax.swing.JTextField jTextFieldImmediateSupervisor;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldPagIbig;
    private javax.swing.JTextField jTextFieldPhilHealth;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRiceSubsidy;
    private javax.swing.JTextField jTextFieldSSS;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldTIN;
    private javax.swing.JLabel labelEmpNo10;
    private javax.swing.JLabel labelEmpNo11;
    private javax.swing.JLabel labelEmpNo12;
    private javax.swing.JLabel labelEmpNo13;
    private javax.swing.JLabel labelEmpNo14;
    private javax.swing.JLabel labelEmpNo15;
    private javax.swing.JLabel labelEmpNo16;
    private javax.swing.JLabel labelEmpNo17;
    private javax.swing.JLabel labelEmpNo18;
    private javax.swing.JLabel labelEmpNo19;
    private javax.swing.JLabel labelEmpNo2;
    private javax.swing.JLabel labelEmpNo20;
    private javax.swing.JLabel labelEmpNo22;
    private javax.swing.JLabel labelEmpNo23;
    private javax.swing.JLabel labelEmpNo24;
    private javax.swing.JLabel labelEmpNo3;
    private javax.swing.JLabel labelEmpNo4;
    private javax.swing.JLabel labelEmpNo5;
    private javax.swing.JLabel labelEmpNo6;
    private javax.swing.JLabel labelEmpNo7;
    private javax.swing.JLabel labelEmpNo8;
    private javax.swing.JLabel labelEmpNo9;
    // End of variables declaration//GEN-END:variables
}
