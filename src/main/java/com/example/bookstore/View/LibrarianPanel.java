package com.example.bookstore.View;

import javafx.scene.layout.BorderPane;
import com.example.bookstore.Controllers.Controller;
import com.example.bookstore.Models.Person;

public class LibrarianPanel extends BorderPane {

    private Person person;

    public LibrarianPanel() {

        BillView billView = new BillView(Controller.books);
        billView.setUser(person);
        setCenter(billView);

    }


    public void setPerson(Person person) {
        this.person = person;
    }
}
