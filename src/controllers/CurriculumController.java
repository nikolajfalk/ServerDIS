package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Curriculum;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class CurriculumController {
    DBConnector db = new DBConnector();
    Gson gson;
    public String getCurriculums()throws IllegalAccessException{
        return gson.toJson(db.getCurriculums());
    }

    public String getCurriculum(int id) {
        return gson.toJson(db.getCurriculum(id));
    }

    public boolean editCurriculum(int id) {
        return db.editCurriculum(id);
    }

    public boolean deleteCurriculum(int id) throws SQLException {
        return db.deleteCurriculum(id);
    }

    public boolean addCurriculum(String data) throws SQLException {
        Curriculum c = new Gson().fromJson(data,Curriculum.class);
        return db.addCurriculum(c);
    }

}
