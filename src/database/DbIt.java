package database;

/**
 * Created by madsgade on 17/10/2016.
 */
public class DbIt {

}



/**
 * The server-side implementation of the RPC service.
 * In this class every method wont be explained in details since they more or less a the same
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {


    /**
     * This method is used for the login by authorizing the username and password in the database
     */
    @Override
    public User authorizeUser(String username, String password) throws IllegalArgumentException {
        // By making a SELECT/executeQuery to the database the data will presented/saved in a ResultSet
        ResultSet resultSet = null;
        User user = null;

        // Using a catch since a query to the database can fail
        try {

			/*
			 * The PreparedStatement which is used to make authorize a user.
			 * This statement will return all users which both have a specific username and password in combination
			 * The username and password is set later in this method
			 */
            PreparedStatement authorizeUser = connection
                    .prepareStatement("SELECT * FROM users where username = ? AND password = ?");
			/*
			 * In the next two lines the username and password is set.
			 * The 1 is referring to the first question mark and the 2 is referring to the second question mark
			 */
            authorizeUser.setString(1, username);
            authorizeUser.setString(2, password);

            // The PreparedStatement is executed and data is loaded into a resultSet
            resultSet = authorizeUser.executeQuery();

            // This loop will create and set a user if anyone found and there by returned in the resultSet
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSaldo(resultSet.getInt("saldo"));
                user.setType(resultSet.getInt("type"));

            }
            // The catch which is used if either the statement or connection is failing
        } catch (SQLException e) {
            e.printStackTrace();
            // A finally try catch which closes the result set and it can't then close the db connection
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return user;
    }



    /**
     * This method is changing a specific users info in the database
     */
    @Override
    public boolean changeUserInfo(User user) throws IllegalArgumentException {
        try {
            // Look at the previous method
            PreparedStatement updateUser = connection.prepareStatement("update brugere SET First name=?, Last name=?, E-mail=?, " +
                    "Username = ?, Password = ? where id = ?\n");

            updateUser.setString(1, user.getPassword());
            updateUser.setInt(2, user.getSaldo());
            updateUser.setInt(3, user.getId());

            int rowsAffected = updateUser.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeFoodPrice(Food food) throws IllegalArgumentException {
        try {
            // Look at the previous method
            PreparedStatement updatePrice = connection.prepareStatement("UPDATE food SET foodPrice = ? WHERE foodName = ?");

            updatePrice.setInt(1, food.getFoodprice());
            updatePrice.setString(2, food.getFoodname());


            int rowsAffected = updatePrice.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeUserPassword(User user) throws IllegalArgumentException {
        try {
            // Look at the previous method
            PreparedStatement updatePassword = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");

            updatePassword.setString(1, user.getPassword());
            updatePassword.setString(2, user.getUsername());


            int rowsAffected = updatePassword.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }



    @Override
    public List<User> getUsers() throws Exception {

        List<User> userresults = null;
        ResultSet userresultSet = null;
        //Her fortælles der at den ikke skal hente af typen 1 men kun typen 2 og derfor
        //hentes admin ikke og derfor kan admin ikke slettes i programmet
        PreparedStatement getUsersStmt = connection
                .prepareStatement("SELECT * FROM brugere WHERE type != 1");

        try {
            userresultSet = getUsersStmt.executeQuery();
            userresults = new ArrayList<User>();

            while (userresultSet.next()) {
                userresults.add(new User(userresultSet.getInt("id"), userresultSet.getString("navn"),
                        userresultSet.getString("kodeord"), userresultSet.getInt("type")));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } finally {
            try {
                userresultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return userresults;
    }

} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return users;
    }


    /**
     * This method is creating a new user in the database
     */
    @Override
    public boolean createUser(User user) throws IllegalArgumentException {
        try {
            // Same concept as createMessage method
            PreparedStatement createUser = connection
                    .prepareStatement("INSERT INTO Users (First_Name, Last_Name, Username, Email, Password, UserType) VALUES (\"?\", \"?\", \"?\", \"?\", \"?\", 1)");

            createUser.setString(1, user.getFirst_name());
            createUser.setString(2, user.getLast_Name());
            createUser.setString(3, user.Username());
            createUser.setString(4, user.Email());
            createUser.setString(5, user.Password());
            createUser.setInt(6, user.UserType());


            int rowsAffected = createUser.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean createBook(Food food) throws IllegalArgumentException {
        try {
            // Same concept as createMessage method
            PreparedStatement createFood = connection
                    .prepareStatement("INSERT INTO food (foodname, foodprice) VALUES (?,?)");

            createFood.setString(1, food.getFoodname());
            createFood.setString(2, Integer.toString(food.getFoodprice()));

            int rowsAffected = createFood.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is deleting a specific user in the database
     */
    @Override
    public boolean deleteUser(User user) throws IllegalArgumentException {
        try {
            // Same concept as createMessage method
            PreparedStatement createUser = connection
                    .prepareStatement("UPDATE Users SET Deleted=1 WHERE Username = '?' AND Password = '?'");

            createUser.setString(1, user.Username());
            createUser.setString(2, user.Password());


            int rowsAffected = deleteUser.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFood(int id) throws IllegalArgumentException {
        try {
			/*
			 * This statement is deleting a row/rows in the users table by id. There should only be on user with a id
			 * since this should be unique
			 */
            PreparedStatement deleteFood = connection.prepareStatement("DELETE FROM food WHERE id = ?");

            deleteFood.setInt(1, id);

            int rowsAffected = deleteFood.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

}
