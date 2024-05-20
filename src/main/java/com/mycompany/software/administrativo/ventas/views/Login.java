package com.mycompany.software.administrativo.ventas.views;

import com.mycompany.software.administrativo.ventas.model.SellerQuery;
import controller.LoginCon;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private String range;
    private int document;
    private int password;

    public Login() {
        initComponents();
    }

    public void close() {
        this.dispose(); // Cierra la ventana
    }

    private boolean checkAndCleanInputUser() {
        range = obtionRange.getSelectedItem().toString();

        // Validar que el rango no esté vacío
        if (range.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un rango");
            return false;
        }
        // Validar que el documento no esté vacío
        if (documentInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce un documento");
            return false;
        }
        // Intentar convertir el documento a un número entero
        try {
            document = Integer.parseInt(documentInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido para el documento");
            return false;
        }
        // Validar que la contraseña no esté vacía
        if (passwordInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce una contraseña");
            return false;
        }
        // Intentar convertir la contraseña a un número entero
        try {
            password = Integer.parseInt(passwordInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido para la contraseña");
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

        jLabel1.setText("Documento");

        jLabel2.setText("Contraseña");

        documentInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentInputActionPerformed(evt);
            }
        });

        jButton1.setText("Acceder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(obtionRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(documentInput, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(passwordInput)))
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(obtionRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documentInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
