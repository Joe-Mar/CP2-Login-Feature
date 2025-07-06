/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import javax.swing.JOptionPane;

/*
 * Class: EditInformation
 * Description: Frame for editing or deleting an existing employee’s details.
 */
public class EditInformation extends javax.swing.JFrame {

    /** Reference to the employee directory panel for table refresh */
    private final EmployeeDirectoryPanel empDir;
    /** Reference to the information panel for detail refresh */
    private final EmployeeInformationPanel empInfo;
    /** Index of the selected employee in the model list */
    private final int empIndex;
    /** The employee being edited */
    private Employee emp;

    /** 
     * Default constructor used by the GUI builder; disables action buttons. 
     */
    public EditInformation() {
        initComponents();
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        this.empDir = null;
        this.empInfo = null;
        this.empIndex = -1;
    }

    /**
     * Constructs the frame for editing a specific employee.
     * @param empDir   the directory panel to refresh upon changes
     * @param empInfo  the information panel to update details
     * @param empIndex the index of the employee to edit
     */
    public EditInformation(EmployeeDirectoryPanel empDir, EmployeeInformationPanel empInfo, int empIndex) {
        initComponents();
        this.empDir = empDir;
        this.empInfo = empInfo;
        this.empIndex = empIndex;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loadEmployeeDataToEditableFields();
    }

    /** Loads the selected employee’s data into the form fields */
    private void loadEmployeeDataToEditableFields() {
        EmployeeModelFromFile model = new EmployeeModelFromFile();
        emp = model.getEmployeeModelList()[empIndex];

        empNumField.setText(emp.getEmpNo());
        nameField.setText(emp.getFirstName() + " " + emp.getLastName());
        birthdayField.setText(emp.getBirthday());
        addressField.setText(emp.getAddress());
        phoneNoField.setText(emp.getPhoneNo());
        sssField.setText(emp.getSssNo());
        philHealthField.setText(emp.getPhilHealthNo());
        tinField.setText(emp.getTinNo());
        pagIbigField.setText(emp.getPagibigNo());
        statusField.setText(emp.getStatus());
        positionField.setText(emp.getPosition());
        supervisorField.setText(emp.getSupervisor());
        salaryField.setText(String.valueOf(emp.getBasicSalary()));
        riceField.setText(String.valueOf(emp.getRiceSubsidy()));
        phoneField.setText(String.valueOf(emp.getPhoneAllowance()));
        clothingField.setText(String.valueOf(emp.getClothingAllowance()));
    }

