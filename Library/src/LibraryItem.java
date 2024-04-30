/*
* LibraryItem
*
* April 22, 2024
*
* Version 1.0
*
* MIT License
 */

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;

public class LibraryItem {
    private static double GENERAL_COST = 0;
    private long serialNumber;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private char status;
    private Date dateAvailable;
    private ArrayList<Person> pastOwners;
    /*
    *       Constructors
    */
    public LibraryItem(String title, String author, String publisher, char status, String genre) {
       setLibraryItem(title, author, publisher, status, genre);
       setSerialNumber(0L); // Sends 0 to setSerialNumber to get its own serialNumber
       setRegistration();
    }

    public LibraryItem(long serialNumber, String title, String author, String publisher, char status, String genre, Date dateAvailable) {
       setLibraryItem(title, author, publisher, status, genre);
       setSerialNumber(serialNumber);
       this.dateAvailable = dateAvailable;
    }

    public void setLibraryItem(String title, String author, String publisher, char status, String genre) {
       setTitle(title);
       setAuthor(author);
       setPublisher(publisher);
       setStatus(status);
       setGenre(genre);
       this.pastOwners = new ArrayList<>();
    }
    /*
    *       Setters and Getters
    */
    public static double getGeneralCost() {
        return GENERAL_COST;
    }

    public static void setGeneralCost(double generalCost) {
        GENERAL_COST = generalCost;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        if (serialNumber != 0L) {
            this.serialNumber = serialNumber;
        }
        else {
            int currentYear = LocalDate.now().getYear() % 10; // Gets Last Digit of Year
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddHHmmss"); // Creates a Format for serialNumber
            this.serialNumber = Long.parseLong(currentYear + LocalDateTime.now().format(dateFormat)); // Combines last digit of Year with the formatted current time
        }
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public ArrayList<Person> getPastOwners() {
            return pastOwners;
    }


    public Date getDateAvailable() {
       return dateAvailable;
    }

    /*
     *      Public Methods
     */
    public void setRegistration() {
        if (status == 'a' || status == 'r') {
            dateAvailable = new Date();   // Creates new Date
        }
        if (status == 'o') {
            dateAvailable = new Date();
            dateAvailable.setMonth(dateAvailable.getMonth() + 3);   // Creates New Date with 3 additional months added
        }
    }
    public int getTimeRemainingDays() {
        if (status == 'o') {
             LocalDate availableDate = Instant.ofEpochMilli(dateAvailable.getTime())  // Converts dateAvailable to milliseconds since epoch time
                     .atZone(ZoneId.systemDefault())                                  // Creates a ZoneDateTime with the milliseconds using local system timezone
                     .toLocalDate();                                                  // Converts ZoneDateTime to LocalDate to begin comparing it
             return (int) DAYS.between(LocalDate.now(), availableDate);               // Uses DAYS.between to check difference of days between Current Date and availableDate
        } else {
            return -1;
        }
    }
    public static double getPrice() {
        return GENERAL_COST;
    }

    public String toString() {
        return "SN: " +serialNumber + "\n" +title +" [" +genre + "]"
                +"\nby " +author +" published by " +publisher + " is "
                +(status == 'r' ? "a reference item" : (status == 'a' ? "available" : "on loan, available on " + getDateAvailable()));
    }


}
