package com.mycompany.software.administrativo.ventas;

import com.mycompany.software.administrativo.ventas.database.BillQuery;
import java.sql.SQLException;

public class SoftwareAdministrativoVentas {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        new Dashboard().setVisible(true);
        //
        BillQuery billQuery = new BillQuery();
//        billQuery.add(new Bill(123, 456, 789, 10));
        billQuery.all();
    }
}
