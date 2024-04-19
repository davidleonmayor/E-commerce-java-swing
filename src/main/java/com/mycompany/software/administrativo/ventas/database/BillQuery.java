package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mycompany.software.administrativo.ventas.tools.Bill;
import java.sql.Date;
import java.sql.Time;

public class BillQuery extends ConnectionDB {
    private final Statement statement;
    private ResultSet resultSet;
    
    private final String tableName = "bills";
    private final String rawQueryAll = "SELECT b.id_bill, b.fecha, b.hora, " +
    "c.names AS client_name, c.last_names AS client_last_name, " +
    "s.names AS seller_name, s.last_names AS seller_last_name, " +
    "x.box_number, bd.payment_method, " +
    "p.product_name, p.unit_value, p.quantity " +
    "FROM bills b " +
    "JOIN clients c ON b.id_bill_client = c.id_client " +
    "JOIN sellers s ON b.id_bill_seller = s.id_seller " +
    "JOIN boxes x ON b.id_bill_box = x.id_box " +
    "JOIN bill_details bd ON b.id_bill = bd.id_bill " +
    "JOIN products p ON bd.id_bill_detail = p.id_bill_details_product " +
    "ORDER BY b.id_bill, bd.id_bill_detail;";
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
    
    public void getOne(int idBill) {
        
    }
    
    public void remove(int billId) {
        try {
            statement.executeUpdate(rawQueryRemove + String.valueOf(billId));
//            System.out.println("ID:" + resultSet.getString("id") + " id usuario: " + resultSet.getString("id usuario"));
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void add(Bill bill) {
        String complit = rawQueryAdd + "(id_bill, id_client, id_saller, id_box)"
                + " VALUES" + "(" + bill.clientId() + ", " + bill.sallerId() + ", " + bill.boxId() + ")";
        System.out.println("complit");
//            statement.executeUpdate(complit);
    }
}
