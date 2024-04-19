package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mycompany.software.administrativo.ventas.tools.Bill;

public class BillQuery extends ConnectionDB {
    // private Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    
    private final String tableName = "bills";
    private final String rawQueryAll = "SELECT * FROM " + tableName;
    private final String rawQueryRemove = "DELETE FROM " + tableName + " WHERE id_bills=";
    private final String rawQueryAdd = "INSERT INTO " + tableName;

    
    public BillQuery() throws SQLException {
        super();
        statement = this.con.createStatement();
    }
//    INSERT INTO sellers () VALUES ();
//    document, names, last_names
    public void all() {
    String rawQueryAll = "SELECT b.id_bill, b.fecha, b.hora, c.names AS client_name, c.last_names AS client_last_name, s.names AS seller_name, s.last_names AS seller_last_name, x.box_number FROM bills b JOIN clients c ON b.id_bill_client = c.id_client JOIN sellers s ON b.id_bill_seller = s.id_seller JOIN boxes x ON b.id_bill_box = x.id_box;";
    try {
        resultSet = statement.executeQuery(rawQueryAll);
        while (resultSet.next()) {
            System.out.println(
                "id_bill: " + resultSet.getInt("id_bill") +
                " client_name: " + resultSet.getString("client_name") +
                " client_last_name: " + resultSet.getString("client_last_name") +
                " seller_name: " + resultSet.getString("seller_name") +
                " seller_last_name: " + resultSet.getString("seller_last_name") +
                " box_number: " + resultSet.getInt("box_number") +
                " fecha: " + resultSet.getDate("fecha") +
                " hora: " + resultSet.getTime("hora")
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
    }       
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
