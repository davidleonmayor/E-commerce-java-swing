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
    private String url = "jdbc:mysql://localhost:3306/" + nameDatabase;

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
        }
    }
    
    public void insertUsuario() {
        try {
            stmt.executeUpdate("INSERT INTO 	clients (document, names, last_names) VALUES (0123456789, 'santiago', 'torres morocho')");
            stmt.executeUpdate("INSERT INTO 	clients (document, names, last_names) VALUES (0987654321, 'jesus david', 'leon')");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
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
        }
    }
}