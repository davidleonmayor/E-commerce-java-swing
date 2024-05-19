package com.mycompany.software.administrativo.ventas.model;

/* query to select all bills with his own content
SELECT b.id_bill, b.fecha, b.hora,
    c.names AS client_name, c.last_names AS client_last_name,
    s.names AS seller_name, s.last_names AS seller_last_name,
    -- x.box_number, bd.payment_method,
    p.product_name, p.unit_value, p.quantity
    FROM bills b
    JOIN clients c ON b.id_bill_client = c.id_client
    JOIN sellers s ON b.id_bill_seller = s.id_seller
    -- JOIN boxes x ON b.id_bill_box = x.id_box
    JOIN bill_details bd ON b.id_bill = bd.id_bill
    JOIN products p ON bd.id_bill_detail = p.id_bill_details_product
    ORDER BY b.id_bill, bd.id_bill_detail;
 */
import com.mycompany.software.administrativo.ventas.tools.BillSpecification;
import com.mycompany.software.administrativo.ventas.tools.Product;
import java.util.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private String nameDatabase = "software_administrativo_ventas";
    private String usuario = "root";
    private String clave = "";
    private String url = "jdbc:mysql://localhost:3306/" + nameDatabase + "?allowMultiQueries=true";

    protected Connection con;
    protected Statement stmt;
    protected ResultSet rs;

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

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Integer> filterBills(String filterText) {
        List<Integer> matchingIds = new ArrayList<>();
        try {
            // Prepara la consulta SQL
            String rawQueryBill = "SELECT id_bill FROM bills WHERE id_bill LIKE '%" + filterText + "%'";

            // Ejecuta la consulta
            rs = stmt.executeQuery(rawQueryBill);

            // Agrega las IDs a la lista
            while (rs.next()) {
                int id = rs.getInt("id_bill");
                matchingIds.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return matchingIds;
    }

    public void insertBill(int idBillClient, int idBillSeller, int idBillBox, String fecha, String hora, String paymentMethod, List<Product> products) {
        System.out.println("----------------medodo de validacion--------------");
        try {
            // Inserta en la tabla 'bills'
            stmt.executeUpdate("INSERT INTO `bills` (`id_bill`, `id_bill_client`, `id_bill_seller`, `id_bill_box`, `fecha`, `hora`) VALUES (NULL, '" + idBillClient + "', '" + idBillSeller + "', '" + idBillBox + "', '" + fecha + "', '" + hora + "')", Statement.RETURN_GENERATED_KEYS);

            // Obtiene el último ID insertado en 'bills'
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int lastIdInBills = -1;
            if (generatedKeys.next()) {
                lastIdInBills = generatedKeys.getInt(1);

                // Inserta en la tabla 'bill_details' usando el último ID insertado en 'bills'
                stmt.executeUpdate("INSERT INTO `bill_details` (`id_bill_detail`, `id_bill`, `payment_method`) VALUES (NULL, " + lastIdInBills + ", '" + paymentMethod + "')", Statement.RETURN_GENERATED_KEYS);

                // Obtiene el último ID insertado en 'bill_details'
                generatedKeys = stmt.getGeneratedKeys();
                int lastIdInBillDetails = -1;
                if (generatedKeys.next()) {
                    lastIdInBillDetails = generatedKeys.getInt(1);

                    // Inserta en la tabla 'products' usando el último ID insertado en 'bill_details'
                    for (Product product : products) {
                        stmt.executeUpdate("INSERT INTO `products` (`id_product`, `product_name`, `unit_value`, `quantity`, `id_bill_details_product`) VALUES (NULL, '" + product.getProductName() + "', '" + product.getUnitValue() + "', '" + product.getQuantity() + "', " + lastIdInBillDetails + ")");
                    }
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

    public BillSpecification getBill(int idBill) throws SQLException {
        BillSpecification billSpecification = null;
        List<Product> products = new ArrayList<>();

        try {
            String rawQueryBill = "SELECT b.id_bill, b.fecha, b.hora, "
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
                    + "WHERE b.id_bill = " + idBill + " "
                    + "ORDER BY bd.id_bill_detail;";
            rs = stmt.executeQuery(rawQueryBill);

            while (rs.next()) {
                if (billSpecification == null) {
                    billSpecification = new BillSpecification();
                    billSpecification.setId(rs.getInt("id_bill"));
                    billSpecification.setFecha(rs.getDate("fecha"));
                    billSpecification.setHora(rs.getTime("hora"));
                    billSpecification.setClientName(rs.getString("client_name"));
                    billSpecification.setClientLastName(rs.getString("client_last_name"));
                    billSpecification.setSellerName(rs.getString("seller_name"));
                    billSpecification.setSellerLastName(rs.getString("seller_last_name"));
                    billSpecification.setBoxNumber(rs.getInt("box_number"));
                    billSpecification.setPaymentMethod(rs.getString("payment_method"));
                }

                products.add(new Product(rs.getString("product_name"), rs.getFloat("unit_value"), rs.getInt("quantity")));
            }

            if (billSpecification != null) {
                billSpecification.setProducts(products);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return billSpecification;
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
}
