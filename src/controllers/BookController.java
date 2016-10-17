package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class BookController {
    Gson gson;


    public BookController() {
        this.gson = new Gson();
    }

    public String getBooks() {
        return gson.toJson(DBConnector.getBooks());
    }

    public String getBook(int id) {
        return gson.toJson(DBConnector.getBook(id));
    }

    public boolean editBook(int id) {
        return DBConnector.editBook(id);
    }

    public boolean deleteBook(int id) {
        return DBConnector.deleteBook(id);
    }

    public boolean addBook(String data) {
        Book b = gson.fromJson(data,Book.class);
        return DBConnector.addBook(b);
    }

}
