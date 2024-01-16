package com.example.bookstore.View;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.bookstore.Models.Person;
import com.example.bookstore.helperClasses.Role;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;


public class PersonsView extends VBox {

    //TODO TEST FOR EDIT
    private TableView<Person> tableView;

    public PersonsView(ObservableList<Person> people) {

        tableView = new TableView<>();
        tableView.setId("tablePersons");

        TableColumn<Person, String> ISBNColumn = new TableColumn<>("Name");
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, String> titleColumn = new TableColumn<>("Username");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<Person, Role> purchasePriceColumn = new TableColumn<>("Role");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Person, Integer> salesPriceColumn = new TableColumn<>("Salary");
        salesPriceColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Person, Void> actionColumn = new TableColumn<>("Actions");

        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");

                    String absolutePathDelete = "C:\\Users\\User\\IdeaProjects\\bookstore\\res\\delete.png";
                    Image imageDelete = new Image("file:" + absolutePathDelete);
                    ImageView deleteIcon = new ImageView(imageDelete);

                    String absolutePathEdit = "C:\\Users\\User\\IdeaProjects\\bookstore\\res\\edit.png";
                    Image imageEdit = new Image("file:" + absolutePathEdit);
                    ImageView editIcon = new ImageView(imageEdit);


                    {

                        editIcon.setFitHeight(15);
                        editIcon.setFitWidth(15);
                        deleteIcon.setFitWidth(15);
                        deleteIcon.setFitHeight(15);
                        deleteButton.setGraphic(deleteIcon);
                        deleteButton.setId("deleteButton");
                        editButton.setGraphic(editIcon);
                        editButton.setId("editButton");
                        editButton.setStyle("-fx-background-color: transparent;");
                        deleteButton.setStyle("-fx-background-color: transparent;");
                        editButton.setOnAction((ActionEvent event) -> {
                            Person person = getTableView().getItems().get(getIndex());
                            showEditPersonWindow(person);
                            tableView.refresh();
                        });
                        deleteButton.setOnAction((ActionEvent event) -> {
                            // logic to handle delete action
                            Person person = getTableView().getItems().get(getIndex());
                            people.remove(person);
                            tableView.refresh();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonsBox = new HBox(editButton, deleteButton);
                            setGraphic(buttonsBox);
                        }
                    }
                };
            }
        };

        actionColumn.setCellFactory(cellFactory);

        List<TableColumn<Person, ?>> columnsArray = new ArrayList<>();

        columnsArray.add(ISBNColumn);
        columnsArray.add(titleColumn);
        columnsArray.add(purchasePriceColumn);
        columnsArray.add(salesPriceColumn);
        columnsArray.add(actionColumn);

// Add all columns to the tableView
        tableView.getColumns().addAll(columnsArray);

        tableView.setItems(people);

        Label lblHeading = new Label("List of Employees");
        lblHeading.setStyle("-fx-font-size: 20px;");
        getChildren().addAll(lblHeading, tableView);
        setSpacing(10);
        setPadding(new Insets(10));
    }
    private void showEditPersonWindow(Person person) {
        // create a new window to show the form to edit the person
        Stage stage = new Stage();
        stage.setTitle("Edit Person");
        // create the form to edit the person
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        // create text fields for the Person properties
        TextField nameField = new TextField();
        nameField.setId("editName");
        nameField.setPromptText("Name");
        nameField.setText(person.getName());

        TextField userNameField = new TextField();
        userNameField.setPromptText("Username");
        userNameField.setText(person.getUserName());

        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setId("passwordField");
        userNameField.setText(person.getPassword());

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");
        phoneNumberField.setText(person.getPhone());

        TextField salaryField = new TextField();
        salaryField.setPromptText("Salary");
        salaryField.setText(String.valueOf(person.getSalary()));

        ComboBox<Role> roleComboBox = new ComboBox<>();
        roleComboBox.setId("roleComboBoxEdit");
        roleComboBox.getItems().addAll(Role.Administrator, Role.Manager, Role.Librarian);
        roleComboBox.setPromptText("Select Role");

        // add the text fields to the grid
        grid.add(new Label("Name"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Username"), 0, 1);
        grid.add(userNameField, 1, 1);
        grid.add(new Label("Password"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(new Label("Phone Number"), 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(new Label("Salary"), 0, 4);
        grid.add(salaryField, 1, 4);
        grid.add(new Label("Role"), 0, 5);
        grid.add(roleComboBox, 1, 5);

        // create a save button to save the changes
        Button saveButton = new Button("Save");
        saveButton.setId("saveButton");
        saveButton.setOnAction((ActionEvent event) -> {
            // validate the form
            if (nameField.getText().isEmpty() || userNameField.getText().isEmpty() || salaryField.getText().isEmpty()
                    || phoneNumberField.getText().isEmpty() || passwordField.getText().isEmpty() || roleComboBox.getSelectionModel().isEmpty()) {
                // show an error if any of the fields is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fill the fields");
                ButtonType customOkButton = new ButtonType("Error!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();            }
            else {
                // update the person with the new values
                person.setName(nameField.getText());
                person.setUserName(userNameField.getText());
                person.setPassword(passwordField.getText());
                person.setSalary(Integer.parseInt(salaryField.getText()));
                person.setPhone(phoneNumberField.getText());
                person.setRole(roleComboBox.getSelectionModel().getSelectedItem());
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful!");
                ButtonType customOkButton = new ButtonType("Person is edited!", ButtonType.OK.getButtonData());
                alert.getButtonTypes().setAll(customOkButton);
                alert.showAndWait();
            }
        });
        grid.add(saveButton, 1, 10);
        grid.setAlignment(Pos.CENTER);
        // create a scene and show the window
        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
