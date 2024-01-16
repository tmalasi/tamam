package com.example.bookstore.helperClasses;

import com.example.bookstore.Models.Administrator;
import com.example.bookstore.Models.Librarian;
import com.example.bookstore.Models.Manager;
import com.example.bookstore.Models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


public class BasisPathTestingForGetPersons {

    @Test
    public void testGetPersons_ExistingFileWithLibrarianTestCase1() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
             writer.write(person1 + "\n");
        }
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ExistingFileWithManagerTestCase2() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
            writer.write(person2 + "\n");
        }
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ExistingFileWithAdministratorTestCase3() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person3 = new Administrator("SampleName3", "sample3", "12345678", "9202-2-2-", 5454, "89878766", Role.Administrator);
            writer.write(person3 + "\n");
        }
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }

    @Test
    public void testGetPersons_NonExistingFileTestCase4() {
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getPersons(null);
        });

    }
    @Test
    public void testGetPersons_WithUnknownRoleTestCase5() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person = new Person("TestName", "TestUsername", "TestPassword", null, 233, "+355686767", Role.Test);
           writer.write(person + "\n");
            }
        assertEquals(0, writingToFiles.getPersons(tempFile.toString()).size());
        tempFile.deleteOnExit();
    }

    @Test
    public void testGetPersons_CheckFileIsCreatedCorrectlyButIsEmptyTestCase6() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        ObservableList<Person> people = FXCollections.observableArrayList();
        assertEquals(people, writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ExistingFileTestForPit1() throws IOException {
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Add some data to the file
            writer.write("TestName,TestUsername,TestPassword,Librarian,30,+355686767,Role.Librarian,100.0");
        }
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }

    @Test
    public void testGetPersons_NonExistingFileTestForPit2() {
        // Intentionally delete the file if it exists to simulate non-existence
        File tempFile = new File("nonExistingFile.txt");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        assertEquals(0, writingToFiles.getPersons("nonExistingFile.txt").size());
    }


}
