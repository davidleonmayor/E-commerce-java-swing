package com.mycompany.software.administrativo.ventas.views;

/* TODO: 
 1) when delete button is clicked and confirm to remove the seller, update the table with his own data 
 2) make an event then changes the document input list matches 
 */
import com.mycompany.software.administrativo.ventas.database.SellerQuery;
import com.mycompany.software.administrativo.ventas.tools.SellerModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Saller extends javax.swing.JPanel {

    private String documentDigits = "";
    private int documentTableSelected;

    public Saller() {
        initComponents();

        // This event is executed when a key is pressed to search this content in data base
        searchVarSeller.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                // Verifica si la tecla presionada es DELETE
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    // Verifica que la cadena tenga al menos un caracter
                    if (!documentDigits.isEmpty()) {
                        // Elimina el último caracter de la cadena
                        documentDigits = documentDigits.substring(0, documentDigits.length() - 1);
                        System.out.println("Delete key pressed, new content is: " + documentDigits);
                    }
                }

                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    documentDigits += c;

                    // Ejecutar la búsqueda solo si la cadena no está vacía
                    if (!documentDigits.isEmpty()) {
                        // Ejecutar aquí lo que quieras hacer cuando se presione una tecla numérica
                        System.out.println("Se presionó la tecla numérica: " + c);
                        SellerQuery sellertQuery;;
                        List<SellerModel> sellers;
                        try {
                            sellertQuery = new SellerQuery();
                            sellers = sellertQuery.getByDocument(Integer.parseInt(documentDigits));

                            if (sellers.isEmpty()) {
                                System.out.println("No se encontraron clientes con el documento: " + documentDigits);
                            } else {
                                System.out.println("Vendedorres encontrados con el documento: " + documentDigits);
                                for (SellerModel cli : sellers) {
                                    System.out.println("ID seller: " + cli.getId());
                                    System.out.println("Documento seller: " + cli.getDocument());
                                    System.out.println("Nombres seller: " + cli.getNames());
                                    System.out.println("Apellidos seller: " + cli.getLastNames());
                                    System.out.println("-------------------------");
                                }

// dibujar los datos en la tabla
// Crea un modelo de tabla y añade las IDs de las facturas
                                DefaultTableModel model = new DefaultTableModel();
                                model.addColumn("Documento");
                                model.addColumn("Nombres");
                                model.addColumn("Apellidos");
                                for (SellerModel cli : sellers) {
                                    model.addRow(new Object[]{cli.getDocument(), cli.getNames(), cli.getLastNames()});
                                }

                                // Establece el modelo en la tabla
                                tableViewSellerData.setModel(model);
                            }
                        } catch (SQLException err) {
                            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
                            err.printStackTrace();
                        }
                    }
                }
            }
        });

        // evento to ejecutar función cuando se hace clic en un elemento de jTable
        tableViewSellerData.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        // Obtener el ID de la factura de la fila seleccionada
                        documentTableSelected = (Integer) target.getValueAt(row, 0);
                    }
                }
            }
        });

        // check user only write numbers
        documentInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                validateNumericInput(e);
            }
        });
        // check user only write numbers
        passwordInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                validateNumericInput(e);
            }
        });
    }

    private void validateNumericInput(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE)
                || (c == KeyEvent.VK_DELETE))) {
            e.consume();  // Consumir el evento para evitar que se escriba el carácter no deseado
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        documentInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JTextField();
        lastNameInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewSellerData = new javax.swing.JTable();
        searchVarSeller = new javax.swing.JTextField();

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        documentInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentInputActionPerformed(evt);
            }
        });

        jLabel2.setText("Documento");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Apellidos");

        jLabel5.setText("Nombres");

        jButton3.setText("Crear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(documentInput)
                        .addComponent(lastNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(passwordInput, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(nameInput, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel5))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documentInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableViewSellerData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Documento", "Nombres", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableViewSellerData);

        searchVarSeller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchVarSellerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchVarSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(searchVarSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void documentInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documentInputActionPerformed

    // delete seller action
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int replyConfirmRemove = javax.swing.JOptionPane.showConfirmDialog(jPanel1, "¿Estas seguro de eliminar el vendedor?");
        if (replyConfirmRemove == JOptionPane.OK_OPTION) {
            SellerQuery sellerQuery;
            try {
                sellerQuery = new SellerQuery();
                sellerQuery.remove(documentTableSelected);
            } catch (SQLException ex) {
                Logger.getLogger(Saller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchVarSellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchVarSellerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchVarSellerActionPerformed

    // crate seller action
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // 1. catch inputs user
        String documentStr = documentInput.getText(),
                passwordStr = passwordInput.getText(),
                names = nameInput.getText(),
                lastNames = lastNameInput.getText();

        // 2. check correct data type inputs
        if (documentStr.isEmpty() || passwordStr.isEmpty() || names.isEmpty() || names.isEmpty() || lastNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Confirmación", JOptionPane.YES_NO_OPTION);
            return;
        }

        // types conversion
        int document = 0,
                password = 0;
        try {
            document = Integer.parseInt(documentStr);
            password = Integer.parseInt(passwordStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El documento y la contraseña deben ser números enteros válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // 3. Make a new sellr with the captured user data
        SellerQuery sellerQuery;
        try {
            sellerQuery = new SellerQuery();
            sellerQuery.create(document, password, names, lastNames);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        // 4. clear inputs
        documentInput.setText("");
        passwordInput.setText("");
        nameInput.setText("");
        lastNameInput.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField documentInput;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameInput;
    private javax.swing.JTextField nameInput;
    private javax.swing.JTextField passwordInput;
    private javax.swing.JTextField searchVarSeller;
    private javax.swing.JTable tableViewSellerData;
    // End of variables declaration//GEN-END:variables
}
