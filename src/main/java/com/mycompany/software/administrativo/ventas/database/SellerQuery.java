package com.mycompany.software.administrativo.ventas.database;

import com.mycompany.software.administrativo.ventas.tools.SellerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellerQuery extends ConnectionDB {
    
    public void remove(int document) {
        try {
            stmt.executeUpdate(removeOneUserStadmend + document);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SellerModel> getByDocument(int doc) {
        List<SellerModel> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM sellers WHERE document = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, doc);
            ResultSet rs = pstmt.executeQuery();
            
            // tomar datos de la peticion
            while (rs.next()) {
                int id = rs.getInt("id_seller");
                int document = rs.getInt("document");
                String names = rs.getString("names");
                String last_names = rs.getString("last_names");
                
                // se almacnea cada cliente en la listaa a retormar
                SellerModel client = new SellerModel(id, document, names, last_names);
                clients.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }

    
    private String removeOneUserStadmend = "DELETE FROM `clients` WHERE `document` = ";
    
//    public static void main(String[] args) {
//        ClientQuery clientQuery = new ClientQuery();
//        clientQuery.remove(666666);
//    }
}
