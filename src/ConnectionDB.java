/**
 * Created by aleksanderkristiansen on 17/10/2016.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {

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

    public ConnectionDB() {

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

    public List<String> getPublishers() throws Exception {
        List<String> results = null;
        ResultSet resultSet = null;
        sql = "SELECT * FROM Books";

        try {
            resultSet = stmt.executeQuery(sql);
            results = new ArrayList<>();

            while ( resultSet.next() ) {
                String first = resultSet.getString("Title");
                results.add(first);
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;
    }
}
