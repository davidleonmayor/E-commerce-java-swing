package com.mycompany.software.administrativo.ventas.model;

import com.mycompany.software.administrativo.ventas.tools.ClientModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientQuery extends ConnectionDB {
    
    public void remove(int document) {
        try {
            stmt.executeUpdate(removeOneUserStadmend + document);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ClientModel> getByDocument(int doc) {
        List<ClientModel> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM clients WHERE document = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, doc);
            ResultSet rs = pstmt.executeQuery();
            
            // tomar datos de la peticion
            while (rs.next()) {
                int id = rs.getInt("id_client");
                int document = rs.getInt("document");
                String names = rs.getString("names");
                String last_names = rs.getString("last_names");
                
                // se almacnea cada cliente en la listaa a retormar
                ClientModel client = new ClientModel(id, document, names, last_names);
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
