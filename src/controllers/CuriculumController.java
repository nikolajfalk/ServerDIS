package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Curiculum;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class CuriculumController {
    Gson gson;


    public CuriculumController(Gson gson) {
        this.gson = gson;
    }

    public String getCuriculums() {
        return gson.toJson(DBConnector.getCuriculums());
    }

    public String getCuriculum(int id) {
        return gson.toJson(DBConnector.getCuriculum(id));
    }

    public boolean editCuriculum(int id) {
        return DBConnector.editCuriculum(id);
    }

    public boolean deleteCuriculum(int id) {
        return DBConnector.deleteCuriculum(id);
    }

    public boolean addCuriculum(String data) {
        Curiculum c = gson.fromJson(data,Curiculum.class);
        return DBConnector.addCuriculum(c);
    }

}
