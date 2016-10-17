/**
 * Created by aleksanderkristiansen on 17/10/2016.
 */

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {



    private static final String URL = "jdbc:mysql://52.58.62.183:3306/kantinen";
    private static final String USERNAME = "dummy";
    private static final String PASSWORD = "gruppe3";

    private static Connection connection = null; // manages connection

    /**
     * Constructor for establishing connection.
     * @throws Exception
     */
    public ConnectionDB() throws Exception {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            getPersonsStmt = connection.prepareStatement(
                    "SELECT * FROM customers ORDER BY email ");


        } catch (SQLException sqlException) {
            throw new DALException("Kan ikke oprette forbindelse til database");
        }

    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Method used to close the database connection
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Methods used for items.
     */

    @Override
    public List<User> getPersons() throws Exception {
        List<User> results = null;
        ResultSet resultSet = null;

        try
        {
            resultSet = getPersonsStmt.executeQuery();
            results = new ArrayList<User>();

            while ( resultSet.next() )
            {
                results.add( new User(
                        resultSet.getInt("id"),
                        resultSet.getString( "email" ),
                        resultSet.getString( "password" ),
                        resultSet.getInt( "admin" ),
                        resultSet.getDouble("saldo")));
            }
        }
        catch ( SQLException sqlException )
        {
            throw new DALException(" \"getPersons\" fejlede");
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch ( SQLException sqlException )
            {
                sqlException.printStackTrace();
                connectionDB.close();
            }
        }
        return results;
    }
}
