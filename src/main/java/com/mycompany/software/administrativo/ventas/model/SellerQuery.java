package com.mycompany.software.administrativo.ventas.model;

import com.mycompany.software.administrativo.ventas.tools.SellerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SellerQuery extends ConnectionDB {

    private String removeOneSellerStadmend = "DELETE FROM `sellers` WHERE `document` = ";
    
    // ----
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void create(int document, String password, String names, String last_names) {
        String hashedPassword = hashPassword(password);
        System.out.println("hashed password: " + hashedPassword);

        try {
            String query = "INSERT INTO `sellers` (`document`, `password_sellers`, `names`, `last_names`) VALUES (" + document + ", '" + hashedPassword + "', '" + names + "', '" + last_names + "')";
            this.stmt.executeUpdate(query);
            System.out.println("Created");

        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }
    }

    public void remove(int document) {
        try {
            this.stmt.executeUpdate(removeOneSellerStadmend + document);
        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
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
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }

        return clients;
    }

    
}
