import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.connect();

        StudentManager manager = new StudentManager(dbConnector);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menü ---");
            System.out.println("1: Alle Studenten abrufen");
            System.out.println("2: Studenten nach Note abrufen");
            System.out.println("3: Neuen Studenten hinzufügen");
            System.out.println("4: Note eines Studenten aktualisieren");
            System.out.println("5: Studenten löschen");
            System.out.println("6: Durchschnittsalter berechnen");
            System.out.println("7: Anzahl der Studenten nach Note anzeigen");
            System.out.println("8: Studenten nach Alter sortieren");
            System.out.println("9: Programm beenden");
            System.out.print("Bitte wählen Sie eine Option: ");
            System.out.println();
            System.out.println();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.getAllStudents();
                    break;
                case 2:
                    System.out.print("Geben Sie die gewünschte Note ein: ");
                    String grade = scanner.nextLine();
                    manager.getStudentsByGrade(grade);
                    break;
                case 3:
                    System.out.print("Name des Studenten: ");
                    String name = scanner.nextLine();
                    System.out.print("Alter des Studenten: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Note des Studenten: ");
                    String note = scanner.nextLine();
                    manager.insertStudent(name, age, note);
                    break;
                case 4:
                    System.out.print("ID des Studenten: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Neue Note: ");
                    String newGrade = scanner.nextLine();
                    manager.updateStudentGrade(idToUpdate, newGrade);
                    break;
                case 5:
                    System.out.print("ID des Studenten, der gelöscht werden soll: ");
                    int idToDelete = scanner.nextInt();
                    manager.deleteStudentById(idToDelete);
                    break;
                case 6:
                    manager.getAverageAge();
                    break;
                case 7:
                    manager.getCountByGrade();
                    break;
                case 8:
                    manager.getStudentsSortedByAge();
                    break;
                case 9:
                    System.out.println("Programm beendet.");
                    dbConnector.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
            }
        }
    }
}