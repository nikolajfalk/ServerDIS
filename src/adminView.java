import config.Config;
import database.DBConnector;
import model.User;


import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class adminView  {

    private DBConnector db;



    public adminView() {
        this.db = new DBConnector();
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
                System.out.println("*          Hovedmenu             *");
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
                        db.getUsers();
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
                    //    db.deleteUser();
                        break;
                    case 4:
                       // db.addCurriculumBook();
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