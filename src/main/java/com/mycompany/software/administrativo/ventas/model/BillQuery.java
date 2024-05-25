package com.mycompany.software.administrativo.ventas.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mycompany.software.administrativo.ventas.tools.Bill;
import com.mycompany.software.administrativo.ventas.tools.Product;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class BillQuery extends ConnectionDB {

    private Statement statement;
    private ResultSet resultSet;

    private final String tableName = "bills";
    private final String rawQueryAll = "SELECT b.id_bill, b.fecha, b.hora, "
            + "c.names AS client_name, c.last_names AS client_last_name, "
            + "s.names AS seller_name, s.last_names AS seller_last_name, "
            + "x.box_number, bd.payment_method, "
            + "p.product_name, p.unit_value, p.quantity "
            + "FROM bills b "
            + "JOIN clients c ON b.id_bill_client = c.id_client "
            + "JOIN sellers s ON b.id_bill_seller = s.id_seller "
            + "JOIN boxes x ON b.id_bill_box = x.id_box "
            + "JOIN bill_details bd ON b.id_bill = bd.id_bill "
            + "JOIN products p ON bd.id_bill_detail = p.id_bill_details_product "
            + "ORDER BY b.id_bill, bd.id_bill_detail;";
    private final String rawQueryRemove = "DELETE FROM " + tableName + " WHERE id_bills=";
    private final String rawQueryAdd = "INSERT INTO " + tableName;

    /**
     * Inserts a new bill record along with associated details and products.
     *
     * @param documentClient The client's document ID.
     * @param documentSeller The seller's document ID.
     * @param idBillBox The ID of the bill box.
     * @param fecha The date of the bill.
     * @param hora The time of the bill.
     * @param paymentMethod The payment method used.
     * @param products A list of products associated with the bill.
     * @return True if the insertion was successful, false otherwise.
     */
    public boolean insertNew(int documentClient, int documentSeller, int idBillBox, String fecha, String hora, String paymentMethod, List<Product> products) {
        try {
            // Insert into the 'bills' table
            String insertBillQuery = "INSERT INTO `bills` (`document_client`, `document_seller`, `id_bill_box`, `fecha`, `hora`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement billStmt = this.con.prepareStatement(insertBillQuery, Statement.RETURN_GENERATED_KEYS);
            billStmt.setInt(1, documentClient);
            billStmt.setInt(2, documentSeller);
            billStmt.setInt(3, idBillBox);
            billStmt.setString(4, fecha);
            billStmt.setString(5, hora);
            billStmt.executeUpdate();

            // Get the last inserted ID in 'bills'
            ResultSet generatedKeys = billStmt.getGeneratedKeys();
            int lastIdInBills = -1;
            if (generatedKeys.next()) {
                lastIdInBills = generatedKeys.getInt(1);

                // Insert into the 'bill_details' table using the last ID inserted in 'bills'
                String insertBillDetailsQuery = "INSERT INTO `bill_details` (`id_bill`, `payment_method`) VALUES (?, ?)";
                PreparedStatement billDetailsStmt = this.con.prepareStatement(insertBillDetailsQuery, Statement.RETURN_GENERATED_KEYS);
                billDetailsStmt.setInt(1, lastIdInBills);
                billDetailsStmt.setString(2, paymentMethod);
                billDetailsStmt.executeUpdate();

                // Get the last inserted ID in 'bill_details'
                generatedKeys = billDetailsStmt.getGeneratedKeys();
                int lastIdInBillDetails = -1;
                if (generatedKeys.next()) {
                    lastIdInBillDetails = generatedKeys.getInt(1);

                    // Insert into the 'products' table using the last ID inserted in 'bill_details'
                    String insertProductQuery = "INSERT INTO `products` (`id_bill_details_product`, `product_name`, `unit_value`, `quantity`) VALUES (?, ?, ?, ?)";
                    PreparedStatement productStmt = this.con.prepareStatement(insertProductQuery);
                    for (Product product : products) {
                        productStmt.setInt(1, lastIdInBillDetails);
                        productStmt.setString(2, product.getProductName());
                        productStmt.setDouble(3, product.getUnitValue());
                        productStmt.setInt(4, product.getQuantity());
                        productStmt.executeUpdate();
                    }
                }
            }

            // Si llegamos hasta aquí, todas las inserciones se realizaron correctamente
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            this.close(); // Cerrar la conexión
        }

        // Si hubo algún problema, retornamos falso
        return false;
    }

    public void all() {
        try {
            resultSet = statement.executeQuery(rawQueryAll);
            int currentBillId = -1; // Variable para controlar el cambio de factura
            boolean firstLine = true;

            while (resultSet.next()) {
                int idBill = resultSet.getInt("id_bill");

                if (idBill != currentBillId) {
                    if (!firstLine) {
                        // Si no es la primera línea, imprimir la separación entre facturas
                        System.out.println("---------------------------------------------------");
                    } else {
                        firstLine = false;
                    }
                    // Imprimir los datos de la nueva factura
                    System.out.println("Factura ID: " + idBill
                            + "\nCliente: " + resultSet.getString("client_name") + " " + resultSet.getString("client_last_name")
                            + "\nVendedor: " + resultSet.getString("seller_name") + " " + resultSet.getString("seller_last_name")
                            + "\nNúmero de Caja: " + resultSet.getInt("box_number")
                            + "\nFecha: " + resultSet.getDate("fecha")
                            + "\nHora: " + resultSet.getTime("hora"));
                    System.out.println("Detalles de productos:");
                    currentBillId = idBill;
                }

                // Imprimir los detalles de los productos para la factura actual
                System.out.println("   Producto: " + resultSet.getString("product_name")
                        + ", Valor unitario: " + resultSet.getInt("unit_value")
                        + ", Cantidad: " + resultSet.getInt("quantity")
                        + ", Método de pago: " + resultSet.getString("payment_method"));
            }

            // Si hubo al menos una factura, cerrar con una línea
            if (!firstLine) {
                System.out.println("---------------------------------------------------");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(Bill bill, int idPaymentMethod, ArrayList<Product> listProducts) {
        try {
            // Crear la consulta SQL para insertar la factura
            String sqlBill = "INSERT INTO bills (id_bill_client, id_bill_seller, fecha, hora) VALUES (?, ?, ?, ?)";
            // Crear un PreparedStatement para ejecutar la consulta SQL
            PreparedStatement pstmtBill = this.con.prepareStatement(sqlBill, Statement.RETURN_GENERATED_KEYS);
            // Establecer los valores de los parámetros
            pstmtBill.setInt(1, bill.getClientId());
            pstmtBill.setInt(2, bill.getSellerId());
            pstmtBill.setInt(3, bill.getBoxId());
            pstmtBill.setString(4, bill.getDate());
            pstmtBill.setString(5, bill.getTime());
            // Ejecutar la consulta SQL
            pstmtBill.executeUpdate();

            // Insertar los detalles de la factura
            ResultSet rs = pstmtBill.getGeneratedKeys(); // Obtener el ID de la factura recién insertada
            rs.next();
            int billId = rs.getInt(1);
            // Crear la consulta SQL para insertar detalles de la factura
            String sqlDetail = "INSERT INTO bill_details (id_bill, payment_method) VALUES (?, ?)";
            PreparedStatement pstmtDetail = this.con.prepareStatement(sqlDetail, Statement.RETURN_GENERATED_KEYS);
            // Establecer los valores de los parámetros
            pstmtDetail.setInt(1, billId);
            pstmtDetail.setInt(2, idPaymentMethod);
            // Ejecutar la consulta SQL
            pstmtDetail.executeUpdate();

            // Insert products
            //for (Product product : bill.getProducts()) {
            for (Product product : listProducts) {
                // Obtener el ID del detalle de la factura recién insertado
                ResultSet rsDetail = pstmtDetail.getGeneratedKeys();
                rsDetail.next();
                int billDetailId = rsDetail.getInt(1);

                // Crear la consulta SQL para insertar el producto
                String sqlProduct = "INSERT INTO products (product_name, unit_value, quantity, id_bill_details_product, ) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmtProduct = this.con.prepareStatement(sqlProduct);
                // Establecer los valores de los parámetros
                pstmtProduct.setString(1, product.getProductName());
                pstmtProduct.setFloat(2, product.getUnitValue());
                pstmtProduct.setInt(3, product.getQuantity());
                pstmtProduct.setInt(4, billDetailId);
                // Ejecutar la consulta SQL
                pstmtProduct.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(int billId) {
        try {
            statement.executeUpdate(rawQueryRemove + String.valueOf(billId));
//            System.out.println("ID:" + resultSet.getString("id") + " id usuario: " + resultSet.getString("id usuario"));

        } catch (SQLException ex) {
            Logger.getLogger(Connection.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
