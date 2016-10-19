package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class UserController {
    Gson gson;
    DBConnector db = new DBConnector();


    public UserController() {
        this.gson = new Gson();
    }

    public ArrayList getUsers() {
        return db.getUsers();
    }

    public User getUser(int id) {
        return db.getUser(id);
    }

    public boolean editUser(int id, String data) throws SQLException {
        return db.editUser(id, data);
    }

    public boolean deleteUser(int id) throws SQLException {
        return db.deleteUser(id);
    }

    public boolean addUser(String data) throws Exception {
        User u = gson.fromJson(data,User.class);
        return db.addUser(u);
    }

}
