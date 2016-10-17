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
    static final String DB_URL = "jdbc:mysql://localhost:3306/stregkode";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    String sql;
    Connection conn = null;
    Statement stmt = null;

    public ConnectionDB() {


        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            //STEP 4: Execute a query
            stmt = conn.createStatement();
            sql = "SELECT * FROM Publishers";

            //STEP 6: Clean-up environment
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
}

    public List<String> getPublishers() throws Exception {
        List<String> results = null;
        ResultSet resultSet = null;


        try
        {
            resultSet = stmt.executeQuery(sql);
            results = new ArrayList<>();

            while ( resultSet.next() )
            {
                String first = resultSet.getString("Publisher Name");
                results.add(first);
            }
        }
        catch ( SQLException sqlException )
        {

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
                resultSet.close();
                stmt.close();
                conn.close();
            }
        }
        return results;
    }
}
