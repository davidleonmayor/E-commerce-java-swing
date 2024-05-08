package com.mycompany.software.administrativo.ventas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginQuery extends ConnectionDB {

    public loginQuery() {
    }

    public boolean checkIfManagerExist(int document, int password) {
        try {
            // Crear la consulta SQL
            String query = "SELECT * FROM `manager` WHERE `document_manager` = " + document + " AND `password_manager` = " + password;

            // Ejecutar la consulta
            this.rs = stmt.executeQuery(query);

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

    public boolean checkIfSellerExist(int document, int password) {
        try {
            // Crear la consulta SQL
            String query = "SELECT * FROM `sellers` WHERE `document` = " + document + " AND `password_sellers` = " + password;

            // Ejecutar la consulta
            this.rs = stmt.executeQuery(query);

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

    private String checkManagerQuery = "SELECT * FROM `manager` WHERE `document_manager` = ";
}
