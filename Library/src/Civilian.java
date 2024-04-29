/*
 * Class: Civilian
 *
 * Version 1.0
 *
 * April 22, 2024
 *
 * MIT License
 *
 */

public class Civilian extends Person {
    private String id;
    private double currentBal;

    public Civilian(String name, String address, char gender, int age, String phoneNumber, String id, double currentBal) {
        super(name, address, gender, age, phoneNumber);
        this.id = id;
        setCurrentBal(currentBal);
    }
    public Civilian(){ this("unkown", "unknown", 'm', 18 , "00-000000", "unknown",50 );}

    //getters and setters

    private void setCurrentBal(double currentBal) {
        if (currentBal > 0)
            this.currentBal = currentBal;
        else this.currentBal = 50;
    }

    public void addCredit(double amount){
        if(amount > 0)
            setCurrentBal(currentBal+amount);
    }


    public double getCurrentBal() {
        return currentBal;
    }

    public String getId() {
        return id;
    }

    public boolean borrowItem(LibraryItem item) {
        if (item.getPrice() <= currentBal && item.getStatus() == 'a') {
            currentBal -=   item.getPrice();
            item.setStatus('o');
            item.setRegistration();
            item.getPastOwners().add(this);
            getBorrowedItem().add(item);
            return true;
        }
        return false;
    }

    public boolean returnItem(LibraryItem item) {
        if (getBorrowedItem().contains(item)){
            item.setStatus('a');
            item.setRegistration();
            getBorrowedItem().remove(item);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Civilian: " +"ID: " + id + "\n" + super.toString() + "\ncurrent Balance: " + currentBal
                + "\nnumber of borrowed items: " + super.getBorrowedItem().size();
    }

}

