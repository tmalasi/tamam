package com.example.bookstore.Controllers;

import com.example.bookstore.Models.Book;
import com.example.bookstore.Models.Person;
import com.example.bookstore.helperClasses.writingToFiles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
//TODO TRY WITHOUT MOCK

class ControllerTest {
//
//    @Mock
//    private writingToFiles mockWritingToFiles;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetBooks() {
//        // Arrange
//            ObservableList<Book> expectedBooks = FXCollections.observableArrayList();
//
//            // Add sample books to the list
//            expectedBooks.add(new Book("Title1", "Author1", 20.0, 15.0, 5.0, "Genre1", "Publisher1", "ISBN1", 100, LocalDate.now()));
//            expectedBooks.add(new Book("Title2", "Author2", 25.0, 18.0, 7.0, "Genre2", "Publisher2", "ISBN2", 120, LocalDate.now()));
//
//        when(mockWritingToFiles.getBooks(anyString())).thenReturn(expectedBooks);
//
//        // Act
//        ObservableList<Book> actualBooks = Controller.books;
//
//        // Assert
//        assertEquals(expectedBooks, actualBooks);
//    }
//
//    @Test
//    void testGetPersons() {
//        // Arrange
//        ObservableList<Person> expectedPeople = // initialize your expected people list here
//                when(mockWritingToFiles.getPersons(anyString())).thenReturn(expectedPeople);
//
//        // Act
//        ObservableList<Person> actualPeople = Controller.people;
//
//        // Assert
//        assertEquals(expectedPeople, actualPeople);
//    }
//
//    @Test
//    void testGetTotalCost() {
//        // Arrange
//        double expectedTotalCost = // set your expected total cost value here
//                when(mockWritingToFiles.getTotalCost(anyString())).thenReturn(expectedTotalCost);
//
//        // Act
//        double actualTotalCost = Controller.totalCost;
//
//        // Assert
//        assertEquals(expectedTotalCost, actualTotalCost);
//    }
//
//    @Test
//    void testGetTotalBill() {
//        // Arrange
//        double expectedTotalBill = // set your expected total bill value here
//                when(mockWritingToFiles.getTotalBill(anyString())).thenReturn(expectedTotalBill);
//
//        // Act
//        double actualTotalBill = Controller.totalBill;
//
//        // Assert
//        assertEquals(expectedTotalBill, actualTotalBill);
//    }
//
//    @Test
//    void testGetNumberOfBooksSold() {
//        // Arrange
//        int expectedNumberOfBooksSold = // set your expected number of books sold value here
//                when(mockWritingToFiles.getBooksSold(anyString())).thenReturn(expectedNumberOfBooksSold);
//
//        // Act
//        int actualNumberOfBooksSold = Controller.numberOfBooksSold;
//
//        // Assert
//        assertEquals(expectedNumberOfBooksSold, actualNumberOfBooksSold);
//    }
}
