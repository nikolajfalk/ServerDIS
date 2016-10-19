package database;

import model.Book;
import model.Curriculum;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class DBConnector {
    /**
     * Constructor for establishing connection.
     *
     * @throws Exception
     */
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/bookit?useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "YNXR3ecz";

    String sql = "";
    Connection conn = null;

    public DBConnector() {

        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query

            //STEP 6: Clean-up environment
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*5 user methods*/
    public static ArrayList<User> getUsers() {
        return new ArrayList<User>();
    }

    public static User getUser(int id) {
        return new User();
    }

    public static boolean editUser(int id) {
        return true;
    }

    public static boolean addUser(User u) {
        return true;
    }

    public static boolean deleteUser(int id) {
        return true;
    }
    
    /*Curriculum methods*/
    public static ArrayList<Curriculum> getCurriculums() {
        return new ArrayList<Curriculum>();
    }

    public static Curriculum getCurriculum(int id) {
        return new Curriculum();
    }

    public static boolean editCurriculum(int id) {
        return true;
    }

    public static boolean addCurriculum(Curriculum c) {
        return true;
    }

    public static boolean deleteCurriculum(int id) {
        return true;
    }
    
    /*books methods*/




    public ArrayList getBooks() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getUsers = conn.prepareStatement("SELECT * FROM Books ");
            resultSet = getUsers.executeQuery();

            while ( resultSet.next() ) {
                results.add(new Book(
                        resultSet.getString("Title"),
                        resultSet.getString("Publisher"),
                        resultSet.getString("ISBN")
                ));
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;

    }




    public static Book getBook(int id) {
        return new Book();
    }

    public static boolean editBook(int id) {
        return true;
    }

    public static boolean addBook(Book b) {
        return true;
    }

    public static boolean deleteBook(int id) {
        return true;
    }

    public boolean authenticate(String username, String password){

        ResultSet resultSet = null;

        try {
            PreparedStatement authenticate = conn.prepareStatement("select * from Users where username = ? AND Password = ?");


            resultSet = authenticate.executeQuery();

            if (resultSet.next()){
                return true;
            }else{
                return false;
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> getUserIdFromToken(String token){
        ArrayList<Integer> results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getUserIdFromToken = conn.prepareStatement("select Tokens.user_id, Users.Usertype from Tokens inner join Users on Tokens.user_id = Users.UserID where Tokens.token = ?");
            resultSet = getUserIdFromToken.executeQuery();
                while ( resultSet.next() ) {
                    results.add(
                            resultSet.getInt("user_id"),
                            resultSet.getInt("Usertype")
                    );

                }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;


    }


}
