module bookstore {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.bookstore.Models to javafx.base;
    exports com.example.bookstore;
}
