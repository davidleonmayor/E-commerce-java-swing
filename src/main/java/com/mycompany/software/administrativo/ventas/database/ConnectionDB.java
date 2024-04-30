package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private String nameDatabase = "software_administrativo_ventas";
    private String usuario = "root";
    private String clave = "";
    private String url = "jdbc:mysql://localhost:3306/" + nameDatabase + "?allowMultiQueries=true";

    protected Connection con;
    private Statement stmt;
    private ResultSet rs;

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void insertUsuario() {
        try {
            stmt.executeUpdate("INSERT INTO 	clients (document, names, last_names) VALUES (0123456789, 'santiago', 'torres morocho')");
            stmt.executeUpdate("INSERT INTO 	clients (document, names, last_names) VALUES (0987654321, 'jesus david', 'leon')");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void selectUsuarios() {
        try {
            rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println("ID:" + rs.getString("id") + " Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void selectAllBills() {
        try {
            rs = stmt.executeQuery("SELECT * FROM bills");
            while (rs.next()) {
                int id = rs.getInt("id_bill");
                int clientId = rs.getInt("id_bill_client");
                int sellerId = rs.getInt("id_bill_seller");
                int boxId = rs.getInt("id_bill_box");
                Date fecha = rs.getDate("fecha");
                Time hora = rs.getTime("hora");

                System.out.println("ID: " + id);
                System.out.println("Client ID: " + clientId);
                System.out.println("Seller ID: " + sellerId);
                System.out.println("Box ID: " + boxId);
                System.out.println("Fecha: " + fecha);
                System.out.println("Hora: " + hora);
                System.out.println("--------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//    public void insertBillAndDetails() {
//        System.out.println("----------------medodo de validacion--------------");
//        try {
//            // Inserta en la tabla 'bills'
//            stmt.executeUpdate("INSERT INTO `bills` (`id_bill`, `id_bill_client`, `id_bill_seller`, `id_bill_box`, `fecha`, `hora`) VALUES (NULL, '1', '2', '1', '2024-04-03', '12:24:37')", Statement.RETURN_GENERATED_KEYS);
//
//            // Obtiene el último ID insertado en 'bills'
//            ResultSet generatedKeys = stmt.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int lastIdInBills = generatedKeys.getInt(1);
//
//                // Inserta en la tabla 'bill_details' usando el último ID insertado en 'bills'
//                stmt.executeUpdate("INSERT INTO `bill_details` (`id_bill_detail`, `id_bill`, `payment_method`) VALUES (NULL, " + lastIdInBills + ", NULL)", Statement.RETURN_GENERATED_KEYS);
//
//                // Obtiene el último ID insertado en 'bill_details'
//                generatedKeys = stmt.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    int lastIdInBillDetails = generatedKeys.getInt(1);
//
//                    // Inserta en la tabla 'products' usando el último ID insertado en 'bill_details'
//                    stmt.executeUpdate("INSERT INTO `products` (`id_product`, `product_name`, `unit_value`, `quantity`, `id_bill_details_product`) VALUES (NULL, 'prueba introducio a la base de datos', '0', '0', " + lastIdInBillDetails + ")");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
    
    public void insertBill(int idBillClient, int idBillSeller, int idBillBox, String fecha, String hora, String paymentMethod, String productName, double unitValue, int quantity) {
    System.out.println("----------------medodo de validacion--------------");
    try {
        // Inserta en la tabla 'bills'
        stmt.executeUpdate("INSERT INTO `bills` (`id_bill`, `id_bill_client`, `id_bill_seller`, `id_bill_box`, `fecha`, `hora`) VALUES (NULL, '" + idBillClient + "', '" + idBillSeller + "', '" + idBillBox + "', '" + fecha + "', '" + hora + "')", Statement.RETURN_GENERATED_KEYS);

        // Obtiene el último ID insertado en 'bills'
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            int lastIdInBills = generatedKeys.getInt(1);

            // Inserta en la tabla 'bill_details' usando el último ID insertado en 'bills'
            stmt.executeUpdate("INSERT INTO `bill_details` (`id_bill_detail`, `id_bill`, `payment_method`) VALUES (NULL, " + lastIdInBills + ", '" + paymentMethod + "')", Statement.RETURN_GENERATED_KEYS);

            // Obtiene el último ID insertado en 'bill_details'
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int lastIdInBillDetails = generatedKeys.getInt(1);

                // Inserta en la tabla 'products' usando el último ID insertado en 'bill_details'
                stmt.executeUpdate("INSERT INTO `products` (`id_product`, `product_name`, `unit_value`, `quantity`, `id_bill_details_product`) VALUES (NULL, '" + productName + "', '" + unitValue + "', '" + quantity + "', " + lastIdInBillDetails + ")");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
}

    public void deleteBill(int idBill) {
        try {
        
        stmt.executeUpdate("DELETE FROM `products` WHERE `id_bill_details_product` = " + idBill);
        //
        stmt.executeUpdate("DELETE FROM `bill_details` WHERE `id_bill` = " + idBill);
        //
        stmt.executeUpdate("DELETE FROM `bills` WHERE `id_bill` = " + idBill);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public void deleteBillAndDetails(int idBill) {
    try {
        // Obtiene el id_bill_detail asociado con el idBill
        rs = stmt.executeQuery("SELECT id_bill_detail FROM bill_details WHERE id_bill = " + idBill);
        if (rs.next()) {
            int idBillDetail = rs.getInt("id_bill_detail");

            // Primero, elimina los productos relacionados con los detalles de la factura
            stmt.executeUpdate("DELETE FROM `products` WHERE `id_bill_details_product` = " + idBillDetail);
        }

        // Luego, elimina los detalles de la factura
        stmt.executeUpdate("DELETE FROM `bill_details` WHERE `id_bill` = " + idBill);

        // Finalmente, elimina la factura
        stmt.executeUpdate("DELETE FROM `bills` WHERE `id_bill` = " + idBill);
    } catch (SQLException ex) {
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
}

    
    public void verifyInsertion() {
        try {
            // Verifica la inserción en la tabla 'bills'
            rs = stmt.executeQuery("SELECT * FROM bills ORDER BY id_bill DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("Última factura insertada:");
                System.out.println("ID: " + rs.getInt("id_bill"));
                System.out.println("Client ID: " + rs.getInt("id_bill_client"));
                System.out.println("Seller ID: " + rs.getInt("id_bill_seller"));
                System.out.println("Box ID: " + rs.getInt("id_bill_box"));
                System.out.println("Fecha: " + rs.getDate("fecha"));
                System.out.println("Hora: " + rs.getTime("hora"));
                System.out.println("--------------------");
            }

            // Verifica la inserción en la tabla 'bill_details'
            rs = stmt.executeQuery("SELECT * FROM bill_details ORDER BY id_bill_detail DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("Último detalle de factura insertado:");
                System.out.println("ID: " + rs.getInt("id_bill_detail"));
                System.out.println("Bill ID: " + rs.getInt("id_bill"));
                System.out.println("Payment Method: " + rs.getString("payment_method"));
                System.out.println("--------------------");
            }

            // Verifica la inserción en la tabla 'products'
            rs = stmt.executeQuery("SELECT * FROM products ORDER BY id_product DESC LIMIT 1");
            if (rs.next()) {
                System.out.println("Último producto insertado:");
                System.out.println("ID: " + rs.getInt("id_product"));
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Unit Value: " + rs.getDouble("unit_value"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Bill Details Product ID: " + rs.getInt("id_bill_details_product"));
                System.out.println("--------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ConnectionDB connectionDB = new ConnectionDB();
//        connectionDB.deleteBill(1);
////        // connectionDB.insertBill();
////        connectionDB.verifyInsertion();
//    }

}
