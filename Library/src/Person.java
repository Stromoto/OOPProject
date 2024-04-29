/*
 * Class: Person
 *
 * Version 1.0
 *
 * April 22, 2024
 *
 * MIT License
 *
 */
import java.util.ArrayList;



public class Person {
    //Attributes: name, gender, age, address, phone number   and arraylist of borrowed items
    private String name, address;
    char gender;
    private int age;
    String phoneNumber;
    private ArrayList<LibraryItem> borrowedItem;

    //Constructors

    public Person(String name, String address, char gender, int age, String phoneNumber) {
        this.name = name;
        this.address = address;
        setGender(gender);
        setAge(age);
        setPhoneNumber(phoneNumber);
        borrowedItem = new ArrayList<>();
    }

    public Person(){
        this("unknown", "unknown", 'm', 0, "00-000000");
    }


    //write the getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender){
        if(gender=='f' || gender=='F') {
            this.gender = 'F';
        } else {
            this.gender = 'M';
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        } else {
            this.age = 18;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        if (checkValidNumber(phoneNumber))
            this.phoneNumber = phoneNumber;
    }

    public static boolean checkValidNumber(String phoneNb){
        if (phoneNb.length() == 9) {
            for (int i = 0; i < 9; i++) {
                if (i == 2) {
                    if (phoneNb.charAt(i) != '-')
                        return false;
                } else if (!Character.isDigit(phoneNb.charAt(i)))
                    return false;
        }
            return true;
        } else if (phoneNb.length()==14) {
            for (int i = 0; i < 14; i++) {
                if(i == 0) {
                    if (phoneNb.charAt(i) != '+')
                        return false;
                } else if(i==4 || i==7) {
                    if (phoneNb.charAt(i) != '-')
                        return false;
                } else {
                    if (!Character.isDigit(phoneNb.charAt(i)))
                return false;
                }
            }
            return true;
        }

        return false;
    }
    public ArrayList<LibraryItem> getBorrowedItem() {
        return borrowedItem;
    }


    @Override
    public String toString() {
        return name + ", " + age + " " + gender + ", residence:" + address + "\nphone number: " + phoneNumber;
    }

}


