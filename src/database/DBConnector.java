package database;

import model.Book;
import model.Curiculum;
import model.User;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class DBConnector {

    /*5 user methods*/
    public static ArrayList<User> getUsers() {
        return new ArrayList<User>();
    }

    public static User getUser(int id) {
        return new User();
    }

    public static boolean editUser(int id) {
        return true;
    }

    public static boolean addUser(User u) {
        return true;
    }

    public static boolean deleteUser(int id) {
        return true;
    }
    
    /*Curriculum methods*/
    public static ArrayList<Curiculum> getCuriculums() {
        return new ArrayList<Curiculum>();
    }

    public static Curiculum getCuriculum(int id) {
        return new Curiculum();
    }

    public static boolean editCuriculum(int id) {
        return true;
    }

    public static boolean addCuriculum(Curiculum c) {
        return true;
    }

    public static boolean deleteCuriculum(int id) {
        return true;
    }
    
    /*books methods*/
    public static ArrayList<Book> getBooks() {
        return new ArrayList<Book>();
    }

    public static Book getBook(int id) {
        return new Book();
    }

    public static boolean editBook(int id) {
        return true;
    }

    public static boolean addBook(Book b) {
        return true;
    }

    public static boolean deleteBook(int id) {
        return true;
    }
}
