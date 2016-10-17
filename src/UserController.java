import com.google.gson.Gson;
import model.User;

import java.util.ArrayList;


/**
 * Created by Nikolaj on 17/10/2016.
 */
public class UserController {
    Gson gson;
    DBDemoUser dbdemo;

    public UserController() {
        gson = new Gson();
        dbdemo = new DBDemoUser();
    }

    public boolean addUser(String data) {
        return dbdemo.addUser(gson.fromJson(data,User.class));
    }

    public ArrayList<User> getUsers(int id, String firstName, String lastName, String email, String password, boolean isAdmin) {
        return dbdemo.getUsers(id,firstName,lastName,email,password,isAdmin);
    }

    /*
    public boolean deleteUser(User u) {
        return dbdemo.deleteUser(u);
    }*/


}
