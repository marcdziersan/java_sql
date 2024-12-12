import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager {
    private Connection connection;

    public StudentManager(DatabaseConnector dbConnector) {
        this.connection = dbConnector.getConnection();
    }

    // 1. Methode: Alle Daten aus der Tabelle students abrufen und ausgeben
    public void getAllStudents() {
        String query = "SELECT * FROM students";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("ID | Name | Alter | Note");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("note"));
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen aller Studenten: " + e.getMessage());
        }
    }

    // 2. Methode: Daten nach einer bestimmten Note abrufen
    public void getStudentsByGrade(String grade) {
        String query = "SELECT * FROM students WHERE note = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grade);
            ResultSet rs = stmt.executeQuery();

            System.out.println("ID | Name | Alter | Note");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("note"));
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen der Studenten mit Note " + grade + ": " + e.getMessage());
        }
    }

    // 3. Methode: Neuen Studenten einfügen
    public void insertStudent(String name, int age, String note) {
        String query = "INSERT INTO students (name, age, note) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, note);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " Student(en) erfolgreich eingefügt.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen eines neuen Studenten: " + e.getMessage());
        }
    }

    // 4. Methode: Note eines Studenten aktualisieren
    public void updateStudentGrade(int id, String newGrade) {
        String query = "UPDATE students SET note = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newGrade);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " Student(en) erfolgreich aktualisiert.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Aktualisieren der Note: " + e.getMessage());
        }
    }

    // 5. Methode: Studenten anhand der ID löschen
    public void deleteStudentById(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " Student(en) erfolgreich gelöscht.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des Studenten: " + e.getMessage());
        }
    }

    // 6. SQL-Abfrage: Durchschnittsalter aller Studenten berechnen
    public void getAverageAge() {
        String query = "SELECT AVG(age) AS average_age FROM students";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Durchschnittsalter der Studenten: " + rs.getDouble("average_age"));
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Berechnen des Durchschnittsalters: " + e.getMessage());
        }
    }

    // 7. SQL-Abfrage: Anzahl der Studenten nach Note gruppieren
    public void getCountByGrade() {
        String query = "SELECT note, COUNT(*) AS count FROM students GROUP BY note";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Note | Anzahl");
            while (rs.next()) {
                System.out.printf("%s | %d%n", rs.getString("note"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen der Anzahl nach Note: " + e.getMessage());
        }
    }

    // 8. SQL-Abfrage: Studenten nach Alter sortieren
    public void getStudentsSortedByAge() {
        String query = "SELECT * FROM students ORDER BY age";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("ID | Name | Alter | Note");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("note"));
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen der Studenten nach Alter: " + e.getMessage());
        }
    }
}
