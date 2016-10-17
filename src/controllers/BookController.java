package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;

import java.util.ArrayList;

public class BookController {

    public ArrayList<Book> getBooks() throws Exception {

        DBConnector db = new DBConnector();

        return db.getBooks();
    }

    public Book getBook(int id) {
        return DBConnector.getBook(id);
    }

    public boolean editBook(int id) {
        return DBConnector.editBook(id);
    }

    public boolean deleteBook(int id) {
        return DBConnector.deleteBook(id);
    }

    public boolean addBook(String data) {
        Gson gson = new Gson();
        Book b = gson.fromJson(data,Book.class);
        return DBConnector.addBook(b);
    }

}
