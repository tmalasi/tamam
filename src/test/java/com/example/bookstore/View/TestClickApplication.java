package com.example.bookstore.View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.assertions.api.Assertions.assertThat;

public class TestClickApplication extends ApplicationTest {
    Button button;
    @Override
    public void start(Stage stage) {
        Parent sceneRoot = new ClickApplication.View();
        Scene scene = new Scene(sceneRoot);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        button = lookup(".button").queryAs(Button.class);
    }

    @Test
    public void should_contain_button() {
        assertThat(button).hasText("Click me");
    }

    @Test
    public void should_click_on_button() {
        clickOn(button);
        assertThat(button).hasText("Clicked");
    }
}
