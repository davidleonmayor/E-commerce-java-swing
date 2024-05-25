package controller;
// com.mycompany.software.administrativo.ventas.controller;

import com.mycompany.software.administrativo.ventas.model.loginQuery;
import com.mycompany.software.administrativo.ventas.views.Dashboard;
import com.mycompany.software.administrativo.ventas.views.Login;
import javax.swing.JOptionPane;

/**
 * The LoginCon class is responsible for handling the login process It acts as
 * an intermediary between the Login view and the Manager and Seller models
 */
public class LoginCon {

    private final Login login; // The Login view that this controller is associated with
    loginQuery loginQuery = new loginQuery(); // The loginQuery object is used to interact with the database

    /**
     * The constructor for the LoginCon class.
     *
     * @param login The Login view that this controller will manage
     */
    public LoginCon(Login login) {
        this.login = login;
    }

    /**
     * This method handles the login process for a manager. If the manager's
     * credentials are valid, it opens the Dashboard and closes the Login view
     *
     * @param document The manager's document number
     * @param hashedPassword The manager's hashed password
     */
    public void accesToDashboardLikeManager(int document, String hashedPassword) {
        if (loginQuery.checkIfManagerExist(document, hashedPassword)) {
            System.out.println("llego asta qui");
            new Dashboard(1).setVisible(true); // para metro (1) es para gerentes
            login.close();
        } else {
            JOptionPane.showMessageDialog(login, "Credenciales incorrectas o no existentes");
        }
    }

    /**
     * This method handles the login process for a seller.
     * If the seller's credentials are valid, it opens the Dashboard and closes the Login view
     * @param document The seller's document number
     * @param hashedPassword The seller's hashed password
     */
    public void accesToDashboardLikeSeller(int document, String hashedPassword) {
        if (loginQuery.checkIfSellerExist(document, hashedPassword)) {
            new Dashboard(2).setVisible(true); // para metro (2) es para gerentes
            login.close();
        } else {
            JOptionPane.showMessageDialog(login, "Credenciales incorrectas o no existentes");
        }
    }

    /**
     * This method closes the current frame and opens the Login view.
     * It is used to log out of the application.
     * @param frame The current frame that will be closed.
     */ 
    public static void closeFrameAndOpenLogin(javax.swing.JFrame frame) {
        frame.dispose();
        new Login().setVisible(true);
    }
}
