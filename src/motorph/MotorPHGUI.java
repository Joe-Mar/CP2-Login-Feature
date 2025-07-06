/*
 * Programmer: A1101 GROUP 4 | Armilla, A., Belga, E., Franco, C., Jardeliza, L., Lasic, J.
 * Date: June 2025
 * Project: MotorPH Payroll System
 */

package motorph;

import java.awt.CardLayout;
import javax.swing.JFrame;

/**
 * Class: MotorPHGUI
 * Description: Main application frame that manages navigation between
 *              different functional panels (Directory, Net Salary, Hours Worked, Exit)
 *              using a CardLayout.
 */
public class MotorPHGUI extends javax.swing.JFrame {

    private BlankPanel blankPanel;
    private EmployeeDirectoryPanel empDirPanel;
    private NetSalaryPanel netSalaryPanel;
    private HoursWorkedPanel hoursWorkedPanel;
    private ExitPanel exitPanel;
    
    /**
     * Constructor: Initializes the GUI, instantiates all sub-panels,
     *              adds them to the card panel, and shows the default blank panel.
     */
    public MotorPHGUI() {
       initComponents();

        // Instantiate all panels once and add them to panelCard
        blankPanel       = new BlankPanel();
        empDirPanel      = new EmployeeDirectoryPanel();
        netSalaryPanel   = new NetSalaryPanel();
        hoursWorkedPanel = new HoursWorkedPanel();
        exitPanel        = new ExitPanel();

        // By default, show the blank panel and set subheader text
        CardLayout cl = (CardLayout) panelCard.getLayout();
        cl.show(panelCard, "BLANK");
        labelSubHeader.setText("Good day!");

        // Add all cards to panelCard with unique names
        panelCard.add(blankPanel,       "BLANK");
        panelCard.add(empDirPanel,      "EMP_DIR");
        panelCard.add(netSalaryPanel,   "NET_SALARY");
        panelCard.add(hoursWorkedPanel, "HOURS_WORKED");
        panelCard.add(exitPanel,        "EXIT_PANEL");
    }

    /** NetBeans-generated UI setup (components and layout) */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        panelSidebar = new javax.swing.JPanel();
        labelMainMenu = new javax.swing.JLabel();
        buttonEmpDirect = new javax.swing.JButton();
        buttonNetSalary = new javax.swing.JButton();
        buttonWorkHours = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        panelSubHeader = new javax.swing.JPanel();
        labelSubHeader = new javax.swing.JLabel();
        panelFooter = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        panelCard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MotorPH Payroll Management System");
        setBackground(new java.awt.Color(255, 255, 255));

        lblHeader.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblHeader.setText("MotorPH Payroll Management System");

        panelSidebar.setBackground(new java.awt.Color(0, 0, 0));

        labelMainMenu.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        labelMainMenu.setForeground(new java.awt.Color(255, 255, 255));
        labelMainMenu.setText("Main Menu");

