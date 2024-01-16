package com.example.bookstore.View;

import com.example.bookstore.Models.Person;
import com.example.bookstore.mainApplication;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//6 threads
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


    @Test
    public void testOpenManageBooksListSuccessUpdatedAfterBookAddition() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        clickOn("#Books");
        clickOn("Manage Books");
        TableView<Person> tableView = lookup("#tableBooks").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
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
        clickOn("#purchasedPriceInput").write("34.4");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        interact(() -> {
            (lookup("Book is added!").queryButton()).fire();
        });
        clickOn("#Books");
        clickOn("Manage Books");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tableBooks").query();
        assertFalse(updatedTableView.getItems().isEmpty(), "TableView shouldnt be empty");
    }
    @Test
    public void testOpenManageBooksListAfterErrorInBookAddition() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        clickOn("#Books");
        clickOn("Manage Books");
        TableView<Person> tableView = lookup("#tableBooks").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
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
        clickOn("#purchasedPriceInput").write("not number");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        interact(() -> {
            (lookup("OK").queryButton()).fire();
        });
        clickOn("#Books");
        clickOn("Manage Books");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tableBooks").query();
        assertTrue(updatedTableView.getItems().isEmpty(), "TableView should be empty");
    }
    //From TEA
    @Test
    public void testOpenEditBooksListSuccessUpdatedAfterBookEdit() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        clickOn("#Books");
        clickOn("Manage Books");
        TableView<Person> tableView = lookup("#tableBooks").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
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
        clickOn("#purchasedPriceInput").write("34.4");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        interact(() -> {
            (lookup("Book is added!").queryButton()).fire();
        });
        clickOn("#Books");
        clickOn("Manage Books");
        clickOn("#editButton");
        clickOn("#titleField").write("a");
        clickOn("#SaveEdited");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("Book is edited!").queryButton().isVisible());
    }
//From TEA
    @Test
    public void testOpenDeleteBookCheckingListUpdatedAfterBookEdit() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        clickOn("#Books");
        clickOn("Manage Books");
        TableView<Person> tableView = lookup("#tableBooks").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
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
        clickOn("#purchasedPriceInput").write("34.4");
        clickOn("#originalPriceInput").write("40.0");
        clickOn("#sellingPriceInput").write("50.0");
        clickOn("#authorInput").write("Test Author");
        clickOn("#stockInput").write("10");

        // Click on the "Submit" button
        clickOn("#submitButton");

        interact(() -> {
            (lookup("Book is added!").queryButton()).fire();
        });
        clickOn("#Books");
        clickOn("Manage Books");
        clickOn("#deleteButton");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tableBooks").query();
        assertTrue(updatedTableView.getItems().isEmpty(), "TableView should be empty");
    }
    @Test
    public void testOpenLibrarianStatsViewList() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        clickOn("#Stats");
        clickOn("Stats of librarians");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tableStats").query();
        assertFalse(updatedTableView.getItems().isEmpty(), "TableView shouldn't be empty");
    }
}