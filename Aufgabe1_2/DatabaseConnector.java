import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:sqlite:school.db";
    private Connection connection = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Verbindung zur Datenbank erfolgreich hergestellt.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Herstellen der Verbindung: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Datenbankverbindung geschlossen.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
        }
    }
}
