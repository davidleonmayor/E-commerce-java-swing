// TODO: eliminar el CRUD del usuario. Manejar solo por CC como "el D1" - (DOING)
// 1) al crea una factura nueva. Si el usuario no se encuentra creado
// en la base de datos por su CC, simplemente se crea un nuevo y se le enlaza ese factura 
// 2) Se eliminara poder catualizar una factura por su complejidad, solo tendra CRU
// 3) elimimar en el pane de cliente todo el CRUD.
// Solo dejar que pueda listar y filtra los clientes qu existen, para que tenga centido con relacion al punto (1)
// 4)  del vendedor 
// TODO: agregar clase abstracta requerida, 
package com.mycompany.software.administrativo.ventas;

import com.mycompany.software.administrativo.ventas.views.Login;

public class SoftwareAdministrativoVentas {

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
