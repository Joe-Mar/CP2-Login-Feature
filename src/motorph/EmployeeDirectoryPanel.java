/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Class: EmployeeDirectoryPanel
 * Description: Panel displaying a table of all employees with controls to view details
 *              or add a new employee.
 */
public class EmployeeDirectoryPanel extends JPanel {

    /** Model for loading and saving employee data from file */
    private EmployeeModelFromFile employeeModel;
    /** Index of the currently selected row in the table */
    private int selectedRow = -1;
    /** Panel for displaying detailed information on a selected employee */
    private EmployeeInformationPanel empInformationPanel;

    /**
     * Main entry point for standalone testing of this panel.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Employee Directory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(531, 445);
            frame.setLocationRelativeTo(null);          // center on screen
            frame.add(new EmployeeDirectoryPanel());
            frame.setVisible(true);
        });
    }

    /**
     * Constructor: initializes UI components, formats the table, and loads data.
     */
    public EmployeeDirectoryPanel() {
        initComponents();

        // Remove any placeholder rows
        DefaultTableModel model = (DefaultTableModel) tableDirectory.getModel();
        model.setRowCount(0);

        // Make header font bold at size 12
        tableDirectory.getTableHeader()
                      .setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));

        // Center header titles
        DefaultTableCellRenderer headerRenderer =
            (DefaultTableCellRenderer) tableDirectory.getTableHeader()
                                                     .getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Center all cell contents
        centerTableContents();

        // Populate with employee records
        loadEmployeeDataToTable();
    }

    /**
     * Centers the text within every cell of the table.
     */
    private void centerTableContents() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableDirectory.getColumnCount(); i++) {
            tableDirectory.getColumnModel()
                          .getColumn(i)
                          .setCellRenderer(centerRenderer);
        }
    }

    /** NetBeans-generated UI setup (do not modify) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionPanel = new javax.swing.JPanel();
        viewBtn = new javax.swing.JButton();
        addEmployeeBtn = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        tableDirectory = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(569, 445));
        setPreferredSize(new java.awt.Dimension(550, 445));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        viewBtn.setText("View Employee");
        viewBtn.setToolTipText("");
        viewBtn.setContentAreaFilled(true);
        viewBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewBtn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        addEmployeeBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addEmployeeBtn.setText("New Employee");
        addEmployeeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addEmployeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(addEmployeeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addComponent(viewBtn)
                .addGap(10, 10, 10)
                .addComponent(addEmployeeBtn)
                .addContainerGap())
        );

        add(actionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 60));

        scrollPane.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        tableDirectory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tableDirectory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee NO.", "Last Name", "First Name", "SSS NO.", "PhilHealth NO.", "TIN NO.", "Pag-IBIG NO."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDirectory.setToolTipText("");
        tableDirectory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableDirectory.setGridColor(new java.awt.Color(0, 0, 0));
        tableDirectory.setRowHeight(40);
        tableDirectory.setShowGrid(true);
        tableDirectory.setShowVerticalLines(false);
        tableDirectory.getTableHeader().setReorderingAllowed(false);
        tableDirectory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDirectoryMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tableDirectory);
        if (tableDirectory.getColumnModel().getColumnCount() > 0) {
            tableDirectory.getColumnModel().getColumn(0).setResizable(false);
            tableDirectory.getColumnModel().getColumn(0).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(1).setResizable(false);
            tableDirectory.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(2).setResizable(false);
            tableDirectory.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(3).setResizable(false);
            tableDirectory.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(4).setResizable(false);
            tableDirectory.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(5).setResizable(false);
            tableDirectory.getColumnModel().getColumn(5).setPreferredWidth(150);
            tableDirectory.getColumnModel().getColumn(6).setResizable(false);
            tableDirectory.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 0, 450, 445));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Remember which row was clicked by the user.
     */
    private void tableDirectoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDirectoryMouseClicked
        selectedRow = tableDirectory.getSelectedRow();
    }//GEN-LAST:event_tableDirectoryMouseClicked

    /**
     * Shows the detailed view of the selected employee,
     * or prompts the user to select one first.
     */
    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select an employee from the table first.");
            return;
        }
        empInformationPanel = new EmployeeInformationPanel(this, selectedRow);

        JFrame frame = new JFrame("Employee Record");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.add(empInformationPanel);
        frame.setVisible(true);

        empInformationPanel.loadEmployeeDataToFields(selectedRow, employeeModel);
    }//GEN-LAST:event_viewBtnActionPerformed

    /**
     * Opens the form to add a new employee.
     */
    private void addEmployeeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeBtnActionPerformed
        new AddNewEmployeeForm(this).setVisible(true);
    }//GEN-LAST:event_addEmployeeBtnActionPerformed

    /**
     * Loads all employees from file into the table.
     */
    public void loadEmployeeDataToTable(){
        DefaultTableModel model = (DefaultTableModel) tableDirectory.getModel();
        model.setRowCount(0);
        employeeModel = new EmployeeModelFromFile();
        for (Employee emp : employeeModel.getEmployeeModelList()) {
            model.addRow(new Object[]{
                emp.getEmpNo(),
                emp.getLastName(),
                emp.getFirstName(),
                emp.getSssNo(),
                emp.getPhilHealthNo(),
                emp.getTinNo(),
                emp.getPagibigNo()
            });
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton addEmployeeBtn;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableDirectory;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
