package com.mycompany.software.administrativo.ventas.views;

import com.mycompany.software.administrativo.ventas.model.SellerQuery;
import controller.LoginCon;
import javax.swing.JOptionPane;

/**
 * The Login class extends javax.swing.JFrame and represents the login window of the application.
 * It contains methods for validating user input and handling the login process.
 */
public class Login extends javax.swing.JFrame {

    // storage range, document number, and password input
    private String range;
    private int document;
    private int password;

    public Login() {
        initComponents();
    }

    /**
     * Closes the current frame
     */
    public void close() {
        this.dispose(); // Cierra la ventana
    }

    /**
     * Checks and cleans the user input.
     * It validates the range, document number, and password entered by the user.
     * @return True if the input is valid, false otherwise.
     */
    private boolean checkAndCleanInputUser() {
        range = obtionRange.getSelectedItem().toString().trim();

        // Validate that the range is not empty
        if (range.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Por favor, selecciona un rango");
            return false;
        }
        // Validate that the document is not empty
        String documentInputText = documentInput.getText().trim();
        if (documentInputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un documento");
            return false;
        }
        // Try to convert the document to an integer
        try {
            document = Integer.parseInt(documentInputText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido para el documento");
            return false;
        }
        // Validate that the password is not empty
        String passwordInputText = passwordInput.getText().trim();
        if (passwordInputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce una contraseña");
            return false;
        }
        // Try to convert the password to an integer
        try {
            password = Integer.parseInt(passwordInputText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor introdusca un número valido para la contraseña");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        documentInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        obtionRange = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Documento");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Contraseña");

        documentInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentInputActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Acceder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        obtionRange.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        obtionRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Trabajador" }));
        obtionRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obtionRangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(passwordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(documentInput)
                            .addComponent(obtionRange, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(obtionRange, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documentInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

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

    private void obtionRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obtionRangeActionPerformed

    }//GEN-LAST:event_obtionRangeActionPerformed

    /**
     * Event called when the login button is clicked.
     * It checks the user input and, if valid, attempts to log in the user as a manager or seller.
     * @param evt The ActionEvent object representing the button click event.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // check inputs
        if (!checkAndCleanInputUser()) {
            return;
        }

        // show Dashboard frame
        LoginCon loginCon = new LoginCon(this); // pass the current Login object
        if (range.equals("Gerente")) {
            loginCon.accesToDashboardLikeManager(document, SellerQuery.hashPassword(Integer.toString(password)));
        } else if (range.equals("Trabajador")) {
            loginCon.accesToDashboardLikeSeller(document, SellerQuery.hashPassword(Integer.toString(password)));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void documentInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentInputActionPerformed

    }//GEN-LAST:event_documentInputActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField documentInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> obtionRange;
    private javax.swing.JTextField passwordInput;
    // End of variables declaration//GEN-END:variables
}
