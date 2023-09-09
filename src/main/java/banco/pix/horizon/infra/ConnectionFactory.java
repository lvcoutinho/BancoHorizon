package banco.pix.horizon.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory(){}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/HorizonDB", "postgres", "f1ffd1b0");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