    /** NetBeans-generated UI setup (components and layout) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        editFormContainer = new javax.swing.JPanel();
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
        personalInfoTitle = new javax.swing.JLabel();
        personalInfoTitle1 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 650));

        jPanel1.setMaximumSize(new java.awt.Dimension(600, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 650));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(600, 650));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(600, 650));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 650));

        editFormContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editFormContainer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editFormContainer.setMaximumSize(new java.awt.Dimension(600, 650));
        editFormContainer.setMinimumSize(new java.awt.Dimension(600, 650));
        editFormContainer.setPreferredSize(new java.awt.Dimension(600, 650));

        empNumLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        empNumLabel.setText("Employee NO.");

        empNumField.setEditable(false);
        empNumField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        empNumField.setToolTipText("");
        empNumField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        empNumField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        empNumField.setMaximumSize(new java.awt.Dimension(314, 24));
        empNumField.setMinimumSize(new java.awt.Dimension(314, 24));
        empNumField.setPreferredSize(new java.awt.Dimension(314, 24));

        nameLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nameLabel.setText("Name");

        nameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nameField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nameField.setMaximumSize(new java.awt.Dimension(314, 24));
        nameField.setMinimumSize(new java.awt.Dimension(314, 24));
        nameField.setPreferredSize(new java.awt.Dimension(314, 24));

        birthdayLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        birthdayLabel.setText("Birthday");

        birthdayField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        birthdayField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        addressLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addressLabel.setText("Address");

        addressField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addressField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        addressField.setMaximumSize(new java.awt.Dimension(68, 24));

        phoneNoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNoLabel.setText("Phone NO.");

        phoneNoField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNoField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        sssLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sssLabel.setText("SSS NO.");

        sssField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sssField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sssField.setMaximumSize(new java.awt.Dimension(314, 24));
        sssField.setMinimumSize(new java.awt.Dimension(314, 24));
        sssField.setPreferredSize(new java.awt.Dimension(314, 24));

        philHealthLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        philHealthLabel.setText("PhilHealth NO.");

        philHealthField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        philHealthField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        philHealthField.setMaximumSize(new java.awt.Dimension(314, 24));
        philHealthField.setMinimumSize(new java.awt.Dimension(314, 24));
        philHealthField.setPreferredSize(new java.awt.Dimension(314, 24));

        tinLabel.setText("TIN NO.");

        tinField.setToolTipText("");
        tinField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tinField.setMaximumSize(new java.awt.Dimension(314, 24));
        tinField.setMinimumSize(new java.awt.Dimension(314, 24));
        tinField.setPreferredSize(new java.awt.Dimension(314, 24));

        pagIbigLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pagIbigLabel.setText("Pag-IBIG NO. ");

        pagIbigField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pagIbigField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pagIbigField.setMaximumSize(new java.awt.Dimension(314, 24));
        pagIbigField.setMinimumSize(new java.awt.Dimension(314, 24));
        pagIbigField.setPreferredSize(new java.awt.Dimension(314, 24));

        statusLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        statusLabel.setText("Status");

        statusField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        statusField.setToolTipText("");
        statusField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        statusField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        statusField.setMaximumSize(new java.awt.Dimension(314, 24));
        statusField.setMinimumSize(new java.awt.Dimension(314, 24));
        statusField.setPreferredSize(new java.awt.Dimension(314, 24));

        positionLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        positionLabel.setText("Position");

        positionField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        positionField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        positionField.setMaximumSize(new java.awt.Dimension(314, 24));
        positionField.setMinimumSize(new java.awt.Dimension(314, 24));
        positionField.setPreferredSize(new java.awt.Dimension(314, 24));

        supervisorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        supervisorLabel.setText("Supervisor");

        supervisorField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        supervisorField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        supervisorField.setMaximumSize(new java.awt.Dimension(314, 24));
        supervisorField.setMinimumSize(new java.awt.Dimension(314, 24));
        supervisorField.setPreferredSize(new java.awt.Dimension(314, 24));

        salaryLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salaryLabel.setText("Basic Salary");

        salaryField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salaryField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        salaryField.setMaximumSize(new java.awt.Dimension(314, 24));
        salaryField.setMinimumSize(new java.awt.Dimension(314, 24));
        salaryField.setPreferredSize(new java.awt.Dimension(314, 24));

        riceLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        riceLabel.setText("Rice Subsidy");

        riceField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        riceField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        riceField.setMaximumSize(new java.awt.Dimension(314, 24));
        riceField.setMinimumSize(new java.awt.Dimension(314, 24));
        riceField.setPreferredSize(new java.awt.Dimension(314, 24));

        phoneLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneLabel.setText("Phone Allowance");

        phoneField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        phoneField.setMaximumSize(new java.awt.Dimension(314, 24));
        phoneField.setMinimumSize(new java.awt.Dimension(314, 24));
        phoneField.setPreferredSize(new java.awt.Dimension(314, 24));

        clothingLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clothingLabel.setText("Clothing Allowance");

        clothingField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clothingField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        clothingField.setMaximumSize(new java.awt.Dimension(314, 24));
        clothingField.setMinimumSize(new java.awt.Dimension(314, 24));
        clothingField.setPreferredSize(new java.awt.Dimension(314, 24));

        personalInfoTitle.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        personalInfoTitle.setText("PERSONAL INFORMATION");

        personalInfoTitle1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        personalInfoTitle1.setText("EMPLOYMENT INFORMATION");

        updateBtn.setBackground(new java.awt.Color(0, 51, 0));
        updateBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(153, 0, 0));
        deleteBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editFormContainerLayout = new javax.swing.GroupLayout(editFormContainer);
        editFormContainer.setLayout(editFormContainerLayout);
        editFormContainerLayout.setHorizontalGroup(
            editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editFormContainerLayout.createSequentialGroup()
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editFormContainerLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn))
                    .addGroup(editFormContainerLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editFormContainerLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editFormContainerLayout.createSequentialGroup()
                                        .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(clothingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                            .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(riceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(salaryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(supervisorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(positionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(100, 100, 100)
                                        .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(riceField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(phoneField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(positionField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(supervisorField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(salaryField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(statusField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(clothingField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(editFormContainerLayout.createSequentialGroup()
                                        .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(empNumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(birthdayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(phoneNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(sssLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(philHealthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                            .addComponent(tinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pagIbigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(100, 100, 100)
                                        .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tinField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(philHealthField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(sssField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(phoneNoField)
                                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(birthdayField)
                                            .addComponent(addressField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pagIbigField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(empNumField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 20, Short.MAX_VALUE))
                            .addGroup(editFormContainerLayout.createSequentialGroup()
                                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(editFormContainerLayout.createSequentialGroup()
                                        .addComponent(personalInfoTitle)
                                        .addGap(10, 10, 10)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editFormContainerLayout.createSequentialGroup()
                                        .addComponent(personalInfoTitle1)
                                        .addGap(10, 10, 10)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)))))
                .addGap(21, 21, 21))
        );
        editFormContainerLayout.setVerticalGroup(
            editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editFormContainerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personalInfoTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empNumLabel)
                    .addComponent(empNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthdayLabel))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNoLabel)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sssLabel)
                    .addComponent(sssField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(philHealthLabel)
                    .addComponent(philHealthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tinLabel)
                    .addComponent(tinField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagIbigLabel)
                    .addComponent(pagIbigField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(personalInfoTitle1)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel)
                    .addComponent(positionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supervisorLabel))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryLabel)
                    .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(riceLabel)
                    .addComponent(riceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clothingLabel)
                    .addComponent(clothingField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(editFormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(deleteBtn))
                .addGap(51, 51, 51))
        );

        jScrollPane1.setViewportView(editFormContainer);

        jPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Action listeners wired to helper methods
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        onUpdate();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        onDelete();
    }//GEN-LAST:event_deleteBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditInformation().setVisible(true);
            }
        });
    }

    /** Performs validation and updates the employee record */
     private void onUpdate() {
        try {
            // Populate employee object from form fields
            emp.setEmpNo(empNumField.getText().trim());
            String[] parts = nameField.getText().trim().split("\\s+", 2);
            emp.setFirstName(parts.length > 0 ? parts[0] : "");
            emp.setLastName(parts.length > 1 ? parts[1] : "");
            emp.setBirthday(birthdayField.getText().trim());
            emp.setAddress(addressField.getText().trim());
            emp.setPhoneNo(phoneNoField.getText().trim());
            emp.setSssNo(sssField.getText().trim());
            emp.setPhilHealthNo(philHealthField.getText().trim());
            emp.setTinNo(tinField.getText().trim());
            emp.setPagibigNo(pagIbigField.getText().trim());
            emp.setStatus(statusField.getText().trim());
            emp.setPosition(positionField.getText().trim());
            emp.setSupervisor(supervisorField.getText().trim());
            emp.setBasicSalary(Double.parseDouble(salaryField.getText().trim()));
            emp.setRiceSubsidy(Double.parseDouble(riceField.getText().trim()));
            emp.setPhoneAllowance(Double.parseDouble(phoneField.getText().trim()));
            emp.setClothingAllowance(Double.parseDouble(clothingField.getText().trim()));

            // Update model
            EmployeeModelFromFile model = new EmployeeModelFromFile();
            boolean ok = model.updateEmployee(empIndex, emp);
            if (!ok) {
                JOptionPane.showMessageDialog(this,
                    "Failed to write changes to file.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Refresh UI
            empDir.loadEmployeeDataToTable();
            if (empInfo != null) {
                empInfo.loadEmployeeDetails(empIndex);
            }
            JOptionPane.showMessageDialog(this,
                "Employee record updated!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers in the salary/allowance fields.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Update failed: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /** Confirms and deletes the employee record */
     private void onDelete() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this record?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                EmployeeModelFromFile model = new EmployeeModelFromFile();
                if (model.deleteEmployee(empIndex)) {
                    JOptionPane.showMessageDialog(this,
                        "Record successfully deleted.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    empDir.loadEmployeeDataToTable();
                    if (empInfo != null) {
                        empInfo.clearFields();
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Failed to delete employee.",
                        "Delete Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error during delete: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    public javax.swing.JTextField birthdayField;
    private javax.swing.JLabel birthdayLabel;
    public javax.swing.JTextField clothingField;
    private javax.swing.JLabel clothingLabel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JPanel editFormContainer;
    public javax.swing.JTextField empNumField;
    private javax.swing.JLabel empNumLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    public javax.swing.JTextField pagIbigField;
    private javax.swing.JLabel pagIbigLabel;
    private javax.swing.JLabel personalInfoTitle;
    private javax.swing.JLabel personalInfoTitle1;
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
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
