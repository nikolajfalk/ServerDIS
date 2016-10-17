package model;

public class Book {
    String name, publisher, title, author;
    int version;
    Double ISBN, priceAB, priceSAXO, priceCDON;

    //Default constructor
    public Book(){

    }

    public Book(String name, String publisher, String title, String author, int version, Double ISBN, Double priceAB, Double priceSAXO, Double priceCDON) {
        this.name = name;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.priceAB = priceAB;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Double getISBN() {
        return ISBN;
    }

    public void setISBN(Double ISBN) {
        this.ISBN = ISBN;
    }

    public Double getPriceAB() {
        return priceAB;
    }

    public void setPriceAB(Double priceAB) {
        this.priceAB = priceAB;
    }

    public Double getPriceSAXO() {
        return priceSAXO;
    }

    public void setPriceSAXO(Double priceSAXO) {
        this.priceSAXO = priceSAXO;
    }

    public Double getPriceCDON() {
        return priceCDON;
    }

    public void setPriceCDON(Double priceCDON) {
        this.priceCDON = priceCDON;
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
