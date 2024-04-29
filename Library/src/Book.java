/*
 * Class: Book
 *
 * April 25, 2024
 *
 * Version 1.0
 *
 * MIT License
 */
import java.util.Date;

public class Book extends LibraryItem {
    private static double BOOK_COST = 8;
    private int numberOfPages;
            
    public Book(int numberOfPages, String title, String publisher, String author, String genre, char status) {
        super(title, publisher, author, status, genre);
        this.numberOfPages = numberOfPages;
    }

    public Book(int numberOfPages, long serialnumber, String title, String publisher, String author, String genre, char status, Date dateAvailable) {
        super(serialnumber, title, publisher, author, status, genre, dateAvailable);
        this.numberOfPages = numberOfPages;
    }

    public static double getBOOK_COST() {
        return BOOK_COST;
    }

    public static void setBOOK_COST(double BOOK_COST) {
        Book.BOOK_COST = BOOK_COST;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public static double getPrice() {
        return BOOK_COST;
    }
    public String toString(){
        return "Book:\n"+super.toString()+"\nnumber of pages: "+numberOfPages;
    }
    
    
    
}
