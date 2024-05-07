package com.mycompany.software.administrativo.ventas.views;

import com.mycompany.software.administrativo.ventas.database.ConnectionDB;
import com.mycompany.software.administrativo.ventas.tools.Product;
import com.mycompany.software.administrativo.ventas.tools.BillSpecification;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

// TODO:
// 1) Manage and check data input user
// 2) Sendd to data base, related each input with jis own
public class ListBill extends javax.swing.JFrame {

    private ArrayList<Product> qualityProducts = new ArrayList<Product>();

    public ListBill() {
        initComponents();

//        GUI styles
        containerContentBillSelected.setLayout(new BorderLayout());
        containerContentBillSelected.add(new JScrollPane(tableShowIDBills), BorderLayout.EAST);

// evento to ejecutar función cuando se hace clic en un elemento de jTable
        tableShowIDBills.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        // cliar bill content to draw the new bill selected
                        noSeNombre.setText("");
                        productsBillSpecification.removeAll();

                        // Obtener el ID de la factura de la fila seleccionada
                        int idBill = (Integer) target.getValueAt(row, 0);

                        // Recuperar los detalles de la factura
                        ConnectionDB connectionDB = new ConnectionDB();
                        BillSpecification billSpecification = null;
                        try {
                            billSpecification = connectionDB.getBill(idBill);
                            System.out.println("--------here is working the getBill method--------");

                            // Ahora puedes usar el objeto billSpecification para acceder a los datos de la factura
                            if (billSpecification != null) {
                                String billDetails = /* "Factura ID: " + billSpecification.getId() 
                                    + */ "\nCliente: " + billSpecification.getClientName() + " " + billSpecification.getClientLastName()
                                        + "\nVendedor: " + billSpecification.getSellerName() + " " + billSpecification.getSellerLastName()
                                        + "\nNúmero de Caja: " + billSpecification.getBoxNumber()
                                        + "\nFecha: " + billSpecification.getFecha()
                                        + "\nHora: " + billSpecification.getHora() /* + "\nProductos:\n" */;

                                for (Product product : billSpecification.getProducts()) {
                                    // Crea una nueva instancia de ContainerProductEspesification con los valores ingresados
                                    ContainerProductEspesification panel = new ContainerProductEspesification(product.getProductName(), product.getUnitValue(), product.getQuantity());
                                    // Agrega el panel a productsBillSpecification
                                    productsBillSpecification.add(panel);
                                }

                                billDetails += "Método de pago: " + billSpecification.getPaymentMethod();
                                noSeNombre.setText(billDetails);
                            } else {
                                System.out.println("No se encontró ninguna factura con el ID: " + idBill);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
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
        containerFilterVar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputIDBill = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        containerContentBillSelected = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productsBillSpecification = new javax.swing.JPanel();
        noSeNombre = new javax.swing.JLabel();
        containerShowBills = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableShowIDBills = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        containerFilterVar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID bill");

        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerFilterVarLayout = new javax.swing.GroupLayout(containerFilterVar);
        containerFilterVar.setLayout(containerFilterVarLayout);
        containerFilterVarLayout.setHorizontalGroup(
            containerFilterVarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerFilterVarLayout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addGroup(containerFilterVarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        containerFilterVarLayout.setVerticalGroup(
            containerFilterVarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerFilterVarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerFilterVarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(inputIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        productsBillSpecification.setLayout(new javax.swing.BoxLayout(productsBillSpecification, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(productsBillSpecification);

        noSeNombre.setText("jLabel1");

        javax.swing.GroupLayout containerContentBillSelectedLayout = new javax.swing.GroupLayout(containerContentBillSelected);
        containerContentBillSelected.setLayout(containerContentBillSelectedLayout);
        containerContentBillSelectedLayout.setHorizontalGroup(
            containerContentBillSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerContentBillSelectedLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(containerContentBillSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(noSeNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        containerContentBillSelectedLayout.setVerticalGroup(
            containerContentBillSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerContentBillSelectedLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(noSeNombre)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        containerShowBills.setLayout(new java.awt.BorderLayout());

        tableShowIDBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "identificador de factura"
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
        jScrollPane1.setViewportView(tableShowIDBills);

        containerShowBills.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerFilterVar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(containerContentBillSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(containerShowBills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(containerFilterVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerContentBillSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerShowBills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void realizeSpesificationBillSQL() throws SQLException {
        // Prepara los datos para la consulta SQL
        final String inputIDBillFilter = inputIDBill.getText().trim();
        // Limpia las cajas de entrada
        inputIDBill.setText("");
        System.out.println("Linea 206. id para listar elemento: " + inputIDBillFilter);
        System.out.println("---------------------------------check--------------");
        ConnectionDB connectionDB = new ConnectionDB();
        List<Integer> billIds = connectionDB.filterBills(inputIDBillFilter);

        // Crea un modelo de tabla y añade las IDs de las facturas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID de la factura");
        for (Integer id : billIds) {
            model.addRow(new Object[]{id});
        }

        // Establece el modelo en la tabla
        tableShowIDBills.setModel(model);

//        // prepare data to SQL query
//        final int inputIDBillToDelete = Integer.parseInt(inputIDBill.getText().trim());
//        // clear input boxes
//        inputIDBill.setText("");
//        System.out.println("Linea 206. id para listar elemento: " + inputIDBillToDelete);
//        System.out.println("---------------------------------check--------------");
//        // execute query
//        ConnectionDB connectionDB = new ConnectionDB();
//        BillSpecification billSpecification = connectionDB.getBill(inputIDBillToDelete);
//
//        // Ahora puedes usar el objeto billSpecification para acceder a los datos de la factura
//        if (billSpecification != null) {
//            String billDetails = "Factura ID: " + billSpecification.getId()
//                    + "\nCliente: " + billSpecification.getClientName() + " " + billSpecification.getClientLastName()
//                    + "\nVendedor: " + billSpecification.getSellerName() + " " + billSpecification.getSellerLastName()
//                    + "\nNúmero de Caja: " + billSpecification.getBoxNumber()
//                    + "\nFecha: " + billSpecification.getFecha()
//                    + "\nHora: " + billSpecification.getHora()
//                    + "\nProducto: " + billSpecification.getProductName()
//                    + ", Valor unitario: " + billSpecification.getUnitValue()
//                    + ", Cantidad: " + billSpecification.getQuantity()
//                    + ", Método de pago: " + billSpecification.getPaymentMethod()
//                    + "\n--------------------";
//            productsBill.setText(billDetails);
//        } else {
//            System.out.println("No se encontró ninguna factura con el ID: " + inputIDBillToDelete);
//        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            this.realizeSpesificationBillSQL();
        } catch (SQLException ex) {
            Logger.getLogger(ListBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerContentBillSelected;
    private javax.swing.JPanel containerFilterVar;
    private javax.swing.JPanel containerShowBills;
    private javax.swing.JTextField inputIDBill;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel noSeNombre;
    private javax.swing.JPanel productsBillSpecification;
    private javax.swing.JTable tableShowIDBills;
    // End of variables declaration//GEN-END:variables
}
