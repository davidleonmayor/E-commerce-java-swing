package com.mycompany.software.administrativo.ventas.database;

import com.mycompany.software.administrativo.ventas.database.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mycompany.software.administrativo.ventas.tools.Bill;

public class BillQuery extends ConnectionDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    private final String tableName = "bill";
    private final String rawQueryAll = "SELECT * FROM " + tableName;
    private final String rawQueryRemove = "DELETE FROM " + tableName + " WHERE id_bills=";
    private final String rawQueryAdd = "INSERT INTO " + tableName;

    
    public BillQuery() throws SQLException {
        super();
        statement = this.con.createStatement();
    }
    
    public void all() {
        try {
            resultSet = statement.executeQuery(rawQueryAll);
            System.out.println("ID:" + resultSet.getString("id") + " id usuario: " + resultSet.getString("id usuario"));
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
