package com.mycompany.software.administrativo.ventas.views;

/* TODO
DONE: refactorizar y validar la toma de datos del usuario
1) ralizar el correcto funcionamiento de la facura, pasandole no el ID de seller, client, Dandole el documento.
2) refactorizar la creacion de la factura utilizando MVC,
3) split function then can go in tool package
 */
import com.mycompany.software.administrativo.ventas.model.ConnectionDB;
import com.mycompany.software.administrativo.ventas.tools.Product;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Extends javax.swing.JFrame and represents the frame then contains the feature
 * to crate a bill
 */
public class CreateBill extends javax.swing.JFrame {

    // A list of quality products to be added to the bill.
    private List<Product> qualityProducts = new ArrayList<>();

    // variables to check input user 
    private String nameProductInput;
    private float unitValueInput;
    private int quantityInput;

    // metadata to end bill
    final int idBillBoxDefult = 2;
    final int documentSeller = 16; // Integer.parseInt(JOptionPane.showInputDialog("Inserta el id unico del vendedor en base de datos: "));
    final int idBillSellerDefult = 2; // deveria benir desde el logeado

    /**
     * Checks and cleans the user input. It validates the range, document
     * number, and password entered by the user.
     *
     * @return True if the input is valid, false otherwise.
     */
    private boolean checkAndCleanInputUser() {
        // Validate that the range is not empty
        nameProductInput = inputNameProduct.getText().trim();
        if (nameProductInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un nombre");
            return false;
        }
        // Regular expressionue check string no contain numbers and rare characters
        String regex = "^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]+$";
        if (!nameProductInput.matches(regex)) {
            JOptionPane.showMessageDialog(this, "El nombre no debe contener números ni caracteres especiales");
            return false;
        }
        // Validate that the unitValue is not empty
        String unitValueInputStr = inputUnitaryValueProduct.getText().trim();
        // Float unitValueInput = Float.parseFloat(inputUnitaryValueProduct.getText().trim());
        if (unitValueInputStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un valor unitario");
            return false;
        }
        // Try to convert the document to an integer
        try {
            unitValueInput = Float.parseFloat(inputUnitaryValueProduct.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido para el valor unitario");
            return false;
        }
        // Validate that the quality is not empty
        String quantityInputStr = inputQualityProduct.getText().trim();
        if (quantityInputStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce una cantidad");
            return false;
        }
        // Try to convert the quantity to an integer
        try {
            quantityInput = Integer.parseInt(quantityInputStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor introdusca un número valido para la cantidad");
            return false;
        }

        return true;
    }

    /**
     * This method adds a product to the list of quality products.
     *
     * @param name The name of the product.
     * @param unitValue The unit value of the product.
     * @param quantity The quantity of the product.
     */
    private void setItemInQualityProducts(String name, float unitValue, int quantity) {
        qualityProducts.add(new Product(name, unitValue, quantity));
    }

    /**
     * Returns the current date as a string in the format "yyyy-MM-dd".
     *
     * @return A String representing the current date.
     */
    private /*public static*/ String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    /**
     * Returns the current time as a string in the format "HH:mm:ss".
     *
     * @return The current time as a string.
     */
    private /*public static*/ String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    /**
     * Solicita al usuario que introduzca un número entero. Si el usuario
     * introduce algo que no es un número entero válido, se le seguirá pidiendo
     * que introduzca un número hasta que lo haga correctamente.
     *
     * @param message El mensaje que se mostrará al usuario cuando se le
     * solicite que introduzca un número.
     * @return El número entero introducido por el usuario.
     */
    private int getIntegerFromUser(String message) {
        while (true) {
            String userInput = JOptionPane.showInputDialog(message);
            try {
                int number = Integer.parseInt(userInput);
                if (number < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número positivo");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido");
            }
        }
    }

    public CreateBill() {
        initComponents();

        // Configurar el comportamiento de cierre
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public String MyOptionPane() {
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        Object[] possibilities = {"Tarjeta de débito", "Tarjeta de crédito", "Efectivo", "Cheques"};
        String selectedOption = (String) JOptionPane.showInputDialog(null,
                "Seleccina el metodo de pago: ", "ShowInputDialog",
                JOptionPane.PLAIN_MESSAGE, errorIcon, possibilities, "Numbers");

        String res = switch (selectedOption) {
            case "Tarjeta de débito" ->
                "1";
            case "Tarjeta de crédito" ->
                "2";
            case "Efectivo" ->
                "3";
            case "Cheques" ->
                "4";
            default ->
                "-1"; // Valor por defecto en caso de que no se seleccione ninguna opción
        };

        System.out.println("res" + res);
        return res;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputNameProduct = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputUnitaryValueProduct = new javax.swing.JTextField();
        inputQualityProduct = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        scrollProductsPane = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nombre Producto");

        jLabel3.setText("Valor Unitario");

        jLabel4.setText("Cantidad");

        jButton2.setText("Done Bill ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(inputNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(inputUnitaryValueProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputQualityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputUnitaryValueProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputQualityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        scrollProductsPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));
        scrollProductsPane.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollProductsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollProductsPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    /**
     * Click event button then checks the get metadata user and product to the
     * list of products, creates a new bill
     *
     * @param evt The ActionEvent object representing the button click event.
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // 1) input data user like document ETC
//        final int documentUser = getIntegerFromUser("Inserta la id unico del usuario en base de datos: ");
        final int idBillClientDefult = getIntegerFromUser("Inserta la id unico del cliente en base de datos: ");
        final int idBillSellertDefult = getIntegerFromUser("Inserta la id unico del vendedor en base de datos: ");
        final String buyOptionSelected = MyOptionPane();
        final String paymentMethod = "1"; // if work can remive this line
        // date 
        String currentDate = this.getCurrentDate();
        String currentTime = this.getCurrentTime();

        // 2) execute SQL query
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.insertBill(idBillClientDefult, idBillSellertDefult, idBillBoxDefult, currentDate, currentTime, buyOptionSelected, qualityProducts);

        // 3) clear input boxes
        inputNameProduct.setText("");
        inputUnitaryValueProduct.setText("");
        inputQualityProduct.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Click event button then checks the user input, adds a product to the list
     * of products, creates and adds a new panel to jPanel3, and clears the
     * input fields.
     *
     * @param evt The ActionEvent object representing the button click event.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // 1) check correct data 
        if (!checkAndCleanInputUser()) {
            return;
        }

        // 2) agregar producto a la lista de productos que se pasa al generar la bill
        qualityProducts.add(new Product(nameProductInput, unitValueInput, quantityInput));
        // Crea una nueva instancia de ContainerProductEspesification con los valores ingresados
        ContainerProductEspesification panel = new ContainerProductEspesification(nameProductInput, unitValueInput, quantityInput);
        jPanel3.add(panel); // Agrega el panel a jPanel3
        // Actualiza jPanel3 para mostrar el nuevo panel
        jPanel3.revalidate();
        jPanel3.repaint();

        // 3) Limpia los campos de entrada para la próxima entrada
        inputNameProduct.setText("");
        inputUnitaryValueProduct.setText("");
        inputQualityProduct.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputNameProduct;
    private javax.swing.JTextField inputQualityProduct;
    private javax.swing.JTextField inputUnitaryValueProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane scrollProductsPane;
    // End of variables declaration//GEN-END:variables
}
