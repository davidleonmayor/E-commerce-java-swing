package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mycompany.software.administrativo.ventas.tools.Bill;
import com.mycompany.software.administrativo.ventas.tools.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;

public class BillQuery extends ConnectionDB {
    private final Statement statement;
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

    public BillQuery() throws SQLException {
        super();
        statement = this.con.createStatement();
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
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void add(int idBillClient, int idBillSeller, String fecha, String hora) {
//        String complit = rawQueryAdd + "(id_bill, id_client, id_saller, id_box)"
//                + " VALUES" + "(" + bill.clientId() + ", " + bill.sallerId() + ", " + bill.boxId() + ")";
//        System.out.println("complit");
////            statement.executeUpdate(complit);
//        try {
//            // Obtener los IDs de los clientes, vendedores y cajas
//            int clientId = getClientId(bill.getClientName(), bill.getClientLastName());
//            int sellerId = getSellerId(bill.getSellerName(), bill.getSellerLastName());
//            int boxNumber = getBoxNumber(bill.getBoxNumber());
//
//            // Crear la consulta SQL para insertar la factura
//            String sql = "INSERT INTO bills (id_bill_client, id_bill_seller, id_bill_box, fecha, hora) VALUES (?, ?, ?, ?, ?)";
//
//            // Crear un PreparedStatement para ejecutar la consulta SQL
//            PreparedStatement pstmt = con.prepareStatement(sql);
//
//            // Establecer los valores de los parámetros
//            pstmt.setInt(1, clientId);
//            pstmt.setInt(2, sellerId);
//            pstmt.setInt(3, boxNumber);
//            pstmt.setDate(4, bill.getDate());
//            pstmt.setTime(5, bill.getTime());
//
//            // Ejecutar la consulta SQL
//            pstmt.executeUpdate();
//
//            // Obtener el ID de la factura recién insertada
//            int billId = getInsertedBillId();
//
//            // Insertar los detalles de la factura
//            for (Product product : bill.getProducts()) {
//                addBillDetail(billId, product);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void remove(int billId) {
        try {
            statement.executeUpdate(rawQueryRemove + String.valueOf(billId));
//            System.out.println("ID:" + resultSet.getString("id") + " id usuario: " + resultSet.getString("id usuario"));
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
