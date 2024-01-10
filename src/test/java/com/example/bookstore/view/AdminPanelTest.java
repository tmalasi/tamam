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
}
