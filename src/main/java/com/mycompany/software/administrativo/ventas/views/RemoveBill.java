package com.mycompany.software.administrativo.ventas.views;

import com.mycompany.software.administrativo.ventas.database.BillQuery;
import com.mycompany.software.administrativo.ventas.database.ConnectionDB;
import com.mycompany.software.administrativo.ventas.tools.Product;
import com.mycompany.software.administrativo.ventas.tools.Bill;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.UIManager;

// TODO:
// 1) Manage and check data input user
// 2) Sendd to data base, related each input with jis own
public class RemoveBill extends javax.swing.JFrame {

    private ArrayList<Product> qualityProducts = new ArrayList<Product>();

    public RemoveBill() {
        initComponents();
    }

    private void setItemInQualityProducts(String name, float unitValue, int quantity) {
        qualityProducts.add(new Product(name, unitValue, quantity));
    }

    private /*public static*/ String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    private /*public static*/ String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    private void finalizeBill() throws SQLException {
        int documentUser = Integer.parseInt(JOptionPane.showInputDialog("Inserta la id unico del usuario en base de datos: "));
        int documentSeller = Integer.parseInt(JOptionPane.showInputDialog("Inserta el id unico del vendedor en base de datos: "));
        int buyOptionSelected = this.MyOptionPane();

        ConnectionDB connectionDB = new ConnectionDB();
        int idBillClientDefult = 1;
        int idBillSellerDefult = 2;
        int idBillBoxDefult = 2;
        String currentDate = this.getCurrentDate();
        String currentTime = this.getCurrentTime();
        String paymentMethod = "1";
//        String nameProduct = inputNameProduct.getText();  // se cambio la forma de la UI, ya no existe esta variable
//        float unitValue = Float.parseFloat(inputUnitaryValueProduct.getText());
        int quality = Integer.parseInt(inputIDBill.getText());
//        connectionDB.insertBill(idBillClientDefult, idBillSellerDefult, idBillBoxDefult, currentDate, currentTime, paymentMethod, nameProduct, unitValue, quality);

        //  ------------------ past code
//        // addBill to create a new bill. Needs a new Bill
//        Bill bill = new Bill(documentUser, documentSeller, getCurrentDate(), getCurrentTime());
//        // rellenar los detalles de la factura
//        BillQuery billQuery = new BillQuery();
//        // billQuery.add(Bill bill, ArrayList<Product> listProducts, int idPaymentMethod);
//        billQuery.add(bill, buyOptionSelected, qualityProducts);
    }

    public int MyOptionPane() {
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        Object[] possibilities = {"debit card", "credit card", "cash", "checks"};
        String selectedOption = (String) JOptionPane.showInputDialog(null,
                "Seleccina el metodo de pago: ", "ShowInputDialog",
                JOptionPane.PLAIN_MESSAGE, errorIcon, possibilities, "Numbers");

        int paymentMethod;
        switch (selectedOption) {
            case "debit card":
                paymentMethod = 1;
                break;
            case "credit card":
                paymentMethod = 2;
                break;
            case "cash":
                paymentMethod = 3;
                break;
            case "checks":
                paymentMethod = 4;
                break;
            default:
                paymentMethod = -1; // Valor por defecto en caso de que no se seleccione ninguna opción
                break;
        }

        System.out.println(paymentMethod);
        return paymentMethod;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        inputIDBill = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("ID bill");

        jButton2.setText("Remove Bill ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(629, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final int uniqueIDBill = Integer.parseInt(inputIDBill.getText());
        System.out.println("linea: 190. ID, elemento a eliminar en la base de dato" + uniqueIDBill);
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.deleteBillAndDetails(uniqueIDBill);
        
        // clear input boxes
        inputIDBill.setText("");
//        try {
//            this.finalizeBill();
//            // insertBill();
//        } catch (SQLException ex) {
//            Logger.getLogger(RemoveBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoveBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputIDBill;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
