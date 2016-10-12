package model;

public class Book {
    String name, publisher, ISBN;

    //Default constructor
    public Book(){

    }

    public Book(String name, String publisher, String ISBN) {
        this.name = name;
        this.publisher = publisher;
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "model.Book{" +
                "name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
