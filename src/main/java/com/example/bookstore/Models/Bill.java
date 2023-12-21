package com.example.bookstore.Models;

import java.util.ArrayList;

// Model class for representing a bill
public class Bill {
    //TODO maybe test getters


    // List of books in the bill
    private ArrayList<Book> books;

    // Unique id of the bill
    private String billId;

    // Quantity of books in the bill
    private int quantity;

    // Total amount of the bill
    private double totalAmount;

    // Date of the transaction
    private String dateOfTransaction;

    // Constructor to initialize the bill object with the given details
    public Bill(ArrayList<Book> books, String billId, int quantity, double totalAmount, String dateOfTransaction) {
        this.books = books;
        this.billId = billId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.dateOfTransaction = dateOfTransaction;
    }

    // Getter method for books
    public ArrayList<Book> getBooks() {
        return books;
    }

    // Setter method for books
    public void setBooks(ArrayList<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("books cannot be null");
        }
        this.books = books;
    }

    // Getter method for bill id
    public String getBillId() {
        return billId;
    }

    // Setter method for bill id
    public void setBillId(String billId) {
        int minLength = 1;
        int maxLength = 15;// Ensure that the ISBN is not null
        if (billId == null) {
            throw new IllegalArgumentException("BillID cannot be null");
        }
        // Ensure that the ISBN does not exceed the maximum length (adjust the value accordingly)
        else if (billId.length() < minLength || billId.length() > maxLength) {
            throw new IllegalArgumentException("BillID length exceeds the maximum allowed (" + maxLength + " characters)");
        } else {
            // Set the ISBN
            this.billId = billId;
        }
    }

    // Getter method for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter method for quantity
    //test cases:
    //Set a negative quantity using setQuantity() and ensure it throws an IllegalArgumentException.
    //test cases bvt:
    //Test case 1: Set the quantity to the minimum valid value (0).
    //Set the quantity to the maximum valid value.
    public void setQuantity(int quantity) {
        if (quantity < 1 || quantity > 10) {
            throw new IllegalArgumentException("Quantity must be more than 0 and less then 10");
        } else {
            this.quantity = quantity; // Quantity is within the valid range
        }
    }

    // Getter method for total amount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Setter method for total amount
    //test Set a negative quantity using setQuantity() and ensure it throws an IllegalArgumentException
    // Set the total amount to the minimum valid value (0.0).
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Amount must be more than 0");
        } else {
            this.totalAmount = totalAmount; // total amount is within the valid range
        }
    }

    // Getter method for date of transaction
    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    // Setter method for date of transaction
    public void setDateOfTransaction(String dateOfTransaction) {
        if (dateOfTransaction == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.dateOfTransaction = dateOfTransaction;
    }

}
