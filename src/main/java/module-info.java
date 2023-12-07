module bookstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.bookstore.Models to javafx.base;
    exports com.example.bookstore;
}
