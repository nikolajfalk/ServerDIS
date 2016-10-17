package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.User;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class UserController {

    public ArrayList<User> getUsers() {
        return DBConnector.getUsers();
    }

    public User getUser(int id) {
        return DBConnector.getUser(id);
    }

    public boolean editUser(int id) {
        return DBConnector.editUser(id);
    }

    public boolean deleteUser(int id) {
        return DBConnector.deleteUser(id);
    }

    public boolean addUser(String data) {
        User u = new Gson().fromJson(data,User.class);
        return DBConnector.addUser(u);
    }

}
