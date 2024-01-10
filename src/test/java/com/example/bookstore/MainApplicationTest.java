package com.example.bookstore;

import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

//5 threads
public class MainApplicationTest extends ApplicationTest {
    Button button;
    @Override
    public void start(Stage stage) {
        new mainApplication().start(stage);
    }
    @BeforeEach
    public void setUp() {
        button = lookup(".button").queryAs(Button.class);
    }
    @Test
    public void testNullLogin() {
        // Simulate user interactions, for example, clicking the login button without entering credentials
        clickOn(button);
        // Check if the appropriate error message is displayed in an Alert dialog
        Button okButton = lookup("OK").queryButton();//check if the "OK" button in the alert dialog is visible
        assertTrue(okButton.isVisible());
    }
    @Test
    public void testNullRole(){
        clickOn("#userTextField").write("invalidUser");
        clickOn("#pwBox").write("invalidPassword");
        clickOn(button);
        // Check if the appropriate error message is displayed
        Button okButton = lookup("OK").queryButton();//check if the "OK" button in the alert dialog is visible
        assertTrue(okButton.isVisible());
    }

    @Test
    public void testValidCredentialsAdministrator(){
        clickOn("#userTextField").write("admin");
        clickOn("#pwBox").write("admin");
        clickOn(button);
        verifyThat("#administratorPanel", Node::isVisible);
    }

    @Test
    public void testValidCredentialsLibrarian(){
        clickOn("#userTextField").write("Llaca");
        clickOn("#pwBox").write("12345");
        clickOn(button);
        verifyThat("#librarianPanel", Node::isVisible);
    }

    @Test
    public void testValidCredentialsManager(){

        clickOn("#userTextField").write("1");
        clickOn("#pwBox").write("12345678");
        clickOn(button);
        verifyThat("#managerPanel", Node::isVisible);
    }

}
