/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oopr212.expensetrackerprogram;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class IncomesPage extends javax.swing.JFrame {
    private DefaultTableModel model1;
    /**
     * Creates new form IncomesPage
     */
    public IncomesPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back_btn = new javax.swing.JButton();
        remove_btn = new javax.swing.JButton();
        data_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Incomes");

        add_btn.setText("Add");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "income_id", "amount", "description", "date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        back_btn.setText("Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        remove_btn.setText("Remove");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });

        data_btn.setText("Get Data");
        data_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove_btn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_btn)
                    .addComponent(add_btn)
                    .addComponent(remove_btn)
                    .addComponent(data_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        dispose();
        MainPage page = new MainPage();
        page.show();
    }//GEN-LAST:event_back_btnActionPerformed

    private void data_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_btnActionPerformed
        // Connect to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "");
             Statement statement = connection.createStatement()) {
            
            // Account retrieval
            int userID = -1;
            ResultSet accountSet = statement.executeQuery("SELECT userID FROM program_user");
            if(accountSet.next()){
                userID = accountSet.getInt("userID");
            }else{
                System.err.println("No user ID found");
            }
            
            // Main Query
            ResultSet resultSet = statement.executeQuery("SELECT income_id, amount, description, date FROM `incomes` WHERE account_id = " + userID);
            
            // Create table model
            model1 = new DefaultTableModel();
            jTable1.setModel(model1);
            
            // Add columns to the table model
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model1.addColumn(metaData.getColumnName(columnIndex));
            }
            
            // Populate the table with data
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                model1.addRow(rowData);
            }
        }catch(SQLException e){
            System.err.println("Error connecting to the database" + e.getMessage());
        }
    }//GEN-LAST:event_data_btnActionPerformed

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed
        // Connect to database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker", "root", "");
             Statement statement = connection.createStatement()) {
            
            // Account retrieval
            int userID = -1;
            ResultSet accountSet = statement.executeQuery("SELECT userID FROM program_user");
            if(accountSet.next()){
                userID = accountSet.getInt("userID");
            }else{
                System.err.println("No user ID found");
            }
            
            String input = JOptionPane.showInputDialog(null,"Enter Income ID: ");
            int itemID = Integer.parseInt(input);
            
            // Remove
            int rowsAffected = statement.executeUpdate("DELETE FROM incomes WHERE expense_id = "+ itemID +";");
            System.out.println(rowsAffected + "row(s) affected");
            JOptionPane.showMessageDialog(null, "Item removed (update table to view changes)");
        }catch(SQLException e){
            System.err.println("Error connecting to the database" + e.getMessage());
        }
    }//GEN-LAST:event_remove_btnActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        // Jump to AddIncomesPage
        dispose();
        AddIncomePage page = new AddIncomePage();
        page.show();
    }//GEN-LAST:event_add_btnActionPerformed

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
            java.util.logging.Logger.getLogger(IncomesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncomesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncomesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncomesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncomesPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JButton back_btn;
    private javax.swing.JButton data_btn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton remove_btn;
    // End of variables declaration//GEN-END:variables
}
