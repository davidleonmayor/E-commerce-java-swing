import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private String usuario = "root";
    private String clave = "";
    private String url = "jdbc:mysql://localhost:3306/software_administrativo_ventas";

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // public void insertUsuario(String name) {
    public void insertUsuario() {
        try {
            stmt.executeUpdate("INSERT INTO usuarios (nombre) VALUES ('san')");
            stmt.executeUpdate("INSERT INTO usuarios (nombre) VALUES ('david')");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectUsuarios() {
        try {
            rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println("ID:" + rs.getString("id") + " Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public static void main(String[] args) {
//        ConnectionDB db = new ConnectionDB();
//        db.insertUsuario();
//        db.selectUsuarios();
//    }
}