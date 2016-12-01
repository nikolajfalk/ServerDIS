
import config.Config;
import database.DBConnector;
import model.User;
import model.Book;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class adminView  {

    private DBConnector db;
    private User user;

    public adminView() {
        this.db = new DBConnector();
        this.user = new User();
    }


    public static void main(String[] args) {
        Config.setDbUrl("localhost");
        Config.setDbPort("3306");
        Config.setDbName("bookit");
        Config.setDbPassword("falk95");
        Config.setDbUserName("root");

    adminView run = new adminView();
        try {
            run.adminMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adminMenu() throws SQLException {

        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("**********************************");
                System.out.println("*          Adminmenu             *");
                System.out.println("* Du har nu f�lgende muligheder: *");
                System.out.println("*                                *");
                System.out.println("**********************************");
                System.out.println("* [ 1 ] Vis brugere              *");
                System.out.println("* [ 2 ] Opret bruger             *");
                System.out.println("* [ 3 ] Slet bruger              *");
                System.out.println("* [ 4 ] Opret bog                *");
                System.out.println("**********************************");

                int adminMenu = input.nextInt();
                switch (adminMenu) {

                    case 1:
                        ArrayList<User> users = db.getUsers();
                        System.out.printf("%15s %-30s %-30s %-25s %-25s %-15s\n", "UserID:", "Brugernavn:", "Fornavn:", "Efternavn:", "Email:", "Brugertype:");
                        for(User user : users) {
                            System.out.printf("%15s %-30s %-30s %-25s %-25s %-15s\n", user.getUserID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserType());
                        }
                        break;
                    case 2:
                        try {
                            System.out.print("Indtast fornavn: ");
                            String fornavn = input.next();
                            System.out.print("Indtast efternavn: ");
                            String efternavn = input.next();
                            System.out.print("Indtast username: ");
                            String username = input.next();
                            System.out.print("Indtast email: ");
                            String email = input.next();
                            System.out.print("Indtast password: ");
                            String password = input.next();

                            User u = new User(fornavn, efternavn, username, email, password, true);

                            db.addUser(u);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println("Indtast UserID på den bruger som du ønsker slettet");
                        int brugerid = input.nextInt();
                       // if()

                    //    db.deleteUser();
                        break;
                    case 4:
                        try {
                            System.out.print("Indtast pensumliste id på den pensumliste bogen skal tilføjes til: ");
                            int curriculumID = input.nextInt();
                            System.out.print("Indtast forlag: ");
                            String forlag = input.next();
                            System.out.print("Indtast titel: ");
                            String titel = input.next();
                            System.out.print("Indtast forfatter: ");
                            String forfatter = input.next();
                            System.out.print("Indtast version: ");
                            int version = input.nextInt();
                            System.out.print("Indtast ISBN: ");
                            double ISBN = input.nextDouble();
                            System.out.print("Indtast pris fra Academic Books: ");
                            double prisAB = input.nextDouble();
                            System.out.print("Indtast pris fra SAXO: ");
                            double prisSAXO = input.nextDouble();
                            System.out.print("Indtast pris fra CDON: ");
                            double prisCDON = input.nextDouble();


                            Book b = new Book(forlag, titel, forfatter, version, ISBN, prisAB, prisSAXO, prisCDON);

                        //    db.addCurriculumBook(curriculumID, b);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        System.out.println("Det er desværre ikke en af mulighederne");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Du kan ikke indtaste bogstaver");
                input.nextLine();
            }
        } while (true);
    }
}