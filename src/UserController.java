import com.google.gson.Gson;
import model.User;

import java.util.ArrayList;


/**
 * Created by Nikolaj on 17/10/2016.
 */
public class UserController {
    Gson gson;
    DBDemoUser dbdemo = new DBDemoUser();

    public UserController() {
    }

    public boolean addUser(int id, String firstName, String lastName, String email, String password, boolean isAdmin) {
        return dbdemo.addUser(id, firstName, lastName, email, password, isAdmin);
    }

    public boolean addUser(User u) {
        return dbdemo.addUser(u);
    }

    public ArrayList<User> getUsers(int id, String firstName, String lastName, String email, String password, boolean isAdmin) {
        return dbdemo.getUsers(id,firstName,lastName,email,password,isAdmin);
    }

    /*
    public boolean deleteUser(User u) {
        return dbdemo.deleteUser(u);
    }*/


}
