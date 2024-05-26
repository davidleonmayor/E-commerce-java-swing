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

/**
 * Responsible for managing the permissions to database
 */
public class ConnectionDB {

    private String nameDatabase = "software_administrativo_ventas";
    private String usuario = "root";
    private String clave = "";
    private String url = "jdbc:mysql://localhost:3306/" + nameDatabase + "?allowMultiQueries=true";

    protected Connection con;
    protected Statement stmt;
    protected ResultSet rs;

    /**
     * Constructor initializes the database driver and establishes a connection
     * with the database.
     */
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

    /**
     * Closes the result set and the database connection.
     */
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

    /**
     * Inserts a new bill into the database.
     *
     * @param documentClient the document number of the client for the bill
     * @param documentSeller the document number of the seller for the bill
     * @param idBillBox the ID of the box for the bill
     * @param fecha the date of the bill
     * @param hora the time of the bill
     * @param paymentMethod the payment method for the bill
     * @param products the list of products included in the bill
     */
    public void insertBill(int documentClient, int documentSeller, int idBillBox, String fecha, String hora, String paymentMethod, List<Product> products) {
        try {
            // Insert into the 'bills' table
            stmt.executeUpdate("INSERT INTO `bills` (`id_bill`, `document_client`, `document_seller`, `id_bill_box`, `fecha`, `hora`) VALUES (NULL, '" + documentClient + "', '" + documentSeller + "', '" + idBillBox + "', '" + fecha + "', '" + hora + "')", Statement.RETURN_GENERATED_KEYS);

            // Get the last inserted ID in 'bills'
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int lastIdInBills = -1;
            if (generatedKeys.next()) {
                lastIdInBills = generatedKeys.getInt(1);

                // Insert into the 'bill_details' table using the last ID inserted in 'bills'
                stmt.executeUpdate("INSERT INTO `bill_details` (`id_bill_detail`, `id_bill`, `payment_method`) VALUES (NULL, " + lastIdInBills + ", '" + paymentMethod + "')", Statement.RETURN_GENERATED_KEYS);

                // Get the last inserted ID in 'bill_details'
                generatedKeys = stmt.getGeneratedKeys();
                int lastIdInBillDetails = -1;
                if (generatedKeys.next()) {
                    lastIdInBillDetails = generatedKeys.getInt(1);

                    // Insert into the 'products' table using the last ID inserted in 'bill_details'
                    for (Product product : products) {
                        stmt.executeUpdate("INSERT INTO `products` (`id_product`, `id_bill_details_product`, `product_name`, `unit_value`, `quantity`) VALUES (NULL, " + lastIdInBillDetails + ", '" + product.getProductName() + "', '" + product.getUnitValue() + "', '" + product.getQuantity() + "')");
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
                    + "JOIN clients c ON b.document_client = c.document "  // change
                    + "JOIN sellers s ON b.document_seller = s.document "
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
