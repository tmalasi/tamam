package com.example.bookstore.helperClasses;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.bookstore.Models.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class writingToFiles {

    //Method that is used to read the information about the person logging in.
    //Based on their username and password(which will be saved in a file)
    //The program decides with which credentials in the text the user's input matches with
    //In the roles.txt file,the role of the person is stored as the third element
    //The method will return data[2] for this reason.

    //test read credentials for : valid , invalid password , not existent username
    //Test for exception!
    public static String readCredentials(String username, String password, String filepath) {
        // Create a file object for the roles file
        try {
            File file = new File(filepath);
            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);
            // Read each line of the file
            while (scanner.hasNext()) {
                // Split the line into an array using the comma as a separator
                String[] data = scanner.nextLine().split(",");
                // Check if the username and password match the values in the array
                if (data[0].equalsIgnoreCase(username) && data[1].equalsIgnoreCase(password)) {
                    // Return the role if the match is found
                    return data[2];
                }
            }
            // Return null if no match is found
            return null;
        } catch (Exception ex) {
            // Return null if an exception is thrown while reading the file
            return null;
        }
    }

    //Method used to write roles in the roles.txt file

    //Test for roles file is created successfully, roles file contains the correct number of lines,
    // roles file contains the expected data.

    //Test for exceptions.
    public static void writeRoles(String filePath, ArrayList<Person> peopleList) {
        // Create a file object for the roles file
        try {
            File file = new File(filePath);
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(file);
            //As the first line we need to write some credentials that we ourselves will use to log in
            // Write the first line of the file with the administrator credentials
            writer.write("admin,admin,Administrator");
            // "people" is an ObservableList that is used to store people,getting
            //information from another method called getPersons.
            //Accessing it through the Controller component we write
            //each person's username, password, and role in separate lines
            for (Person person : peopleList) {
                writer.write("\n" + person.getUserName() + "," + person.getPassword() + "," + person.getRole().toString());
            }
            // Close the writer to save the file
            writer.close();
        } catch (IOException exception) {
            // Print the stack trace if an IOException is thrown
            throw new RuntimeException(exception);
        }
    }

    //Method used to write books in the books.txt file

    //Test if the method returns a non-null ObservableList,
    // list contains the correct number of books.
    //if the book attributes are correctly populated.!!!
    public static ObservableList<Book> getBooks( String filepath){
        // Create an ObservableList to store the books
        ObservableList<Book> books = FXCollections.observableArrayList();
        // Define the file location for the books data
        try {
            File file = new File( filepath);
// Check if the file has been created
            if (file.exists()){
                // Create a Scanner object to read the file
                Scanner scanner = new Scanner(file);
                // Read the file line by line
                while (scanner.hasNextLine()){
                    // Split the line into data fields
                    String[] Data = scanner.nextLine().split(",");
                    // Create a new book object using the data fields
                    books.add(new Book(Data[0],Data[1],Double.parseDouble(Data[2]),Double.parseDouble(Data[3]),Double.parseDouble(Data[4]),Data[5],Data[6],Data[7],Integer.parseInt(Data[8]), LocalDate.parse(Data[9])));
                }
            }
        } catch (IOException e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e);
        }
        // Return the list of books
        return books;
    }

    //Test same with books !!
    public static ObservableList<Person> getPersons(String filepath){
        // Create an ObservableList to store the persons
        ObservableList<Person> people = FXCollections.observableArrayList();
        // Define the file location for the persons data
        File file = new File(filepath);
        try {
            // Create the file if it does not exist
                if (file.exists()) {
// If it exists, return the number of files (bills) in the directory

                    // Create a Scanner object to read the file
                    Scanner scanner = new Scanner(file);
                    // Read the file line by line
                    while (scanner.hasNextLine()){
                        // Split the line into data fields
                        String[] Data = scanner.nextLine().split(",");
                        // Determine the type of person based on the role field
                        if (Data[6].equalsIgnoreCase("Librarian")){
                            // Create a new Librarian object using the data fields
                            people.add(new Librarian(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Librarian,Double.parseDouble(Data[7])));
                        }else if (Data[6].equalsIgnoreCase("Manager")){
                            // Create a new Manager object using the data fields
                            people.add(new Manager(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Manager));
                        }else if (Data[6].equalsIgnoreCase("Administrator")){
                            // Create a new Administrator object using the data fields
                            people.add(new Administrator(Data[0],Data[4],Data[5],Data[1],Integer.parseInt(Data[3]),Data[2],Role.Administrator));
                        }
                    }
                }
        } catch (IOException e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e);
        }
        return people;
    }

    //mock controller
    //Test Check if the method returns the correct number of librarians.
    public static String getNumberOfLibrarians(ArrayList<Person> peopleList){
        // retrieve list of people from the BooksPersonsController
        int numberOfLibrarians = 0;
        // iterate over each person in the list
        for (Person person: peopleList) {
            // check if the person is an instance of Librarian
            if (person instanceof Librarian){
                numberOfLibrarians++;
            }
        }
        // return the number of Librarians as a string
        return String.valueOf(numberOfLibrarians);
    }

    //Test Check if the method returns the correct number of managers
    public static String getNumberOfManagers(ArrayList <Person> peopleList){
        // retrieve list of people from the BooksPersonsController
        int numberOfManagers = 0;
        // iterate over each person in the list
        for (Person person: peopleList) {
            // check if the person is an instance of Manager
            if (person instanceof Manager){
                numberOfManagers++;
            }
        }
        // return the number of Managers as a string
        return String.valueOf(numberOfManagers);
    }

    //Test Check if the method returns the correct number of bills.
    //Check if the method handles the absence of the Bills directory.
    public static int getNumberOfBills(String Filepath){
// Create a File object representing the directory "res/Bills"
        File file = new File(Filepath);
// Check if the directory exists
        try{
            if (file.exists()) {
// If it exists, return the number of files (bills) in the directory
                return Objects.requireNonNull(file.listFiles()).length;
            }}
        catch(NullPointerException e){
            throw new RuntimeException(e);
        }
// If the directory does not exist, return 0
        return 0;
    }

    //if the bill file contains the correct book information.
    //if the total bill is correctly written.
    public static void writeBill(String billId, double totalBill, List<Book> books){
        // Create a File object representing the bill file
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            File file = new File("res/Bills/" + billId + ".txt");
            // Create a new file
            if (file.createNewFile()){
            // Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(file);
            // Write the bill header information
            writer.write("Bill Id: " + billId);
            writer.write("\nDate: " + dateFormat.format(calendar.getTime()));
            // Write the books included in the bill
            int i = 0;
            for (Book book: books) {
                writer.write("\n" + ++i + ": " + book.toStringBill());
            }
            // Write the total bill amount
            writer.write("\nTotal: " + totalBill);
            // Close the FileWriter
            writer.close();}
        } catch (IOException e) {
            // Throw a runtime exception if an IOException occurs
            throw new RuntimeException(e);
        }
    }

    //Test Check if the method returns the correct total bill.
    //Test Check if the method handles the absence of the totalBill file.
    //use mocking to isolate the method from external dependencies, such as file operations.
    // This allows you to test the logic of your method without
    // actually reading from a file or performing other external operations.
    private static FileOperations fileOperations = new DefaultFileOperations();

    public writingToFiles(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    public static double getTotalBill(String filepath) {
        if (fileOperations.fileExists(filepath)) {
            try {
                return fileOperations.readDoubleFromFile(filepath);
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

    //Test Check if the method returns the correct total cost.
    //Test Check if the method handles the absence of the totalCost file.
    public static double getTotalCost(String filePath) {
        if (fileOperations.fileExists(filePath)) {
            try {
                return fileOperations.readDoubleFromFile(filePath);
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

    //Test Check if the method returns the correct number of books sold.
    //Test Check if the method handles the absence of the booksSold file.
    public static int getBooksSold(String filePath){
// Check if the file "res/booksSold.bin" exists
        if (fileOperations.fileExists(filePath)) {
            try {
                return (int) fileOperations.readDoubleFromFile(filePath);
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

    //Test Check if the books file contains the correct book information.
    public static void writeBooks(String filepath, ArrayList<Book> bookArrayList){
        // Create the file "res/books.txt"
        try {
            File file = new File(filepath);
            // Create a FileWriter instance to write the data to the file
            FileWriter writer = new FileWriter(file);
            // Write each book's information to the file
            for (Book book: bookArrayList){
                writer.write(book.toString() + "\n");
            }
            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            // In case of IOException, throw a new RuntimeException with the caught exception
            throw new RuntimeException(e);
        }
    }


    //Test Check if the persons file contains the correct person information.
    //mockfilewriter
    public static void writePersons(String filepath, ArrayList<Person> personArrayList){
        File file = new File(filepath);
        try {
            FileWriter writer = new FileWriter(file);
            for (Person person: personArrayList){
                writer.write(person.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Check if the totalBill file contains the correct total.
    public static void writeTotalBill(double total, String filePath){
        File file = new File(filePath);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeDouble(total);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Check if the totalCost file contains the correct total.
    public static void writeTotalCost(double total, String filepath){
        File file = new File(filepath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeDouble(total);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

