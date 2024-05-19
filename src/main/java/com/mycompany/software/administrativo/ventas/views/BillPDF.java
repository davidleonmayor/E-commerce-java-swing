package com.mycompany.software.administrativo.ventas.views;

/* TODO: 
 1) when delete button is clicked and confirm to remove the seller, update the table with his own data :check
 2) make an event then changes the document input list matches 
 */
import com.itextpdf.text.DocumentException;
import com.mycompany.software.administrativo.ventas.model.ConnectionDB;
import com.mycompany.software.administrativo.ventas.model.SellerQuery;
import com.mycompany.software.administrativo.ventas.tools.AbrirPDF;
import com.mycompany.software.administrativo.ventas.tools.BillSpecification;
import com.mycompany.software.administrativo.ventas.tools.MakePDF;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BillPDF extends javax.swing.JPanel {

    private String documentDigits = "";
    private int documentTableSelected;

    public BillPDF() {
        initComponents();

        // This event is executed when a key is pressed to search this content in data base
        searchVarSeller.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    documentDigits += c;

                    // Ejecutar la búsqueda solo si la cadena no está vacía
                    if (!documentDigits.isEmpty()) {
                        // Ejecutar aquí lo que quieras hacer cuando se presione una tecla numérica
                        SellerQuery sellertQuery;

                        ConnectionDB connectionDB;
                        List<Integer> bills;
                        try {
                            sellertQuery = new SellerQuery();
                            connectionDB = new ConnectionDB();
                            bills = connectionDB.filterBills(documentDigits);

                            // dibujar los datos en la tabla
                            // Crea un modelo de tabla y añade las IDs de las facturas
                            DefaultTableModel model = new DefaultTableModel();
                            model.addColumn("Documento");
                            for (Integer bill : bills) {
                                model.addRow(new Object[]{bill});
                                // Si tienes más datos para cada bill, puedes agregarlos aquí...
                            }

                            // Establece el modelo en la tabla
                            tableViewSellerData.setModel(model);
                        } catch (SQLException err) {
                            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
                            err.printStackTrace();
                        }
                    }
                }
            }

            public void keyPressed(KeyEvent e) {
                System.out.println("Has pulsado VK_DELETE o VK_BACK_SPACE ");
                if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    // Verifica que la cadena tenga al menos un caracter
                    if (!documentDigits.isEmpty()) {
                        // Elimina el último caracter de la cadena
                        documentDigits = documentDigits.substring(0, documentDigits.length() - 1);
                        System.out.println("Delete key pressed, new content is: " + documentDigits);
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
        searchVarSeller.addKeyListener(new KeyAdapter() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewSellerData = new javax.swing.JTable();
        searchVarSeller = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        tableViewSellerData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Documento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
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

        jButton2.setText("Crear PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(searchVarSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(searchVarSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
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

    private void searchVarSellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchVarSellerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchVarSellerActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // pedir los datos de la bill seleccionada
        // documentTableSelected
        ConnectionDB connectionDB;
        BillSpecification billSpecification = null;
        try {
            connectionDB = new ConnectionDB();
            billSpecification = connectionDB.getBill(documentTableSelected);

        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        // PDF
        // adding the content of "billSpecification" in the pdf
        MakePDF makePDF = new MakePDF();
        try {
            try {
                makePDF.draw(billSpecification);
            } catch (IOException ex) {
                Logger.getLogger(BillPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            AbrirPDF.abrirPDF();
        } catch (DocumentException ex) {
            Logger.getLogger(BillPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchVarSeller;
    private javax.swing.JTable tableViewSellerData;
    // End of variables declaration//GEN-END:variables
}
