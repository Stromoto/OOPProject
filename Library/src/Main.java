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

import java.util.*;
import java.io.*;
public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<LibraryItem> myItems = new ArrayList<>();
        ArrayList<Person> myMembers = new ArrayList<>();

        try {
            LoadFromFiles(myMembers, myItems); // Method to be implemented by the student
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println("\n\n*******************************\n\n");
            int choice = getChoice();


            if (choice == 1) {                                          // Add new LibraryItem
                System.out.println("Enter the new information");
                System.out.println("---------------------------");
                boolean validLibraryItem = false;    // Exits the upcoming while loop if flag = true (if adding the LibraryItem was successful)
                while (!validLibraryItem) {
                System.out.println("What is the type of new Item (Book[B] DVD[D]): ");
                char libraryItemChoice = input.next().toUpperCase().charAt(0);
                input.nextLine();
                    if (libraryItemChoice == 'B') {
                        System.out.println("Enter the book title: ");
                        String objectTitle = input.nextLine();
                        System.out.println("Enter the author's name: ");
                        String objectAuthor = input.nextLine();
                        System.out.println("Enter the publisher's name");
                        String objectPublisher = input.nextLine();
                        System.out.println("Is the Book available [a] or a reference [r]:");
                        char objectStatus = input.next().charAt(0);
                        input.nextLine();
                        System.out.println("Enter the book's genre: ");
                        String objectGenre = input.nextLine();
                        System.out.println("Enter the number of pages: ");
                        int objectNbOfPages = input.nextInt();
                        input.nextLine();

                        myItems.add(new Book(objectNbOfPages, objectTitle, objectAuthor,
                                objectPublisher, objectGenre, objectStatus));    // Adds Book to myItems arrayList
                        System.out.println("New Book added correctly!");
                        validLibraryItem = true;
                    }
                    else if (libraryItemChoice == 'D') {
                        System.out.println("Enter the DVD title: ");
                        String objectTitle = input.nextLine();
                        System.out.println("Enter the director's name: ");
                        String objectAuthor = input.nextLine();
                        System.out.println("Enter the producer's name: ");
                        String objectPublisher = input.nextLine();
                        System.out.println("Is the DVD available [a] or a reference [r]:");
                        char objectStatus = input.next().charAt(0);
                        input.nextLine();
                        System.out.println("Enter the DVDs genre: ");
                        String objectGenre = input.nextLine();
                        System.out.println("Enter the DVDs size (in MB): ");
                        double objectMBSize = input.nextDouble();
                        input.nextLine();

                        myItems.add(new DVD(objectMBSize, objectTitle, objectPublisher,
                                objectAuthor, objectStatus, objectGenre));    // Adds DVD to myItems arrayList
                        System.out.println("New DVD added correctly!");
                        validLibraryItem = true;

                    }
                    else {
                        System.out.println("ERROR: INVALID CHOICE!");   // If char value for libraryItemChoice is invalid
                    }
                }
            }
            if (choice == 2) {                                                      // Modify An Existing LibraryItem
                System.out.println("Enter the serial number of the LibraryItem: ");
                long serialNumberItem = input.nextLong();
                input.nextLine();
                LibraryItem modifyItem = searchItemBySerialNb(serialNumberItem, myItems);
                if (modifyItem != null) {   // If the item is found
                    myItems.remove(searchItemBySerialNb(serialNumberItem, myItems));     // Removes item as it will get replaced
                    System.out.println("Pick a number to modify the item: ");
                    System.out.println("1. Modify title name: ");
                    System.out.println("2. Modify author's name: ");
                    System.out.println("3. Modify publisher's name: ");
                    System.out.println("4. Modify status: ");
                    System.out.println("5. Modify genre: ");
                    if (modifyItem instanceof Book) {
                        System.out.println("6. Modify number of pages: ");
                    } else {
                        System.out.println("6. Modify MB size: ");
                    }
                    int modifyChoice = input.nextInt();
                    input.nextLine();
                    switch (modifyChoice) {     // It's modifying time
                        case 1 -> {
                            System.out.println("Enter new title name: ");
                            String newTitle = input.nextLine();
                            modifyItem.setTitle(newTitle);
                        }
                        case 2 -> {
                            System.out.println("Enter new author name: ");
                            String newAuthor = input.nextLine();
                            modifyItem.setAuthor(newAuthor);
                        }
                        case 3 -> {
                            System.out.println("Enter new publisher name: ");
                            String newPublisher = input.nextLine();
                            modifyItem.setPublisher(newPublisher);
                        }
                        case 4 -> {
                            boolean validStatus = false;
                            while (!validStatus) {                                 // Prevents users inputting incorrect status
                                System.out.println("Enter new status: ");
                                char newStatus = input.next().charAt(0);
                                if (newStatus == 'a' || newStatus == 'r' || newStatus == 'o') {
                                    modifyItem.setStatus(newStatus);
                                    validStatus = true;
                                } else {
                                    System.out.println("INVALID STATUS!");
                                }
                            }
                        }
                        case 5 -> {
                            System.out.println("Enter new genre: ");
                            String newGenre = input.nextLine();
                            modifyItem.setGenre(newGenre);
                        }
                        case 6 -> {
                            if (modifyItem instanceof Book) {    // Ensures that unique variables of each class is appropriately modified
                                System.out.println("Enter new number of pages: ");
                                int newNbOfPages = input.nextInt();
                                input.nextLine();
                                ((Book) modifyItem).setNumberOfPages(newNbOfPages);
                            } else {
                                System.out.println("Enter new MB size: ");
                                double newMBSize = input.nextDouble();
                                input.nextLine();
                                ((DVD) modifyItem).setSizeInMB(newMBSize);
                            }
                        }
                    }


                    myItems.add(modifyItem);
                } else {
                    System.out.println("ITEM NOT FOUND!");
                }
            }


            if (choice == 3) {      // Removes an item
                boolean validOperation = false;
                while (!validOperation) {
                    System.out.println("Pick a number: ");
                    System.out.println("1. Remove by Author Name: ");
                    System.out.println("2. Remove by Serial Number: ");
                    int removeChoice = input.nextInt();
                    input.nextLine();
                    if (removeChoice == 1) {
                        System.out.println("Enter the Author Name: ");
                        String removeItemName = input.nextLine();
                        for (LibraryItem item: myItems) {
                            if (item.getAuthor().equals(removeItemName)) {
                                myItems.remove(item);
                                System.out.println("Item has been removed successfully.");
                                validOperation = true;
                            }
                        }
                        if (!validOperation)
                            System.out.println("Item has not been found!");
                    }
                    else if (removeChoice == 2) {
                        System.out.println("Enter the Serial Number: ");
                        long removeItemSerialNumber = input.nextLong();
                        for (LibraryItem item: myItems) {
                            if (item.getSerialNumber() == removeItemSerialNumber) {
                                myItems.remove(item);
                                System.out.println("Item has been removed successfully.");
                                validOperation = true;
                            }
                        }
                        if (!validOperation)
                            System.out.println("Item has not been found!");
                    }
                    else {
                        System.out.println("Invalid choice!");
                    }
                }

            }
            if (choice == 4) {      // Adds new member
                System.out.println("Enter the new information");
                System.out.println("------------------------------");
                System.out.println("Is the member a student [s] or civilian [c]: ");
                char personMemberChoice = input.next().charAt(0);
                System.out.println("Enter the ID: ");
                String personID = input.nextLine();
                boolean flag = false;
                while (!flag) {     // Ensures ID is written correctly depending on the member's status
                    if (personID.charAt(0) == 'A' && personMemberChoice == 's'
                            || personID.charAt(0) == 'C' && personMemberChoice == 'c') {
                        flag = true;
                    }
                    else {
                        System.out.println("Incorrect ID format!");
                        System.out.println("Enter the ID: ");
                        personID = input.nextLine();
                    }
                }
                System.out.println("Enter the member's name: ");
                String personName = input.nextLine();
                System.out.println("Enter the address: ");
                String personAddress = input.nextLine();
                System.out.println("Male [M] or Female [F]: ");
                char personGender = input.next().toUpperCase().charAt(0);
                while (flag) {     // Ensures gender is chosen correctly
                    if (personGender == 'M' || personGender == 'F') {
                        flag = false;
                    } else {
                        System.out.println("Wrong gender!");
                        System.out.println("Male [M] or Female [F]: ");
                        personGender = input.next().toUpperCase().charAt(0);
                    }
                }
                    System.out.println("Enter the age: ");
                    int personAge = input.nextInt();
                System.out.println("Enter the phone number: ");
                String personPhoneNumber = input.nextLine();
                    while (!flag) {     // Ensures Phone Number's format is written correctly
                        if (Person.checkValidNumber(personPhoneNumber)) {  // Check if Phone Number format is valid
                            flag = true;
                        }
                        else {
                            System.out.println("Incorrect phone number format!");
                            System.out.println("Enter the phone number: ");
                            personPhoneNumber = input.nextLine();
                        }
                    }

                if (personMemberChoice == 's') {
                    myMembers.add(new Student(personName, personAddress, personGender,
                            personAge, personPhoneNumber, personID));
                }
                else {
                    System.out.println("Enter member's initial balance: ");
                    int civilianBal = input.nextInt();
                    myMembers.add(new Civilian(personName, personAddress, personGender,
                            personAge, personPhoneNumber, personID, civilianBal));
                }

            }

           if (choice == 13) {     // Saves all items and Members into .txts before exiting
                try {
                    saveAllToFiles(myMembers, myItems);
                }
                catch(Exception e) {
                    System.out.println("error in save");
                }
                break;
            }
        }
    }
    public static void saveAllToFiles(ArrayList<Person> newMembers, ArrayList<LibraryItem> newItems) {
        String membersFilePath = "src\\members.txt";
        String itemsFilePath = "src\\items.txt";
        try (FileWriter overwriteMembersFile = new FileWriter(membersFilePath, true)) {    // Prepares to overwrite members file
            for (Person person : newMembers) {
                String personType = (person instanceof Student) ? "S" : "C";

                String personInformation = personType + "&" +
                                           person.getName() + "&" +
                                           person.getAddress() + "&" +
                                           person.getGender() + "&" +
                                           person.getAge() + "&" +
                                           person.getPhoneNumber() + "&" +
                        ((person instanceof Civilian) ? (((Civilian) person).getId() + "&"
                                                          + ((Civilian) person).getCurrentBal()) + "&"
                                                      : (((Student)person).getStudentId()) + "&");
                if (person.getBorrowedItem() != null) {
                    for (int i = 0; i < person.getBorrowedItem().size(); i++) {     // Begins adding items the Person has borrowed
                        personInformation += person.getBorrowedItem().get(i);
                        personInformation += "##";
                    }

                }
                overwriteMembersFile.write(personInformation);
                overwriteMembersFile.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred in saving members...");
            e.printStackTrace();
        }

        try (FileWriter overwriteItemsFile = new FileWriter(itemsFilePath)) {
            for (LibraryItem item : newItems) {
                String itemType = (item instanceof Book) ? "B" : "D";

                String itemInformation = itemType + "#" +
                                         item.getSerialNumber() + "#" +
                                         item.getTitle() + "#" +
                                         item.getAuthor() + "#" +
                                         item.getPublisher() + "#" +
                                         item.getStatus() + "#" +
                                         item.getGenre() + "#" +
                                         item.getDateAvailable().getTime() + "#" +
                        ((item instanceof Book) ? (((Book) item).getNumberOfPages()) + "#"
                                : (((DVD) item).getSizeInMB()) + "#");

                if (item.getPastOwners() != null) {
                    for (int i = 0; i < item.getPastOwners().size(); i++) {
                        itemInformation += item.getPastOwners().get(i);
                        itemInformation += "&&";
                    }
                }
                overwriteItemsFile.write(itemInformation);
                overwriteItemsFile.write("\n");

            }
        }
        catch (IOException e) {
            System.out.println("An error occurred in saving items...");
            e.printStackTrace();
        }
    }
    public static void LoadFromFiles(ArrayList<Person> members, ArrayList<LibraryItem> items) throws FileNotFoundException {
        ArrayList<String> borrowed= new ArrayList<>();
        ArrayList<String> owners= new ArrayList<>();
        loadAllmembers(members,borrowed, "members.txt");
        loadAllItems(items,owners, "items.txt");
        adjustOwners(members, items, owners);
        adjustBorrowed(members, items,borrowed);

    }

    public static void adjustBorrowed(ArrayList<Person> members, ArrayList<LibraryItem> items, ArrayList<String> borrowed){
        for(int i = 0; i < members.size(); i++) {
            if (borrowed.get(i) != null) {
            String[] itemsBorrowed = borrowed.get(i).split("##");
            for(String serial : itemsBorrowed){
                if (serial != null) {
                    LibraryItem item = searchItemBySerialNb(Long.parseLong(serial), items);
                    if (item != null)
                        members.get(i).getBorrowedItem().add(item);
                }
            }
        }}

    }


    public static void adjustOwners(ArrayList<Person> members, ArrayList<LibraryItem> items, ArrayList<String> owners){
        for(int i = 0 ; i < items.size(); i++) {
            if (owners.get(i) != null) {
                String[] oldOwners = owners.get(i).split("&&");
                for (String id : oldOwners) {
                    if (id != null) {
                        Person member = searchMemberById(id, members);
                    if (member != null)
                        items.get(i).getPastOwners().add(member);
                    }
            }}

        }
    }
    public static void loadAllmembers(ArrayList<Person> members,ArrayList<String> borrowed, String filePath) throws FileNotFoundException {
        File myFile = new File(filePath);
        if (!myFile.exists()) return;

        Scanner reader= new Scanner(myFile);
        while(reader.hasNext()){
            String line = reader.nextLine();
            String[] tokens= line.split("&");
            if(tokens[0].equals("C"))
            {  members.add(new Civilian(tokens[1],tokens[2],tokens[3].charAt(0),Integer.parseInt(tokens[4]),tokens[5],tokens[6], Double.parseDouble(tokens[7])));
                borrowed.add(tokens.length==9? tokens[tokens.length-1]: null);}
            else
            {
                members.add(new Student(tokens[1], tokens[2], tokens[3].charAt(0), Integer.parseInt(tokens[4]), tokens[5], tokens[6]));
                borrowed.add(tokens.length==8? tokens[tokens.length-1]: null);
            }

        }
    }
    public static void loadAllItems(ArrayList<LibraryItem> items,ArrayList<String> owners, String filePath) throws FileNotFoundException {
        File myFile= new File(filePath);
        if(!myFile.exists()) return;

        Scanner reader= new Scanner(myFile);
        while(reader.hasNext()){
            String  line = reader.nextLine();
            String[] tokens= line.split("#");
            if(tokens[0].equals("D")) {
                items.add(new DVD(Double.parseDouble(tokens[8]), Long.parseLong(tokens[1]), tokens[2],  tokens[4], tokens[3],
                        tokens[6], tokens[5].charAt(0), new Date(Long.parseLong(tokens[7]))));
            }
            else {
                items.add(new Book(Integer.parseInt(tokens[8]), Long.parseLong(tokens[1]), tokens[2], tokens[4], tokens[3],
                        tokens[6], tokens[5].charAt(0), new Date(Long.parseLong(tokens[7]))));
            }
            if (tokens.length==10)
                owners.add(tokens[9]);
            else
                owners.add(null);
        }
    }


    public static LibraryItem searchItemBySerialNb(long serialNb, ArrayList<LibraryItem> items) {
        for (LibraryItem item : items) {
            if (item.getSerialNumber() == serialNb) {
                return item;
            }
        }
        return null;
    }

    public static Person searchMemberById(String id, ArrayList<Person> members) {
        for (Person person: members) {
            if (person instanceof Student) {
                if (((Student)person).getStudentId().contains(id)) {
                    return person;
                }
            }
            else {
                if (((Civilian)person).getId().contains(id)) {
                    return person;
                }
            }
        }
        return null;
    }

    public static int getChoice() {
        int choice = -1;
        while (choice < 0 || choice > 13) {
            System.out.println("Choose a number: ");
            System.out.println("1- add new Library Item");
            System.out.println("2- modify an item");
            System.out.println("3- delete an item");
            System.out.println("4- add new member");
            System.out.println("5- modify a member info");
            System.out.println("6- delete a member info");
            System.out.println("7- Search an item// or check availability");
            System.out.println("8- Search a member");
            System.out.println("9- Borrow an item");
            System.out.println("10- Return an item");
            System.out.println("11- Display all items");
            System.out.println("12- Display all members");
            System.out.println("13- exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();
            if (choice < 0 || choice > 13) {
                System.out.println("Invalid choice!!");
            }
        }
        return choice;
    }
    
}