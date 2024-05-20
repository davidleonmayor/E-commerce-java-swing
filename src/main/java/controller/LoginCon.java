package controller;
// com.mycompany.software.administrativo.ventas.controller;

import com.mycompany.software.administrativo.ventas.model.loginQuery;
import com.mycompany.software.administrativo.ventas.views.Dashboard;
import com.mycompany.software.administrativo.ventas.views.Login;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginCon {

    private final Login login;
    loginQuery loginQuery = new loginQuery();

    public LoginCon(Login login) {
        this.login = login;
    }

    // intermediario between Login view and Manager model
    public void accesToDashboardLikeManager(int document, String hashedPassword) {
        if (loginQuery.checkIfManagerExist(document, hashedPassword)) {
            new Dashboard(1).setVisible(true); // para metro (1) es para gerentes
            login.close();
        } else {
            JOptionPane.showMessageDialog(login, "Credenciales incorrectas o no existentes");
        }
    }

    // intermediario between Login view and Seller model
    public void accesToDashboardLikeSeller(int document, String hashedPassword) {
        if (loginQuery.checkIfSellerExist(document, hashedPassword)) {
            new Dashboard(2).setVisible(true); // para metro (2) es para gerentes
            login.close();
        } else {
            JOptionPane.showMessageDialog(login, "Credenciales incorrectas o no existentes");
        }
    }

    // close/sing off frame to open Login frame 
    public static void closeFrameAndOpenLogin(javax.swing.JFrame frame) {
        frame.dispose();
        new Login().setVisible(true);
    }
}
