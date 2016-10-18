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

    public String getUsers() {
        return gson.toJson(db.getUsers());
    }

    public String getUser(int id) {
        return gson.toJson(db.getUser(id));
    }

    public boolean editUser(int id) {
        return db.editUser(id);
    }

    public boolean deleteUser(int id) throws SQLException {
        return db.deleteUser(id);
    }

    public boolean addUser(String data) throws Exception {
        User u = gson.fromJson(data,User.class);
        return db.addUser(u);
    }

}
