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

    public Book getBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        return db.getBook(id);
    }

    public boolean editBook(int id, String data) throws Exception {
        DBConnector db = new DBConnector();
        return db.editBook(id, data);
    }

    public boolean deleteBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        return db.deleteBook(id);
    }

   /* public boolean addBook(String data) throws Exception {
        DBConnector db = new DBConnector();
        Book b = new Gson().fromJson(data,Book.class);
        return db.addCurriculumBook(b);
    }
*/
}
