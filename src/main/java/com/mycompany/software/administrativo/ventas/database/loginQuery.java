package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginQuery extends ConnectionDB {

//    public boolean checkIfManagerExist(int document, String password) {
//        try {
//            // Crear la consulta SQL
//            String query = "SELECT * FROM `manager` WHERE `document_manager` = " + document + " AND `password_manager` = '" + password + "'";
//
//            // Ejecutar la consulta
//            this.rs = stmt.executeQuery(query);
//
//            // Verificar si hay resultados
//            if (rs.next()) {
//                return true; // Si hay al menos una fila, el gerente existe y la contraseña es correcta
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            this.close(); // Cerrar la conexión
//        }
//
//        return false; // Si no se encontró ninguna fila, el gerente no existe o la contraseña es incorrecta
//    }

    public boolean checkIfManagerExist(int document, String password) {
        try {
            // Crear la consulta SQL
            String query = "SELECT * FROM `manager` WHERE `document_manager` = ? AND `password_manager` = ?";

            // Crear una consulta preparada
            PreparedStatement pstmt = this.con.prepareStatement(query);

            // Establecer los parámetros de la consulta
            pstmt.setInt(1, document);
            pstmt.setString(2, password);

            // Ejecutar la consulta
            this.rs = pstmt.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                return true; // Si hay al menos una fila, el gerente existe y la contraseña es correcta
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(); // Cerrar la conexión
        }

        return false; // Si no se encontró ninguna fila, el gerente no existe o la contraseña es incorrecta
    }

//    public boolean checkIfSellerExist(int document, String password) {
//        try {
//            // Crear la consulta SQL
////          String query = "SELECT * FROM `sellers` WHERE `document` = " + document + " AND `password_sellers` = " + password;
//            String query = "SELECT * FROM `sellers` WHERE `document` = " + document + " AND `password_sellers` = '" + password + "'";
//
//            // Ejecutar la consulta
//            this.rs = stmt.executeQuery(query);
//
//            // Verificar si hay resultados
//            if (rs.next()) {
//                return true; // Si hay al menos una fila, el gerente existe y la contraseña es correcta
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            this.close(); // Cerrar la conexión
//        }
//
//        return false; // Si no se encontró ninguna fila, el gerente no existe o la contraseña es incorrecta
//    }
    public boolean checkIfSellerExist(int document, String password) {
        try {
            // Crear la consulta SQL
            String query = "SELECT * FROM `sellers` WHERE `document` = ? AND `password_sellers` = ?";

            // Crear una consulta preparada
            PreparedStatement pstmt = this.con.prepareStatement(query);

            // Establecer los parámetros de la consulta
            pstmt.setInt(1, document);
            pstmt.setString(2, password);

            // Ejecutar la consulta
            this.rs = pstmt.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                return true; // Si hay al menos una fila, el vendedor existe y la contraseña es correcta
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(); // Cerrar la conexión
        }

        return false; // Si no se encontró ninguna fila, el vendedor no existe o la contraseña es incorrecta
    }

}
