package com.example.bookstore.view;

import com.example.bookstore.mainApplication;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertTrue;
//3 threads
class ManagerPanelTest extends ApplicationTest {
    Button button;
    @Override
    public void start(Stage stage) {
        mainApplication mainApp = new mainApplication();
        mainApp.start(stage);
    }

    @BeforeEach
    public void setUp() {
        button = lookup(".button").queryAs(Button.class);
    }
    @Test
    public void testAddNewBookSuccess() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);

        // Click on the "Books" menu
        clickOn("#Books");

        // Click on the "New Book" menu item
        clickOn("New Book");

        // Fill in the book details
        clickOn("#isbnInput").write("9781234567890");
        clickOn("#titleInput").write("Test Book");
        clickOn("#categoryInput").write("Test Category");
        clickOn("#supplierInput").write("Test Supplier");
        DatePicker purchasedDatePicker = lookup("#purchasedDateInput").query();
        purchasedDatePicker.setValue(LocalDate.of(2023, 1, 1));
        clickOn("#purchasedPriceInput").write("30.0");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assuming that a success message is displayed
        assertTrue(lookup("Book is added!").queryButton().isVisible());
    }

    @Test
    public void testAddNewBookEmptyCredentials() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);

        // Click on the "Books" menu
        clickOn("#Books");

        // Click on the "New Book" menu item
        clickOn("New Book");

        // Click on the "Submit" button
        clickOn("#submitButton");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assuming that a success message is displayed
        assertTrue(lookup("!OK!").queryButton().isVisible());
    }
    @Test
    public void testAddNewBookExceptionThrown() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);

        // Click on the "Books" menu
        clickOn("#Books");

        // Click on the "New Book" menu item
        clickOn("New Book");

        // Fill in the book details
        clickOn("#isbnInput").write("9781234567890");
        clickOn("#titleInput").write("Test Book");
        clickOn("#categoryInput").write("Test Category");
        clickOn("#supplierInput").write("Test Supplier");
        DatePicker purchasedDatePicker = lookup("#purchasedDateInput").query();
        purchasedDatePicker.setValue(LocalDate.of(2023, 1, 1));
        clickOn("#purchasedPriceInput").write("notNumber");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assuming that a success message is displayed
        assertTrue(lookup("OK").queryButton().isVisible());
    }

}
