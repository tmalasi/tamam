package com.example.bookstore.helperClasses;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.bookstore.Models.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class writingToFiles {
    //TODO look at each method and its test clarify them

    //Method that is used to read the information about the person logging in.
    //Based on their username and password(which will be saved in a file)
    //The program decides with which credentials in the text the user's input matches with
    //In the roles.txt file,the role of the person is stored as the third element
    //The method will return data[2] for this reason.
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
        } catch (Exception exception) {
            // Print the stack trace if an IOException is thrown
            throw new RuntimeException(exception);
        }
    }

    //Method used to write books in the books.txt file
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
        } catch (Exception e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e.getMessage());
        }
        //TODO check this possibility
        //// catch (FileNotFoundException e1)
        //            e1.printStackTrace();
        //        } catch (IOException e) {
        //            System.out.println(e);
        //        }
        // Return the list of books
        return books;
    }

    //Test same with books !!
    public static ObservableList<Person> getPersons(String filepath){
        // Create an ObservableList to store the persons
        ObservableList<Person> people = FXCollections.observableArrayList();
        // Define the file location for the persons data
        try {
            File file = new File(filepath);
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
        } catch (Exception e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e.getMessage());
        }
        return people;
    }


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


    public static void writeBill(String billId, double totalBill, List<Book> books, String filePath){
        // Create a File object representing the bill file
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            File file = new File(filePath);
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
        } catch (Exception e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e.getMessage());
        }
    }


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

    public static int getBooksSold(String filePath){
        if (fileOperations.fileExists(filePath)) {
            try {
                return (int) fileOperations.readDoubleFromFile(filePath);
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

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
        } catch (Exception e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e.getMessage());
        }
    }


    public static void writePersons(String filepath, ArrayList<Person> personArrayList){

        try {
            File file = new File(filepath);
            FileWriter writer = new FileWriter(file);
            for (Person person: personArrayList){
                writer.write(person.toString() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            // Throw a runtime exception if there is an error reading the file
            throw new RuntimeException(e.getMessage());
        }
    }

    //TODO fix these
    public static void writeTotalBill(double total, String filePath, FileOutputInterface fileOutput){
            try {
                fileOutput.writeDoubleToFile(total, filePath);
            } catch (IOException e) {
                // Throw a runtime exception if there is an error writing the file
                throw new RuntimeException(e.getMessage());
            }
    }

    public static void writeTotalCost(double total, String filepath, FileOutputInterface fileOutput){
        try {
            fileOutput.writeDoubleToFile(total, filepath);
        } catch (IOException e) {
            // Throw a runtime exception if there is an error writing the file
            throw new RuntimeException(e.getMessage());
        }
    }
}

