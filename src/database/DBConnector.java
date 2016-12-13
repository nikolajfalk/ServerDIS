package database;

import Encrypters.Digester;
import com.google.gson.Gson;
import config.Config;
import model.Book;
import model.Curriculum;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    /**
     * Konstruktor der opretter forbindelse til databasen via config klasse.
     * Config klassens input varierer fra bruger til bruger, da man skal definere variabler til sit eget system.
     */
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + Config.getDbUrl() + ":" + Config.getDbPort() + "/" + Config.getDbName();

    //  Database credentials
    static final String USER = Config.getDbUserName();
    static final String PASS = Config.getDbPassword();

    //String sql; Not needed anymore after introducing prepared statements.
    Connection conn = null;
    Statement stmt = null;

    public DBConnector() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            this.stmt = conn.createStatement();

            System.out.println("Connected");

            //STEP 6: Clean-up environment
        } catch (SQLException se) {
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
    /**
     * Metode til at hente af alle brugere i systemet.
     * Metoden benytter sig af et SQL query til at vælge brugerne i databasen og derefter returneres de som et objekt.
    **/
    public ArrayList getUsers() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {

            PreparedStatement getUsers = conn.prepareStatement("SELECT * FROM Users WHERE DELETED = 0");

            resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                try {

                    User users = new User(
                            resultSet.getInt("UserID"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("Username"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password"),
                            resultSet.getBoolean("Usertype")
                    );

                    results.add(users);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }
    /**
     * Metode til at hente en specifik bruger.
     * Metoden henter denne gang en bruger på dens id, men det fungere på samme måde som getUsers metoden.
    **/
    public User getUser(int id) throws IllegalArgumentException {
        User user = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getUser = conn.prepareStatement("SELECT * FROM Users WHERE UserID=?");
            getUser.setInt(1, id);
            resultSet = getUser.executeQuery();


            while (resultSet.next()) {
                try {

                    user = new User(
                            resultSet.getInt("UserID"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("Username"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password"),
                            resultSet.getBoolean("Usertype")
                    );

                } catch (Exception e) {

                }
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return user;
    }

    /**
     * Metode til at ændre en specifik bruger.
     * Metoden bruger et SQL query til at "update" en brugers informationer.
     * Dernæst gemmes det i databasen.
     **/
    public boolean editUser(int id, String data) throws SQLException {
        User u = new Gson().fromJson(data, User.class);
        PreparedStatement editUserStatement = conn
                .prepareStatement("UPDATE Users SET First_Name = ?, Last_Name = ?, Username = ?, Email = ?, Password = ? WHERE userID =?");

        try {
            editUserStatement.setString(1, u.getFirstName());
            editUserStatement.setString(2, u.getLastName());
            editUserStatement.setString(3, u.getUsername());
            editUserStatement.setString(4, u.getEmail());
            editUserStatement.setString(5, Digester.hashWithSalt(u.getPassword()));
            editUserStatement.setInt(6, id);

            editUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at oprette en bruger.
     * Metoden benytter et SQL query som alle de andre, men denne gang tilføjer den en nye bruger i databasen.
     * UserID bliver automatisk tildelt brugeren.
     **/
    public boolean addUser(User u) throws Exception {

        PreparedStatement addUserStatement =
                conn.prepareStatement("INSERT INTO Users (First_Name, Last_Name, Username, Email, Password, Usertype) VALUES (?, ?, ?, ?, ?, ?)");

        try {
            addUserStatement.setString(1, u.getFirstName());
            addUserStatement.setString(2, u.getLastName());
            addUserStatement.setString(3, u.getUsername());
            addUserStatement.setString(4, u.getEmail());
            addUserStatement.setString(5, u.getPassword());
            addUserStatement.setBoolean(6, u.getUserType());

            addUserStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at slette en specifik bruger.
     * Metoden sletter en bruger på et unikt UserID.
     **/
    public boolean deleteUser(int id) throws SQLException {

        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE Users SET Deleted = 1 WHERE UserID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at hente alle pensumlister. Dog kun deres informationer, ikke bøgerne.
     * Metoden fungere på samme måde som getUsers.
     **/
    public ArrayList getCurriculums() throws IllegalArgumentException {
        ArrayList<Curriculum> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement getCurriculums = conn.prepareStatement("SELECT * FROM Curriculum ");
            resultSet = getCurriculums.executeQuery();

            while (resultSet.next()) {
                try {

                    Curriculum cul = new Curriculum(
                            resultSet.getInt("CurriculumID"),
                            resultSet.getString("School"),
                            resultSet.getString("Education"),
                            resultSet.getInt("Semester")
                    );

                    results.add(cul);

                    String test = resultSet.getString("School");
                } catch (Exception e) {
                    Integer test = resultSet.getInt("CurriculumID");

                    System.out.println(test + "WORKS");
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }
    /**
     * Metode til at hente en specifik pensumliste, igen kun informationerne for pensumlisten, ikke bøgerne..
     * Metoden fungere på samme måde som getUser.
     **/
    public Curriculum getCurriculum(int curriculumID) throws IllegalArgumentException {
        ResultSet resultSet = null;
        Curriculum curriculum = null;

        try {
            PreparedStatement getCurriculum = conn.prepareStatement("SELECT * FROM Curriculum WHERE CurriculumID=?");
            getCurriculum.setInt(1, curriculumID);
            resultSet = getCurriculum.executeQuery();

            while (resultSet.next()) {
                try {

                    curriculum = new Curriculum(
                            resultSet.getInt("CurriculumID"),
                            resultSet.getString("School"),
                            resultSet.getString("Education"),
                            resultSet.getInt("Semester")
                    );

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {

            System.out.println(sqlException.getMessage());
        }
        return curriculum;

    }

    /**
     * Metode til at ændre en specifik pensumliste.
     * Metoden fungere på samme måde som editUser.
     **/
    public boolean editCurriculum(int id, String data) throws SQLException {
        PreparedStatement editCurriculumStatement = conn.prepareStatement("UPDATE Curriculum SET School = ?, Education = ?, Semester = ? WHERE curriculumID = ?");

        Curriculum c = new Gson().fromJson(data,Curriculum.class);

        try {
            editCurriculumStatement.setString(1, c.getSchool());
            editCurriculumStatement.setString(2, c.getEducation());
            editCurriculumStatement.setInt(3, c.getSemester());
            editCurriculumStatement.setInt(4, id);

            editCurriculumStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * Metode til at oprette en pensumliste.
     * Metoden fungere på samme måde som addUser.
     **/
    public boolean addCurriculum(Curriculum c) throws SQLException {
        PreparedStatement addCurriculumStatement = conn.prepareStatement("INSERT INTO Curriculum (School, Education, Semester) VALUES (?, ?, ?)");

        try {

            addCurriculumStatement.setString(1, c.getSchool());
            addCurriculumStatement.setString(2, c.getEducation());
            addCurriculumStatement.setInt(3, c.getSemester());

            addCurriculumStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at slette en pensumliste.
     * Metoden fungere på samme måde som deleteUser.
     **/
    public boolean deleteCurriculum(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE Curriculum SET Deleted = 1 WHERE CurriculumID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at hente en pensumliste, denne gang pensumlistens bøger.
     * Metoden fungere på samme måde som getUser.
     **/
    public ArrayList<Book> getCurriculumBooks(int curriculumID) {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;


        try {
            PreparedStatement getCurriculumBooks = conn.prepareStatement("SELECT * FROM Books INNER JOIN BooksCurriculum ON Books.BookID=BooksCurriculum.BookID WHERE CurriculumID = ? ");
            getCurriculumBooks.setInt(1, curriculumID);
            resultSet = getCurriculumBooks.executeQuery();

            while ( resultSet.next() ) {
                try {

                    Book books = new Book(
                            resultSet.getInt("BookID"),
                            resultSet.getString("Publisher"),
                            resultSet.getString("Title"),
                            resultSet.getString("Author"),
                            resultSet.getInt("Version"),
                            resultSet.getDouble("ISBN"),
                            resultSet.getDouble("PriceAB"),
                            resultSet.getDouble("PriceSAXO"),
                            resultSet.getDouble("PriceCDON")
                    );

                    results.add(books);

                }catch(Exception e){

                }
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;
    }

    /**
     * Metode til at hente alle bøger.
     * Metoden fungere på samme måde som getUsers.
     **/
    public ArrayList getBooks() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getBooks = conn.prepareStatement("SELECT * FROM Books WHERE Deleted = 0 ");
            resultSet = getBooks.executeQuery();

            while (resultSet.next()) {
                try {

                    Book books = new Book(
                            resultSet.getInt("BookID"),
                            resultSet.getString("Publisher"),
                            resultSet.getString("Title"),
                            resultSet.getString("Author"),
                            resultSet.getInt("Version"),
                            resultSet.getDouble("ISBN"),
                            resultSet.getDouble("PriceAB"),
                            resultSet.getDouble("PriceSAXO"),
                            resultSet.getDouble("PriceCDON")
                    );

                    results.add(books);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    /**
     * Metode til at hente en bog.
     * Metoden fungere på samme måde som getUser.
     **/
    public Book getBook(int id) throws IllegalArgumentException {
        Book book = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getBook = conn.prepareStatement("SELECT * FROM Books WHERE BookID=? ");
            getBook.setInt(1, id);
            resultSet = getBook.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("BookID"),
                    resultSet.getString("Publisher"),
                    resultSet.getString("Title"),
                    resultSet.getString("Author"),
                    resultSet.getInt("Version"),
                    resultSet.getDouble("ISBN"),
                    resultSet.getDouble("PriceAB"),
                    resultSet.getDouble("PriceSAXO"),
                    resultSet.getDouble("PriceCDON")
            );
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return book;

    }

    /**
     * Metode til at ændre en bog.
     * Metoden fungere på samme måde som editUser.
     **/
    public boolean editBook(int id, String data) throws SQLException {
        PreparedStatement editBookStatement = conn.prepareStatement("UPDATE Books SET Title = ?, Version = ?, ISBN = ?, PriceAB = ?, PriceSAXO = ?, PriceCDON = ?, Publisher = ?, Author = ? WHERE bookID =?");
        Book b = new Gson().fromJson(data, Book.class);
        try {
            editBookStatement.setString(1, b.getTitle());
            editBookStatement.setInt(2, b.getVersion());
            editBookStatement.setDouble(3, b.getISBN());
            editBookStatement.setDouble(4, b.getPriceAB());
            editBookStatement.setDouble(5, b.getPriceSAXO());
            editBookStatement.setDouble(6, b.getPriceCDON());
            editBookStatement.setString(7, b.getPublisher());
            editBookStatement.setString(8, b.getAuthor());
            editBookStatement.setInt(9, id);

            editBookStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at tilføje en bog samt at tilføje den til en pensumliste.
     * Det er ikke muligt at tilføje en bog uden at tilføje den til en pensumliste.
     * Metoden fungere på samme måde som addUser.
     **/
    public boolean addCurriculumBook(int curriculumID, Book b) throws SQLException {
        int id;
        PreparedStatement addBookStatement = conn.prepareStatement("INSERT INTO Books (Title, Version, ISBN, PriceAB, PriceSAXO, PriceCDON, Publisher, Author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        //Book b = new Gson().fromJson(b, Book.class);
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
            ResultSet rs = addBookStatement.getGeneratedKeys();

            if(rs.next()){
                id = rs.getInt(1);

                PreparedStatement addToBooksCurriculum = conn.prepareStatement("INSERT INTO BooksCurriculum (BookID, CurriculumID) VALUES (?,?)");
                addToBooksCurriculum.setInt(1, id);
                addToBooksCurriculum.setInt(2, curriculumID);
                addToBooksCurriculum.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     * Metode til at slette en bog.
     * Metoden fungere på samme måde som deleteUser.
     **/
    public boolean deleteBook(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE Books SET Deleted = 1 WHERE BookID = ?");
        PreparedStatement deleteBooksCurriculumRows = conn.prepareStatement("DELETE FROM BooksCurriculum WHERE BookID = ?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();

            deleteBooksCurriculumRows.setInt(1, id);
            deleteBooksCurriculumRows.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Metode til at logge ind i systemet.
     * Når et brugernavn samt password bekræftes af SQL query'en, logges brugeren ind og bliver tildelt en token.
     **/
    public User authenticate(String username, String password) {

        ResultSet resultSet = null;
        User userFound = null;

        try {

            PreparedStatement authenticate = conn.prepareStatement("select * from Users where username = ? AND Password = ? AND DELETED = 0");

            authenticate.setString(1, username);
            authenticate.setString(2, Digester.hashWithSalt(password));



            resultSet = authenticate.executeQuery();

            while (resultSet.next()) {
                try {
                    userFound = new User();
                    userFound.setUserID(resultSet.getInt("UserID"));
                    userFound.setFirstName((resultSet.getString("First_Name")));
                    userFound.setLastName((resultSet.getString("Last_Name")));
                    userFound.setUsername((resultSet.getString("Username")));
                    userFound.setEmail((resultSet.getString("Email")));
                    userFound.setPassword((resultSet.getString("Password")));


                } catch (SQLException e) {

                }


            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return userFound;

    }
    /**
     * Metode til at hente en bruger ud fra en token.
     * Metoden finder en token og derudfra findes brugeren tilknyttet denne token.
     **/
    public User getUserFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        User userFromToken = null;



        try {

            PreparedStatement getUserFromToken = conn
                    .prepareStatement("select Tokens.user_id, Users.Usertype, Users.First_Name, Users.Last_Name, Users.Email, Users.username, Users.Password from Tokens inner join Users on Tokens.user_id = Users.UserID where Tokens.token = ?");
            getUserFromToken.setString(1, token);
            resultSet = getUserFromToken.executeQuery();

            while (resultSet.next()) {

                userFromToken = new User();

                userFromToken.setUserID(resultSet.getInt("user_id"));
                userFromToken.setFirstName(resultSet.getString("First_Name"));
                userFromToken.setLastName(resultSet.getString("Last_Name"));
                userFromToken.setEmail(resultSet.getString("Email"));
                userFromToken.setUsername(resultSet.getString("username"));
                userFromToken.setPassword(resultSet.getString(("Password")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return userFromToken;

    }

    /**
     * Metode til at tilføje en token.
     * Metoden fungere på samme måde som addUser.
     **/
    public void addToken(String token, int userId) {

        PreparedStatement addTokenStatement;
        try {
            addTokenStatement = conn.prepareStatement("INSERT INTO Tokens (token, user_id) VALUES (?,?)");
            addTokenStatement.setString(1, token);
            addTokenStatement.setInt(2, userId);
            addTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode til at slette en token.
     * Metoden benyttes til at slette en token, hvilket systemet skal benyttes sig af når der logges ud.
     **/
    public boolean deleteToken(String token) throws SQLException {

        PreparedStatement deleteTokenStatement = conn.prepareStatement("DELETE FROM Tokens WHERE token=?");

        try {
            deleteTokenStatement.setString(1, token);
            deleteTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}