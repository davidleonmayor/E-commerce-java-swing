/* TODO: 
1) agregar clase abstracta requerida 
2) CRUD requerido :check
3) login requrido con roles y almacenemiento de contraseñas incriptadas :check
 */

// TODO: eliminar el CRUD del usuario. Manejar solo por CC como "el D1" - (DOING)
// 1) al crea una factura nueva. Si el usuario no se encuentra creado
// en la base de datos por su CC, simplemente se crea un nuevo y se le enlaza ese factura 
// 2) Se eliminara poder catualizar una factura por su complejidad, solo tendra CRU
// 3) elimimar en el pane de cliente todo el CRUD.
// Solo dejar que pueda listar y filtra los clientes qu existen, para que tenga centido con relacion al punto (1)
// 4)  del vendedor 

/* TODO
1) check all error when you try to create new item in database, like repeated document(it prints an error, but don't manage)
2) check all error when users "interactuar" con la aplicacion like (re-open the same pane ETC)
*/

/* TODO - no needed
1) CRUD manager
*/
package com.mycompany.software.administrativo.ventas;

import com.mycompany.software.administrativo.ventas.views.Login;

public class SoftwareAdministrativoVentas {

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