        buttonEmpDirect.setBackground(new java.awt.Color(51, 51, 51));
        buttonEmpDirect.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonEmpDirect.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmpDirect.setText("Employee Directory");
        buttonEmpDirect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmpDirectActionPerformed(evt);
            }
        });

        buttonNetSalary.setBackground(new java.awt.Color(51, 51, 51));
        buttonNetSalary.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonNetSalary.setForeground(new java.awt.Color(255, 255, 255));
        buttonNetSalary.setText("Compute Net Salary");
        buttonNetSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNetSalaryActionPerformed(evt);
            }
        });

        buttonWorkHours.setBackground(new java.awt.Color(51, 51, 51));
        buttonWorkHours.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonWorkHours.setForeground(new java.awt.Color(255, 255, 255));
        buttonWorkHours.setText("Compute Salary Based on Work Hours");
        buttonWorkHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWorkHoursActionPerformed(evt);
            }
        });

        buttonExit.setBackground(new java.awt.Color(51, 51, 51));
        buttonExit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonExit.setForeground(new java.awt.Color(255, 255, 255));
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSidebarLayout.createSequentialGroup()
                        .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSidebarLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labelMainMenu))
                            .addGroup(panelSidebarLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(buttonEmpDirect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonNetSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonWorkHours, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSidebarLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(buttonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelMainMenu)
                .addGap(26, 26, 26)
                .addComponent(buttonEmpDirect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonNetSalary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonWorkHours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                .addComponent(buttonExit)
                .addContainerGap())
        );

        panelSubHeader.setBackground(new java.awt.Color(153, 0, 0));

        labelSubHeader.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        labelSubHeader.setForeground(new java.awt.Color(255, 255, 255));
        labelSubHeader.setText("SubHeader");

        javax.swing.GroupLayout panelSubHeaderLayout = new javax.swing.GroupLayout(panelSubHeader);
        panelSubHeader.setLayout(panelSubHeaderLayout);
        panelSubHeaderLayout.setHorizontalGroup(
            panelSubHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubHeaderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelSubHeader)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        panelSubHeaderLayout.setVerticalGroup(
            panelSubHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubHeaderLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelSubHeader)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelFooter.setBackground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout panelFooterLayout = new javax.swing.GroupLayout(panelFooter);
        panelFooter.setLayout(panelFooterLayout);
        panelFooterLayout.setHorizontalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFooterLayout.setVerticalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        panelMain.setBackground(new java.awt.Color(153, 153, 153));

        panelCard.setBackground(new java.awt.Color(255, 255, 255));
        panelCard.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCard, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFooter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSubHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHeader)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSubHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(panelFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handler for the "Employee Directory" button.
     * Switches the card to the Employee Directory panel and updates subheader.
     */
    private void buttonEmpDirectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmpDirectActionPerformed
        CardLayout cl = (CardLayout) panelCard.getLayout();
        cl.show(panelCard, "EMP_DIR");
        labelSubHeader.setText("Employee Directory");
    }//GEN-LAST:event_buttonEmpDirectActionPerformed
    
    /**
     * Handler for the "Compute Net Salary" button.
     * Switches the card to the Net Salary panel and updates subheader.
     */
    private void buttonNetSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNetSalaryActionPerformed
        CardLayout cl = (CardLayout) panelCard.getLayout();
        cl.show(panelCard, "NET_SALARY");
        labelSubHeader.setText("Compute Net Salary");
    }//GEN-LAST:event_buttonNetSalaryActionPerformed
    
    /**
     * Handler for the "Compute Salary Based on Work Hours" button.
     * Switches the card to the Hours Worked panel and updates subheader.
     */
    private void buttonWorkHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWorkHoursActionPerformed
        CardLayout cl = (CardLayout) panelCard.getLayout();
        cl.show(panelCard, "HOURS_WORKED");
        labelSubHeader.setText("Compute Salary Based on Work Hours");
    }//GEN-LAST:event_buttonWorkHoursActionPerformed

    /**
     * Handler for the "Exit" button.
     * Clears and re-instantiates all panels to reset their state,
     * then displays the Exit panel.
     */
    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // Remove all existing cards
        panelCard.removeAll();

        // Re-instantiate each panel to reset fields
        blankPanel       = new BlankPanel();
        empDirPanel      = new EmployeeDirectoryPanel();
        netSalaryPanel   = new NetSalaryPanel();
        hoursWorkedPanel = new HoursWorkedPanel();
        exitPanel        = new ExitPanel();

        // Re-add cards under the same identifiers
        panelCard.add(blankPanel,       "BLANK");
        panelCard.add(empDirPanel,      "EMP_DIR");
        panelCard.add(netSalaryPanel,   "NET_SALARY");
        panelCard.add(hoursWorkedPanel, "HOURS_WORKED");
        panelCard.add(exitPanel,        "EXIT_PANEL");

        // Refresh the card layout
        panelCard.revalidate();
        panelCard.repaint();

        // Show the Exit panel and update subheader
        CardLayout cl = (CardLayout) panelCard.getLayout();
        cl.show(panelCard, "EXIT_PANEL");
        labelSubHeader.setText("Exit");
    }//GEN-LAST:event_buttonExitActionPerformed

    /**
     * Main entry point: Sets the look and feel, then creates and displays
     * the main MotorPHGUI frame.
     * @param args command line arguments (unused)
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
            java.util.logging.Logger.getLogger(MotorPHGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MotorPHGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MotorPHGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MotorPHGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MotorPHGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEmpDirect;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNetSalary;
    private javax.swing.JButton buttonWorkHours;
    private javax.swing.JLabel labelMainMenu;
    private javax.swing.JLabel labelSubHeader;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubHeader;
    // End of variables declaration//GEN-END:variables
}
