package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {

    public ArrayList<Book> getBooks() throws Exception {

        DBConnector db = new DBConnector();

        return db.getBooks();
    }

    public ArrayList<Book> getBook(int id) {
        DBConnector db = new DBConnector();
        return db.getBook(id);
    }

    public boolean editBook(int id) {
        return DBConnector.editBook(id);
    }

    public boolean deleteBook(int id) throws SQLException {
        DBConnector db = new DBConnector();
        return db.deleteBook(id);
    }

    public boolean addBook(String data) throws SQLException {
        Gson gson = new Gson();
        DBConnector db = new DBConnector();
        Book b = gson.fromJson(data,Book.class);
        return db.addBook(b);
    }

}
