package com.example.bookstore.View;


import com.example.bookstore.Models.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import com.example.bookstore.Controllers.Controller;
import com.example.bookstore.helperClasses.Role;
import com.example.bookstore.helperClasses.writingToFiles;

import java.time.LocalDate;
import java.util.ArrayList;

public class AdminPanel extends BorderPane {

    MenuBar bar;
    public BooksView booksView = new BooksView(Controller.books);
    public PersonsView personsView = new PersonsView(Controller.people);
    public BillView billView = new BillView(Controller.books);
    Background background;

    // Constructor for the AdminPanel class
    public AdminPanel() {

        // Setting the background for the BorderPane with a gradient of two colors
        background = new Background(new BackgroundFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.REPEAT,                  //cycling
                new Stop(0, Color.web("#89CFF0")),
                new Stop(1, Color.web("#ffffff")))   //colors
                , null, null));
        setBackground(background);

        // Setting the center of the BorderPane to the homePage() method
        setCenter(homePage());

        // Creating the "Home" menu with a single menu item
        Menu men = new Menu("Home");
        men.setStyle("-fx-font-size: 9pt; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        MenuItem men1 = new MenuItem("Home");
        men1.setOnAction(actionEvent -> {
            // Setting the center of the BorderPane to the homePage() method when the menu item is clicked
            setCenter(homePage());
        });
        men.getItems().add(men1);

        // Creating the "Employees" menu with two menu items
        Menu menu = new Menu("Employees");
        menu.setId("Employees");
        menu.setStyle("-fx-font-size: 9pt; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        MenuItem item = new MenuItem("Register Employee");
        item.setOnAction(event -> {
            // Setting the center of the BorderPane to the registerEmployee() method when the menu item is clicked
            setCenter(registerEmployee());
        });
        MenuItem item1 = new MenuItem("Manage Employee");
        item1.setOnAction(actionEvent -> {
            // Setting the center of the BorderPane to the personsView object when the menu item is clicked
            setCenter(personsView);
        });

        // Creating the "Books" menu with two menu items
        Menu menu1 = new Menu("Books");
        menu1.setId("Books");
        menu1.setStyle("-fx-font-size: 9pt; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
        MenuItem item2 = new MenuItem("New Book");
        item2.setOnAction(event -> {
            // Setting the center of the BorderPane to the addNewBook() method when the menu item is clicked
            setCenter(addNewBook());
        });
        MenuItem item3 = new MenuItem("Manage Books");
        item3.setOnAction(event -> {
            // Setting the center of the BorderPane to the booksView object when the menu item is clicked
            setCenter(booksView);
        });

        // Create a menu named "Bills"
        Menu menu2 = new Menu("Bills");
        menu2.setId("Bills");

// Set font style for the menu
        menu2.setStyle("-fx-font-size: 9pt; -fx-font-family: 'Arial'; -fx-font-weight: bold;");

// Create a menu item named "New Bill"
        MenuItem item4 = new MenuItem("New Bill");

// Set action for the menu item
        item4.setOnAction(actionEvent -> {
// Set the center of the BorderPane to the billView object
            setCenter(billView);
        });

// Add the employee related menu items to the employees menu
        menu.getItems().add(item);
        menu.getItems().add(item1);

// Add the book related menu items to the books menu
        menu1.getItems().addAll(item2, item3);

// Add the bill related menu item to the bills menu
        menu2.getItems().addAll(item4);

// Create a menu bar and set its cursor to HAND
        bar = new MenuBar();
        bar.setCursor(Cursor.HAND);

// Add all the menus to the menu bar
        bar.getMenus().addAll(men, menu, menu1, menu2);

// Set the background color of the menu bar
        bar.setStyle("-fx-background-color: lightgray;");
        VBox vBox = new VBox(bar);
        setTop(vBox);
    }

    private GridPane registerEmployee() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setBackground(background);

        Text sceneTitle = new Text("Register Employee");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 15));
        sceneTitle.setFill(Color.rgb(80, 80, 80));
        grid.add(sceneTitle, 0, 0);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setId("nameField");
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setId("usernameField");
        grid.add(usernameLabel, 0, 2);
        grid.add(usernameField, 1, 2);

        Label birthdayLabel = new Label("Birthday:");
        DatePicker birthdayField = new DatePicker();
        birthdayField.setId("birthdayField");
        grid.add(birthdayLabel, 0, 3);
        grid.add(birthdayField, 1, 3);

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();
        phoneField.setId("phoneField");
        grid.add(phoneLabel, 0, 4);
        grid.add(phoneField, 1, 4);

        Label salaryLabel = new Label("Salary:");
        TextField salaryField = new TextField();
        salaryField.setId("salaryField");
        grid.add(salaryLabel, 0, 5);
        grid.add(salaryField, 1, 5);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setId("passwordField");
        grid.add(passwordLabel, 0, 6);
        grid.add(passwordField, 1, 6);

        Label roleLabel = new Label("Role:");
        ComboBox<Role> roleComboBox = new ComboBox<>();
        roleComboBox.setId("roleComboBox");
        roleComboBox.getItems().addAll(Role.Administrator, Role.Manager, Role.Librarian);
        roleComboBox.setPromptText("Select Role");
        grid.add(roleLabel, 0, 7);
        grid.add(roleComboBox, 1, 7);

        Button registerButton = new Button("Register");
        registerButton.setId("registerButton");
        registerButton.setPrefWidth(100);
        registerButton.setDefaultButton(true);

        final Color startColor = Color.web("#6C6C6CFF");
        final Color endColor = Color.web("#FFFFFF");

        final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.web("#FFFFFF"));

        // String that represents the color above as a JavaFX CSS function:
        // -fx-body-color: rgb(r, g, b);
        // with r, g, b integers between 0 and 255
        final StringBinding cssColorSpec = Bindings.createStringBinding(() -> String.format("-fx-body-color: rgb(%d, %d, %d);",
                (int) (256 * color.get().getRed()),
                (int) (256 * color.get().getGreen()),
                (int) (256 * color.get().getBlue())), color);

        // bind the button's style property
        registerButton.styleProperty().bind(cssColorSpec);
        registerButton.setCursor(Cursor.HAND);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(color, startColor)),
                new KeyFrame(Duration.seconds(1), new KeyValue(color, endColor)));

        registerButton.setOnAction(event -> {
            // Play the timeline
            timeline.play();

            // Check if all fields are filled
            //might crate as method and test
            if (!(usernameField.getText().isEmpty() || nameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                    birthdayField.getValue() == null || salaryField.getText().isEmpty() || phoneField.getText().isEmpty() || roleComboBox.getSelectionModel().isEmpty())) {
                try {

                    Role selectedRole = roleComboBox.getSelectionModel().getSelectedItem();
                    Person newPerson = Person.createPerson(
                            nameField.getText(),
                            usernameField.getText(),
                            passwordField.getText(),
                            birthdayField.toString(),
                            Integer.parseInt(salaryField.getText()),
                            phoneField.getText(),
                            selectedRole
                    );
                    newPerson.setName(nameField.getText());
                    newPerson.setUserName(usernameField.getText());
                    newPerson.setPassword(passwordField.getText());
                    newPerson.setBirthday(birthdayField.toString());
                    newPerson.setSalary(Integer.parseInt(salaryField.getText()));
                    newPerson.setPhone(phoneField.getText());
                    Controller.people.add(newPerson);
                    // Increment the total cost with the salary entered
                    Controller.totalCost += Integer.parseInt(salaryField.getText());

                    // Clear all fields
                    usernameField.setText("");
                    nameField.setText("");
                    passwordField.setText("");
                    salaryField.setText("");
                    phoneField.setText("");
                    birthdayField.setValue(null);

                    // Print the updated total cost
                    System.out.println(Controller.totalCost);

                    // Show success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successful!");
                    ButtonType customOkButton = new ButtonType("Person is added!", ButtonType.OK.getButtonData());
                    alert.getButtonTypes().setAll(customOkButton);
                    alert.showAndWait();
                } catch (Exception e) {
                    // Catch any exception and show error alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                // Show error alert if all fields are not filled
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("There is some error, check if all fields are filled!");
                ButtonType customOkButton = new ButtonType("!Ok!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();
            }
        });
        grid.add(registerButton, 1, 8);
        return grid;
    }

    private GridPane addNewBook() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);

        // ISBN of the book
        Label isbnLabel = new Label("ISBN:");
        GridPane.setConstraints(isbnLabel, 0, 0);

        TextField isbnInput = new TextField();
        isbnInput.setId("isbnInput");

        GridPane.setConstraints(isbnInput, 1, 0);

        // Title of the book
        Label titleLabel = new Label("Title:");
        GridPane.setConstraints(titleLabel, 0, 1);

        TextField titleInput = new TextField();
        titleInput.setId("titleInput");
        GridPane.setConstraints(titleInput, 1, 1);

        // Category of the book
        Label categoryLabel = new Label("Category:");
        GridPane.setConstraints(categoryLabel, 0, 2);

        TextField categoryInput = new TextField();
        categoryInput.setId("categoryInput");
        GridPane.setConstraints(categoryInput, 1, 2);

        // Supplier
        Label supplierLabel = new Label("Supplier:");
        GridPane.setConstraints(supplierLabel, 0, 3);

        TextField supplierInput = new TextField();
        supplierInput.setId("supplierInput");
        GridPane.setConstraints(supplierInput, 1, 3);

        // Purchased date
        Label purchasedDateLabel = new Label("Purchased Date:");
        GridPane.setConstraints(purchasedDateLabel, 0, 4);

        DatePicker purchasedDateInput = new DatePicker();
        purchasedDateInput.setId("purchasedDateInput");
        GridPane.setConstraints(purchasedDateInput, 1, 4);

        // Purchased price
        Label purchasedPriceLabel = new Label("Purchased Price:");
        GridPane.setConstraints(purchasedPriceLabel, 0, 5);

        TextField purchasedPriceInput = new TextField();
        purchasedPriceInput.setId("purchasedPriceInput");
        GridPane.setConstraints(purchasedPriceInput, 1, 5);

        // Original price
        Label originalPriceLabel = new Label("Original Price:");
        GridPane.setConstraints(originalPriceLabel, 0, 6);
        TextField originalPriceInput = new TextField();
        originalPriceInput.setId("originalPriceInput");
        GridPane.setConstraints(originalPriceInput, 1, 6);

        // Selling price
        Label sellingPriceLabel = new Label("Selling Price:");
        GridPane.setConstraints(sellingPriceLabel, 0, 7);

        TextField sellingPriceInput = new TextField();
        sellingPriceInput.setId("sellingPriceInput");
        GridPane.setConstraints(sellingPriceInput, 1, 7);

        // Author
        Label authorLabel = new Label("Author:");
        GridPane.setConstraints(authorLabel, 0, 8);

        TextField authorInput = new TextField();
        authorInput.setId("authorInput");
        GridPane.setConstraints(authorInput, 1, 8);

        // Stock
        Label stockLabel = new Label("Stock:");
        GridPane.setConstraints(stockLabel, 0, 9);

        TextField stockInput = new TextField();
        stockInput.setId("stockInput");
        GridPane.setConstraints(stockInput, 1, 9);

        Button submitButton = new Button("Submit");
        final Color startColor = Color.web("#6C6C6CFF");
        final Color endColor = Color.web("#FFFFFF");

        final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.web("#FFFFFF"));

        // String that represents the color above as a JavaFX CSS function:
        // -fx-body-color: rgb(r, g, b);
        // with r, g, b integers between 0 and 255
        final StringBinding cssColorSpec = Bindings.createStringBinding(() -> String.format("-fx-body-color: rgb(%d, %d, %d);",
                (int) (256 * color.get().getRed()),
                (int) (256 * color.get().getGreen()),
                (int) (256 * color.get().getBlue())), color);

        // bind the button's style property
        submitButton.styleProperty().bind(cssColorSpec);
        submitButton.setId("submitButton");
        submitButton.setCursor(Cursor.HAND);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(color, startColor)),
                new KeyFrame(Duration.seconds(1), new KeyValue(color, endColor)));
        GridPane.setConstraints(submitButton, 1, 10);

        submitButton.setOnAction(actionEvent -> {
            // Get the values of all inputs in the form
            String isbn = isbnInput.getText();
            String title = titleInput.getText();
            String purchasedPrice = purchasedPriceInput.getText();
            String sellingPrice = sellingPriceInput.getText();
            String originalPrice = originalPriceInput.getText();
            String author = authorInput.getText();
            String category = categoryInput.getText();
            String supplier = supplierInput.getText();
            String stock = stockInput.getText();
            LocalDate purchasedDate = purchasedDateInput.getValue();

            // Start the timeline animation
            timeline.play();

            // Check if all the inputs are filled

            if (!(isbn.isEmpty() || title.isEmpty() || purchasedPrice.isEmpty() || sellingPrice.isEmpty() ||
                    originalPrice.isEmpty() || author.isEmpty() || category.isEmpty() || supplier.isEmpty() ||
                    stock.isEmpty() || purchasedDate == null)) {
                try {
                    // Add a new book object to the books list using the input values
                    Controller.books.add(new Book(isbn, title, Double.parseDouble(purchasedPrice),
                            Double.parseDouble(originalPrice), Double.parseDouble(sellingPrice), author, category,
                            supplier, Integer.parseInt(stock), purchasedDate));

                    // Update the total cost of books
                    Controller.totalCost += Integer.parseInt(stock) * Double.parseDouble(purchasedPrice);

                    // Clear all the input fields
                    isbnInput.setText("");
                    titleInput.setText("");
                    purchasedPriceInput.setText("");
                    sellingPriceInput.setText("");
                    originalPriceInput.setText("");
                    authorInput.setText("");
                    categoryInput.setText("");
                    supplierInput.setText("");
                    stockInput.setText("");
                    purchasedDateInput.setValue(null);

                    // Show an alert message indicating that the book was added successfully
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successful");
                    alert.setContentText("Book is added successfully!");
                    ButtonType customOkButton = new ButtonType("Book is added!", ButtonType.OK.getButtonData());
                    alert.getButtonTypes().setAll(customOkButton);
                    alert.showAndWait();
                } catch (Exception e) {
                    // Show an error message if the prices or stock fields contain non-numeric characters
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please make sure you don't enter any character in prices and stock fields");
                    alert.showAndWait();
                }
            } else {

                // Show an error message if any of the inputs are not filled
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("There is some error, please confirm all fields are filled!");
                ButtonType customOkButton = new ButtonType("!OK!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();
            }
        });

        grid.getChildren().addAll(isbnLabel, isbnInput, titleLabel, titleInput, categoryLabel, categoryInput, supplierLabel,
                supplierInput, purchasedDateLabel, purchasedDateInput, purchasedPriceLabel, purchasedPriceInput,
                originalPriceLabel, originalPriceInput, sellingPriceLabel, sellingPriceInput, authorLabel, authorInput,
                stockLabel, stockInput, submitButton);
        return grid;
    }

    private GridPane homePage() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label welcomeLabel = new Label("Welcome to Administrative Panel");
        welcomeLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 22));
        grid.add(welcomeLabel, 0, 0);

        ///
        ArrayList<Person> peopleList = new ArrayList<>(Controller.people);
        // Add labels and values for books sold, librarians, managers, and bill
        Label TotalCost = new Label("Total Cost: ");
        TotalCost.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label TotalCostValue = new Label(String.valueOf(Controller.totalCost));
        TotalCostValue.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label librarians = new Label("Librarians: ");
        librarians.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label librariansValue = new Label(writingToFiles.getNumberOfLibrarians(peopleList));
        librariansValue.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label managers = new Label("Managers: ");
        managers.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        ///////
        Label managersValue = new Label(writingToFiles.getNumberOfManagers(peopleList));
        managersValue.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label bill = new Label("Total Income: ");
        bill.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label billValue = new Label(String.valueOf(writingToFiles.getTotalBill("res/totalBill.bin")));
        billValue.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

        // Add the labels to the grid
        grid.add(TotalCost, 0, 4);
        grid.add(TotalCostValue, 1, 4);
        grid.add(librarians, 0, 1);
        grid.add(librariansValue, 1, 1);
        grid.add(managers, 0, 2);
        grid.add(managersValue, 1, 2);
        grid.add(bill, 0, 3);
        grid.add(billValue, 1, 3);
        return grid;
    }
}
