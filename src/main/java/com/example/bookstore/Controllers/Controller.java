package com.example.bookstore.Controllers;

import javafx.collections.ObservableList;
import com.example.bookstore.Models.Book;
import com.example.bookstore.Models.Person;
import com.example.bookstore.helperClasses.writingToFiles;

// Controller class for managing books and persons data

//Test !

public class Controller {

    // ObservableList to store the books data
    public static ObservableList<Book> books = writingToFiles.getBooks("res/books.txt");

    // ObservableList to store the persons data
    public static ObservableList<Person> people = writingToFiles.getPersons("res/persons.txt");

    // Total cost of books
    public static double totalCost = writingToFiles.getTotalCost("res/totalCost.bin");

    // Total bill amount for the books
    public static double totalBill = writingToFiles.getTotalBill("res/totalBill.bin");

    // Number of books sold
    public static int numberOfBooksSold = writingToFiles.getBooksSold("res/booksSold.bin");

}

