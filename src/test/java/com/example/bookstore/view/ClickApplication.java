package com.example.bookstore.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClickApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button btn = new Button("hello");
        Scene scene = new Scene(btn, 300, 300);
        stage.setScene(scene);

        btn.setOnAction(e -> btn.setText("clicked"));
        stage.show();
    }
}
