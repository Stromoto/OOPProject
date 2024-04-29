/*
 * Class: DVD
 *
 * April 24, 2024
 *
 * Version 1.0
 *
 * MIT License
 */

import java.util.Date;

public class DVD extends LibraryItem {
    private static double DVD_COST = 5;
    private double sizeInMB;

    public DVD(double sizeInMB, String title, String publisher, String author, char status, String genre) {
        super(title, publisher, author,status, genre);
        this.sizeInMB = sizeInMB;
    }

    public DVD(double sizeInMB, long serialnumber, String title, String publisher, String author, String genre, char status, Date dateAvailable) {
        super(serialnumber, title, publisher, author, status, genre, dateAvailable);
        this.sizeInMB = sizeInMB;
    }

    public static double getDVD_COST() {
        return DVD_COST;
    }

    public void setDVD_COST(double DVD_COST) { //should we put a setter la he static?
        this.DVD_COST = DVD_COST;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public void setSizeInMB(double sizeInMB) {
        this.sizeInMB = sizeInMB;
    }
    public static double getPrice(){
        return DVD_COST;
    }
    public String toString(){ //kif fina nghyr muhtawa to tring bl kbire hsb he aw no?
        return "DVD:\n" + super.toString() + "DVD size: "+sizeInMB+"MB";
    }
    
}
