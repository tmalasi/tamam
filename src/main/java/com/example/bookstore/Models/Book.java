package com.example.bookstore.Models;

import java.time.LocalDate;

public class Book {
    private String isbn; // The ISBN of the book
    private String title; // The title of the book
    private double purchasePrice, totalPurchased; // The purchase price of the book and the total amount purchased

    private double originalPrice; // The original price of the book
    private double sellPrice; // The selling price of the book
    private String author, category, supplier; // The author, category, and supplier of the book
    private int stock; // The number of copies of the book in stock

    private LocalDate purchaseDate; // The date when the book was purchased
    // A default constructor
    public Book(){}
    // A constructor with isbn, title, purchase price, selling price, and category

    public Book(String isbn,String title,double purchasePrice,double sellPrice,String category) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.category = category;
    }

    // A constructor with isbn, title, purchase price, selling price, category, and stock
    public Book(String isbn,String title,double purchasePrice,double sellPrice,String category, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.category = category;
        this.stock = stock;
    }

    // A constructor with all attributes of the book
    public Book(String isbn, String title, double purchasePrice, double originalPrice, double sellPrice, String author, String category, String supplier, int stock, LocalDate purchaseDate) {
        this.isbn = isbn;
        this.title = title;
        this.purchasePrice = purchasePrice;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
        this.author = author;
        this.category = category;
        this.supplier = supplier;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
        this.totalPurchased = stock * purchasePrice;
    }

    // Getter method for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Getter method for totalPurchased
    public double getTotalPurchased() {
        return totalPurchased;
    }

    // Setter method for totalPurchased
    //test:eAttempt to set a negative total purchased value.
    //Set a valid positive total purchased value.

    public void setTotalPurchased(double totalPurchased) {
        if (totalPurchased < 0) {
        throw new IllegalArgumentException("cant be negative or bigger than 5000");
    } else {
            this.totalPurchased = totalPurchased;
    }
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Getter method for author
    public String getAuthor() {
        return author;
    }

    // Getter method for purchasePrice
    public double getPurchasePrice() {
        return purchasePrice;
    }

    // Getter method for sellPrice
    public double getSellPrice() {
        return sellPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        // Ensure that the category is not null
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        // Ensure that the category does not exceed the maximum length
        int maxLength = 255;
        if (category.length() > maxLength) {
            throw new IllegalArgumentException("Category length exceeds the maximum allowed (" + maxLength + " characters)");
        }
        this.category = category;
    }
    public void setIsbn(String isbn) {
        // Ensure that the ISBN is not null
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        // Ensure that the ISBN does not exceed the maximum length (adjust the value accordingly)
        int maxLength = 30;
        if (isbn.length() > maxLength) {
            throw new IllegalArgumentException("ISBN length exceeds the maximum allowed (" + maxLength + " characters)");
        }
        // Set the ISBN
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        // Ensure that the title is not null
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        // Ensure that the title does not exceed the maximum length (adjust the value accordingly)
        int maxLength = 355;
        if (title.length() > maxLength) {
            throw new IllegalArgumentException("Title length exceeds the maximum allowed (" + maxLength + " characters)");
        }

        // Set the title
        this.title = title;
    }

//Test Attempt to set a negative purchase price.
// Set a valid positive purchase price.
    public void setPurchasePrice(double purchasePrice) {
        if (purchasePrice < 0|| purchasePrice>5000) {
            throw new IllegalArgumentException("cant be negative");
        } else {
            this.purchasePrice = purchasePrice; // Stock is within the valid range
        }
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    //Test Attempt to set a negative original price.
    //Set a valid positive original price.

    public void setOriginalPrice(double originalPrice) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("cant be negative");
        } else {
            this.originalPrice = originalPrice;
        }
    }

    //Test Attempt to set a sell price less than the purchase price.
    //Test Set a valid sell price greater than or equal to the purchase price
    public void setSellPrice(double sellPrice) {
        // Ensure that sellPrice is greater than purchasePrice
        if (sellPrice < purchasePrice) {
            throw new IllegalArgumentException("Selling price must be greater than or equal to purchase price.");
        } else {
            this.sellPrice = sellPrice; // Sell price is valid
        }
    }


    public void setAuthor(String author) {
        // Ensure that the author is not null
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        // Ensure that the author does not exceed the maximum length (adjust the value accordingly)
        int maxLength = 255;
        if (author.length() > maxLength) {
            throw new IllegalArgumentException("Author length exceeds the maximum allowed (" + maxLength + " characters)");
        }
        // Set the author
        this.author = author;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        // Ensure that the supplier is not null
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        // Ensure that the supplier does not exceed the maximum length (adjust the value accordingly)
        int maxLength = 255;
        if (supplier.length() > maxLength) {
            throw new IllegalArgumentException("Supplier length exceeds the maximum allowed (" + maxLength + " characters)");
        }

        // Set the supplier
        this.supplier = supplier;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        // Ensure that the purchase date is not null
        if (purchaseDate == null) {
            throw new IllegalArgumentException("Purchase date cannot be null");
        }

//        // Optionally, you can check for other constraints on the date (e.g., not in the future)
//        LocalDate currentDate = LocalDate.now();
//        if (purchaseDate.isAfter(currentDate)) {
//            throw new IllegalArgumentException("Purchase date cannot be in the future");
//        }

        // Set the purchase date
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return this.isbn + "," + this.title + "," + this.purchasePrice +
                "," + this.originalPrice + "," + this.sellPrice + "," + this.author + ","
                + this.category + "," + this.supplier + "," + this.stock + "," + this.purchaseDate.toString();
    }

    //another toString method, used in printing the bills
    public String toStringBill() {
        return "ISBN: " + getIsbn() +
                " ,Title: " + getTitle() +
                " ,Author: " + getAuthor();
    }

    public int getStock() {
        return stock;
    }

    //Test  Attempt to set a negative stock value.
    // Set a valid positive stock value.
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock must be more than 0");
        } else {
            this.stock = stock; // Stock is within the valid range
        }
    }


}