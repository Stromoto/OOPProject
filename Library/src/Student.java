/*
 * Class: Main
 *
 * Version 1.0
 *
 * April 22, 2024
 *
 * MIT License
 *
 */

public class Student extends Person {
    private String studentId;
    private static int maxNumberToBorrow = 3;

    public Student(String name, String address, char gender, int age, String phoneNumber, String studentId) {
        super(name, address, gender, age, phoneNumber);
        this.studentId = studentId;
    }

    public Student() {
        this("unknown", "unknown", 'm', 18 , "00-000000", "unknown");
    }

    public boolean borrowItem(LibraryItem item) {
        if (getBorrowedItem().size() < maxNumberToBorrow && item.getStatus() == 'a'){
            item.setStatus('o');
            item.setRegistration();
            item.getPastOwners().add(this);
            getBorrowedItem().add(item);
            return true;
        }
        return false;
    }

    public boolean returnItem(LibraryItem item) {
        if (getBorrowedItem().contains(item)) {
            item.setStatus('a');
            item.setRegistration();
            getBorrowedItem().remove(item);
            return true;
        }
        return false;
    }

    public String getStudentId() {
        return studentId;
    }

    public static int getMaxNumberToBorrow() {
        return maxNumberToBorrow;
    }

    public static void setMaxNumberToBorrow(int maxNumberToBorrow) {
        Student.maxNumberToBorrow = maxNumberToBorrow;
    }



    @Override
    public String toString() {
        return "Student ID: " + studentId + "\n" + super.toString() + "\nNumber of borrowed items: "+ super.getBorrowedItem().size() ;
    }
}
