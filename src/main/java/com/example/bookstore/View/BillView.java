package com.example.bookstore.View;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import com.example.bookstore.Controllers.Controller;
import com.example.bookstore.Models.Book;
import com.example.bookstore.Models.Librarian;
import com.example.bookstore.Models.Person;
import com.example.bookstore.helperClasses.writingToFiles;
//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class BillView extends BorderPane {
    private TableView<Book> table = new TableView<>();
    private AtomicInteger quantity = new AtomicInteger();
    private double totalPrice = 0.0;
    private int billId = writingToFiles.getNumberOfBills("res/Bills");
    private final ObservableList<Book> books1 = FXCollections.observableArrayList();
    private Label totalPriceLabel = new Label("Total: $" + totalPrice);

    public static double total = Controller.totalBill;
    public static int booksSold = Controller.numberOfBooksSold;

    private Person person;
    private Label billIdLabel;

    public BillView(ObservableList<Book> books) {

        Button addBookButton = new Button("Add Book");
        addBookButton.setId("addBookButton");
        Button generateBillButton = new Button("Generate Bill");
        generateBillButton.setId("generateBillButton");
        generateBillButton.setCursor(Cursor.HAND);
        generateBillButton.setPrefSize(100, 20);
        generateBillButton.setOnAction(e -> {
            if (!books1.isEmpty()) {
                // Call writeBill method of UtilityHelper class to write bill details into a file
                writingToFiles.writeBill(String.valueOf(billId), totalPrice, books1, "res/Bills/" + billId + ".txt");
                // Show an information alert to the user that the bill has been generated
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bill Generated");
                alert.setHeaderText("Bill has been generated in the res folder named by Bill Id.");
                ButtonType customOkButton = new ButtonType("Success!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();

                // Increase the total billed amount by the current bill amount
                total += totalPrice;

                // Update the total billed amount for the librarian if the user is a librarian
                if (getUser() instanceof Librarian) {
                    for (Person person1 : Controller.people) {
                        if (person1 instanceof Librarian) {
                            if (person1.equals(getUser())) {
                                ((Librarian) person1).setTotalBilled(((Librarian) getUser()).getTotalBilled() + totalPrice);
                            }
                        }
                    }
                    ((Librarian) getUser()).setTotalBilled(((Librarian) getUser()).getTotalBilled() + totalPrice);
                }

                // Clear the books in the bill and refresh the table
                books1.clear();
                table.refresh();

                // Reset the total price and bill id label
                totalPrice = 0;
                totalPriceLabel.setText("Total: $" + totalPrice);
                billIdLabel.setText(("Bill ID: " + ++billId));
            } else {
                // Show an error alert to the user if the bill is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An error has occurred");
                alert.setContentText("Bill is Empty. Kindly add books in the bill first.");
                ButtonType customOkButton = new ButtonType("Error!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();
            }
        });
        addBookButton.setPrefSize(100, 20);
        totalPriceLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        addBookButton.setOnAction(event -> bookTable(books));
        setCenter(billTable());
        billIdLabel = new Label("Bill ID: " + ++billId);
        billIdLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        GridPane bottomBox = new GridPane();
        bottomBox.setPadding(new Insets(15, 12, 15, 12));
        bottomBox.setVgap(20);
        bottomBox.add(billIdLabel, 0, 0);
        bottomBox.add(totalPriceLabel, 0, 1);
        bottomBox.add(addBookButton, 0, 2);
        bottomBox.add(generateBillButton, 0, 3);
        bottomBox.setAlignment(Pos.CENTER);
        setRight(bottomBox);
    }

    private TableView<Book> billTable() {

        List<TableColumn<Book, ?>> columns = new ArrayList<>();
        // Create a TableColumn object for ISBN
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        // Bind the ISBN value from the Book object to the ISBN column
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        // Create a TableColumn object for Title
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setId("titleColumn");
        // Bind the Title value from the Book object to the Title column
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // Create a TableColumn object for Category
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        // Bind the Category value from the Book object to the Category column
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Create a TableColumn object for Sell Price
        TableColumn<Book, Double> sellPriceColumn = new TableColumn<>("Sell Price");
        // Bind the Sell Price value from the Book object to the Sell Price column
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

        TableColumn<Book, Void> quantityColumn = getBookVoidTableColumn();

        List<TableColumn<Book, ?>> columnsArray = new ArrayList<>();
        columnsArray.add(isbnColumn);
        columnsArray.add(titleColumn);
        columnsArray.add(categoryColumn);
        columnsArray.add(sellPriceColumn);
        columnsArray.add(quantityColumn);


// Add all columnsArray to the table
        table.getColumns().addAll(columnsArray);
        // Set the items of the table to books1
        table.setItems(books1);
        return table;
    }

    //    @NotNull
    private TableColumn<Book, Void> getBookVoidTableColumn() {
        TableColumn<Book, Void> quantityColumn = new TableColumn<>("Quantity");
        // Define a callback function to create cells for the Quantity column
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory1 = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {
                    // Override the updateItem method
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Set the text of the cell to the value of quantity
                            setText(String.valueOf(quantity.get()));
                        }
                    }
                };
            }
        };
        // Set the cell factory for the Quantity column
        quantityColumn.setCellFactory(cellFactory1);
        return quantityColumn;
    }


    private void bookTable(ObservableList<Book> books) {
        Stage bookStage = new Stage();
        bookStage.initModality(Modality.APPLICATION_MODAL);
        bookStage.setTitle("Add Book");
        bookStage.setMinWidth(250);
        Label label = new Label();
        label.setText("Select a book to add to the bill:");

        TableView<Book> bookTable = new TableView<>();
        bookTable.setItems(books);
        bookTable.setId("bookTable");
        List<TableColumn<Book, ?>> columns = new ArrayList<>();

// Assuming TableColumn types are TableColumn<Book, Void>
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        columns.add(isbnColumn);

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        columns.add(categoryColumn);

        TableColumn<Book, Double> sellPriceColumn = new TableColumn<>("Sell Price");
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        columns.add(sellPriceColumn);

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columns.add(stockColumn);

        bookTable.getColumns().addAll(columns);
        bookTable.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setId("row" + row.getIndex()); // Assign an ID based on the row index
            return row;
        });

        // Handle the double-click event on a row in the book table
        bookTable.setOnMouseClicked(e -> {
            // Check if the event is a double-click
            if (e.getClickCount() == 2) {
                // Get the selected book from the table
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                // Check if the selected book is in stock

                if (selectedBook.getStock() > 0) {
                    // Show a text input dialog to enter the quantity of books to add

                    TextInputDialog dialog = new TextInputDialog("1");
                    dialog.setTitle("Enter Quantity");
                    dialog.setHeaderText("Enter the number of books you want to add:");
                    dialog.setContentText("Quantity:");
                    Optional<String> result = dialog.showAndWait();
                    // If the user enters a value
                    if (result.isPresent()) {
                        // Update the quantity of books to add
                        quantity.set(Integer.parseInt(result.get()));                        // Keep track of the total number of books sold
                        booksSold += quantity.get();
                        // Check if the entered quantity is valid (between 1 and the stock of the selected book)
                        //MaybeMethod
                        if (Book.canAddBook(selectedBook, quantity.get())) {
                            double totalPrice = selectedBook.getSellPrice() * quantity.get();
                            totalPriceLabel.setText("Total: $" + totalPrice);
                            totalPriceLabel.setId("totalPriceLabel");
                            // Call the model method to update the book and total
                            selectedBook.setStock(selectedBook.getStock() - quantity.get());

                            // Add the selected book to the bill
                            books1.add(selectedBook);
                            // Close the book stage
                            bookStage.fireEvent(new WindowEvent(bookStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                        } else {
                            // Show an error message if the entered quantity is invalid
                            label.setText("Invalid quantity. Please enter a value between 1 and " + selectedBook.getStock());
                        }
                    }
                } else {
                    // Show an error message if the selected book is out of stock
                    label.setText("The selected book is out of stock. Please select another book.");
                }
            }
        });


        BorderPane bookPane = new BorderPane();
        bookPane.setTop(label);
        bookPane.setCenter(bookTable);

        Scene bookScene = new Scene(bookPane, 400, 400);
        bookStage.setScene(bookScene);
        bookStage.showAndWait();
    }

    public Person getUser() {
        return person;
    }

    public void setUser(Person user) {
        this.person = user;
    }
}
