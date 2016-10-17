/**
 * Created by aleksanderkristiansen on 17/10/2016.
 */

import javax.xml.transform.TransformerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {


    private static final String URL = "jdbc:mysql://db4free.net/bookit";
    private static final String USERNAME = "bookit";
    private static final String PASSWORD = "bookit";

    private static Connection connection = null; // manages connection
    private PreparedStatement getSchoolsStmt;

    /**
     * Constructor for establishing connection.
     *
     * @throws Exception
     */
    public ConnectionDB() throws Exception {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            getSchoolsStmt = connection.prepareStatement(
                    "select DISTINCT School from Curicullum");


        } catch (SQLException sqlException) {

//            throw new DALException("Kan ikke oprette forbindelse til database");
            sqlException.printStackTrace();
        }

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


    public List<String> getSchools() throws Exception {
        List<String> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = getSchoolsStmt.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(resultSet.getString("School"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }
}
