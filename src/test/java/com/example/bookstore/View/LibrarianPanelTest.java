package com.example.bookstore.View;

import com.example.bookstore.mainApplication;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.testfx.framework.junit5.ApplicationTest;

class LibrarianPanelTest extends ApplicationTest {
    //2 threads
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
    public void testOpeningNewBillAndGeneratingABillSuccessfully() {

        // Assuming valid administrator credentials
        clickOn("#userTextField").write("123457");
        clickOn("#pwBox").write("12345");
        clickOn(button);

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
        clickOn("#userTextField").write("123457");
        clickOn("#pwBox").write("12345");
        clickOn(button);
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
}