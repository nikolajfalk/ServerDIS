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
    static final String DB_URL = "jdbc:mysql://bookit.ch3v4pqrs4c3.eu-central-1.rds.amazonaws.com/Bookit?useSSL=false";

    //  Database credentials
    static final String USER = "bookit";
    static final String PASS = "bookit123";

    String sql;
    Connection conn = null;
    Statement stmt = null;

    public DBConnector() {

        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            this.stmt = conn.createStatement();

            System.out.println("Connected");

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
    public ArrayList<User> getUsers() {
        ArrayList<User> results = null;
        ResultSet resultSet = null;
        sql = "SELECT * FROM Users";

        try {
            resultSet = stmt.executeQuery(sql);
            results = new ArrayList<>();

            while ( resultSet.next() ) {
                results.add(new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getBoolean("Usertype")
                ));
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;
        
    }

    public static User getUser(int id) {
        return new User();
    }

    public static boolean editUser(int id) {
        return true;
    }

    public void addUser(User u) throws Exception {

        PreparedStatement addUserStatement =
            conn.prepareStatement("INSERT INTO Users (First_Name, Last_Name, Username, Email, Password, Usertype) VALUES (?, ?, ?, ?, ?, ?)");

        try {
            addUserStatement.setString(1, u.getFirstName());
            addUserStatement.setString(2, u.getLastName());
            addUserStatement.setString(3, u.getUsername());
            addUserStatement.setString(4, u.getEmail());
            addUserStatement.setString(5, u.getPassword());
            addUserStatement.setBoolean(6, u.getUserType());

            addUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) throws SQLException {

        PreparedStatement deleteUserStatement = conn.prepareStatement("DELETE FROM Users WHERE UserID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*Curriculum methods*/
    public ArrayList getCurriculums() throws IllegalArgumentException { ArrayList results = new ArrayList(); ResultSet resultSet = null;

        try {
            PreparedStatement getCurriculums = conn.prepareStatement("SELECT * FROM Curriculum ");
            resultSet = getCurriculums.executeQuery();

            while ( resultSet.next() ) {
                try {

                    Curriculum cul = new Curriculum(
                            resultSet.getInt("CurriculumID"),
                            resultSet.getString("School"),
                            resultSet.getString("Education"),
                            resultSet.getInt("Semester")
                    );

                    results.add(cul);

                    String test = resultSet.getString("School");
                }catch(Exception e){
                    Integer test = resultSet.getInt("CurriculumID");

                    System.out.println(test + "WORKS");
                }
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;

    }
    public static Curriculum getCurriculum(int id) {
        return new Curriculum();
    }

    public static boolean editCurriculum(int id) {
        return true;
    }

    public void addCurriculum(Curriculum c) throws SQLException {
        PreparedStatement addCurriculumStatement = conn.prepareStatement("INSERT INTO Curriculum (School, Education, Semester) VALUES (?, ?, ?)");

        try {

            addCurriculumStatement.setString(1, c.getSchool());
            addCurriculumStatement.setString(2, c.getEducation());
            addCurriculumStatement.setInt(3, c.getSemester());

            addCurriculumStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCurriculum(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("DELETE FROM Curriculum WHERE CurriculumID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    /*books methods*/
//    public ArrayList<Book> getBooks() throws Exception {
//        ArrayList<Book> results = null;
//        ResultSet resultSet = null;
//        sql = "SELECT * FROM Books";
//
//        try {
//            resultSet = stmt.executeQuery(sql);
//            results = new ArrayList<>();
//
//            while ( resultSet.next() ) {
//                results.add(new Book(
//                        resultSet.getString("Title"),
//                        resultSet.getString("Publisher"),
//                        resultSet.getString("ISBN")
//                ));
//            }
//        }
//        catch ( SQLException sqlException ){
//            System.out.println(sqlException.getMessage());
//        }
//        return results;
//    }

//    public static Book getBook(int id) {
//        return new Book();
//    }

    public static boolean editBook(int id) {
        return true;
    }

    public void addBook(Book b) throws SQLException {

    PreparedStatement addBookStatement = conn
            .prepareStatement("INSERT INTO Books (Title, Version, ISBN, PriceAB, PriceSAXO, PriceCDON, Publisher, Author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        try {
            addBookStatement.setString(1, b.getTitle());
            addBookStatement.setInt(2, b.getVersion());
            addBookStatement.setDouble(3, b.getISBN());
            addBookStatement.setDouble(4, b.getPriceAB());
            addBookStatement.setDouble(5, b.getPriceSAXO());
            addBookStatement.setDouble(6, b.getPriceCDON());
            addBookStatement.setString(7, b.getPublisher());
            addBookStatement.setString(8, b.getAuthor());

            addBookStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("DELETE FROM Books WHERE BookID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
