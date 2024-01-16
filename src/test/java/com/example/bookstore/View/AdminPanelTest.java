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

import static org.junit.jupiter.api.Assertions.*;

//15 threads
public class AdminPanelTest extends ApplicationTest {
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
    public void testOpenEmployeeRegisterSuccess() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("Person is added!").queryButton().isVisible());
    }
    //From KEVIN
    @Test
    public void testOpenEmployeeRegisterEmptyCredentials() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        // Click on the "Register" button
        clickOn("#registerButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("!Ok!").queryButton().isVisible());
    }
    //From KEVIN
    @Test
    public void testOpenEmployeeRegisterExceptionThrown() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("notnumber");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("OK").queryButton().isVisible());
    }
    //From KEVIN
    @Test
    public void testAddNewBookSuccess() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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
    //From KEVIN
    @Test
    public void testAddNewBookEmptyCredentials() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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
    //From KEVIN
    @Test
    public void testAddNewBookExceptionThrown() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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
    public void testOpenEmployeeListSuccessEmployeeRegistrationUpdated() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        TableView<Person> tableView = lookup("#tablePersons").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");

        interact(() -> {
            (lookup("Person is added!").queryButton()).fire();
        });
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tablePersons").query();
        assertFalse(updatedTableView.getItems().isEmpty(), "TableView shouldnt be empty");
    }
    //From KEVIN
    @Test
    public void testOpenEmployeeListAfterErrorInEmployeeRegistration() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        TableView<Person> tableView = lookup("#tablePersons").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("not number");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");

        interact(() -> {
            (lookup("OK").queryButton()).fire();
        });
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tablePersons").query();
        assertTrue(updatedTableView.getItems().isEmpty(), "TableView should be empty");
    }
    //From KEVIN
    @Test
    public void testOpenManageBooksListSuccessUpdatedAfterBookAddition() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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
    //From KEVIN
    @Test
    public void testOpenManageBooksListAfterErrorInBookAddition() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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

    @Test
    public void testOpeningNewBillAndGeneratingABillSuccessfully() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);

        clickOn("#Bills");
        clickOn("New Bill");
        clickOn("#addBookButton");
        doubleClickOn("#bookTable");
        clickOn("OK");
        clickOn("#generateBillButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("Success!").queryButton().isVisible());

        interact(() -> {
            (lookup("Success!").queryButton()).fire();
        });
    }
    @Test
    public void testOpenNewBillAndGeneratingEmptyBillError() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);

        clickOn("#Bills");
        clickOn("New Bill");
        clickOn("#generateBillButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(lookup("Error!").queryButton().isVisible());

        interact(() -> {
            (lookup("Error!").queryButton()).fire();
        });
    }
    @Test
    public void testOpenEmployeeListEditingEmployeeSavingChangesSuccessfully() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        TableView<Person> tableView = lookup("#tablePersons").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");

        interact(() -> {
            (lookup("Person is added!").queryButton()).fire();
        });
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        clickOn("#editButton");
        clickOn("#editName").write(".N");
        clickOn("#passwordField").write("password");
        clickOn("#comboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn("#saveButton");
        assertTrue(lookup("Person is edited!").queryButton().isVisible());


    }
    @Test
    public void testOpenEmployeeListAttemptingEmptyEditCheckingError() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        TableView<Person> tableView = lookup("#tablePersons").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");

        interact(() -> {
            (lookup("Person is added!").queryButton()).fire();
        });
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        clickOn("#editButton");
        clickOn("#editName").write(".N");
        clickOn("#saveButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(lookup("Error!").queryButton().isVisible());


    }
    @Test
    public void testOpenEmployeeListDeletingEmployeeCheckingUpdatedState() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        TableView<Person> tableView = lookup("#tablePersons").query();
        Platform.runLater(() -> {
            tableView.getItems().clear();
        });

        // Click on the "Employees" menu
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Register Employee");
        clickOn("#nameField").write("John Doe");
        clickOn("#usernameField").write("johndoe");
        DatePicker datePicker = lookup("#birthdayField").query();
        datePicker.setValue(LocalDate.of(2000, 1, 1));
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password");
        clickOn("#roleComboBox").clickOn("Administrator"); // Assuming Administrator role is selected
        // Click on the "Register" button
        clickOn("#registerButton");

        interact(() -> {
            (lookup("Person is added!").queryButton()).fire();
        });
        clickOn("#Employees");
        // Click on the "Register Employee" menu item
        clickOn("Manage Employee");
        clickOn("#deleteButton");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TableView<Person> updatedTableView = lookup("#tablePersons").query();
        assertTrue(updatedTableView.getItems().isEmpty(), "TableView should be empty");
    }
    @Test
    public void testOpenEditBooksListSuccessUpdatedAfterBookEdit() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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

    @Test
    public void testOpenDeleteBookCheckingListUpdatedAfterBookEdit() {
        // Assuming valid administrator credentials
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
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
}
