package com.mycompany.software.administrativo.ventas.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * loginQuery class that extends ConnectionDB.
 * This class contains methods to check, make a manager or seller in the database.
 */
public class loginQuery extends ConnectionDB {
    
    /**
     * Method to check if a manager exists in the database.
     * @param document The manager's document.
     * @param password The manager's password.
     * @return True if the manager exists, false otherwise.
     */
    public boolean checkIfManagerExist(int document, String password) {
        try {
            // Prepared statement
            String query = "SELECT * FROM `manager` WHERE `document_manager` = ? AND `password_manager` = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, document);
            pstmt.setString(2, password);
            // execute statement
            this.rs = pstmt.executeQuery();
            
            // check response
            if (rs.next()) {
                return true; // exist seller with this credentials
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(); // Cerrar la conexión
        }

        return false; // doesn't exist seller with this credentials
    }
    
    /**
     * Method to check if a seller exists in the database.
     * @param document The seller's document.
     * @param password The seller's password.
     * @return True if the seller exists, false otherwise.
     */
    public boolean checkIfSellerExist(int document, String password) {
        try {
            // Prepared statement
            String query = "SELECT * FROM `sellers` WHERE `document` = ? AND `password_sellers` = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, document);
            pstmt.setString(2, password);
            // execute statement
            this.rs = pstmt.executeQuery();
            
            // check response
            if (rs.next()) {
                return true; // exist seller with this credentials
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }

        return false; // doesn't exist seller with this credentials
    }

}
