import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:school.db";

        try (Connection conn = DriverManager.getConnection(url); 
             Statement stmt = conn.createStatement()) {

            if (conn != null) {
                System.out.println("Verbindung zur Datenbank hergestellt.");
            }

            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(50), " +
                    "age INT, " +
                    "note VARCHAR(10)" +
                    ");";
            stmt.execute(createTableSQL);
            System.out.println("Tabelle students wurde erstellt.");

            String insertDataSQL = "INSERT INTO students (name, age, note) VALUES " +
                    "('Max Mustermann', 18, '1'), " +
                    "('Maria Musterfrau', 19, '2'), " +
                    "('John Doe', 17, '2'), " +
                    "('Jane Doe', 20, '5');";
            stmt.execute(insertDataSQL);
            System.out.println("Testdaten wurden eingefügt.");

        } catch (SQLException e) {
            System.out.println("Fehler beim Arbeiten mit der Datenbank:");
            e.printStackTrace();
        }
    }
}