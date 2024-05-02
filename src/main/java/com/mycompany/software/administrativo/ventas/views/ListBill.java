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
public class ListBill extends javax.swing.JFrame {

    private ArrayList<Product> qualityProducts = new ArrayList<Product>();

    public ListBill() {
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
        String nameProduct = inputIDBill.getText();
//        float unitValue = Float.parseFloat(inputUnitaryValueProduct.getText());
//        int quality = Integer.parseInt(inputQualityProduct.getText());
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
        jLabel2 = new javax.swing.JLabel();
        inputIDBill = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        scrollProductsPane = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID bill");

        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollProductsPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        scrollProductsPane.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(scrollProductsPane))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollProductsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        final int inputIDBillToDelete = Integer.parseInt(inputIDBill.getText());
        System.out.println("Linea 206. id para listar elemento: " + inputIDBillToDelete);
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.getBill(inputIDBillToDelete);
        
        // clear input boxes
        inputIDBill.setText("");
//        try {
//            this.finalizeBill();
//            // insertBill();
//        } catch (SQLException ex) {
//            Logger.getLogger(ListBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputIDBill;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane scrollProductsPane;
    // End of variables declaration//GEN-END:variables
}
