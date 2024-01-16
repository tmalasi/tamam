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

    // Test Case 1: Existing file with Librarian
    @Test
    public void testGetPersons_ExistingFileWithLibrarianTestCase1() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create a Librarian object and write it to the file
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            writer.write(person1 + "\n");
        }
        // Ensure that persons are retrieved from the file successfully
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 2: Existing file with Manager
    @Test
    public void testGetPersons_ExistingFileWithManagerTestCase2() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create a Manager object and write it to the file
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
            writer.write(person2 + "\n");
        }
        // Ensure that persons are retrieved from the file successfully
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 3: Existing file with Administrator
    @Test
    public void testGetPersons_ExistingFileWithAdministratorTestCase3() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create an Administrator object and write it to the file
            Person person3 = new Administrator("SampleName3", "sample3", "12345678", "9202-2-2-", 5454, "89878766", Role.Administrator);
            writer.write(person3 + "\n");
        }
        // Ensure that persons are retrieved from the file successfully
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 4: Non-existing file
    @Test
    public void testGetPersons_NonExistingFileTestCase4() {
        // Ensure that a runtime exception is thrown when attempting to read from a non-existing file
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getPersons(null);
        });
    }

    // Test Case 5: Unknown Role in the file
    @Test
    public void testGetPersons_WithUnknownRoleTestCase5() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create a Person object with an unknown role and write it to the file
            Person person = new Person("TestName", "TestUsername", "TestPassword", null, 233, "+355686767", Role.Test);
            writer.write(person + "\n");
        }
        // Ensure that an empty list is returned since the role is unknown
        assertEquals(0, writingToFiles.getPersons(tempFile.toString()).size());
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 6: Existing file is created correctly but is empty
    @Test
    public void testGetPersons_CheckFileIsCreatedCorrectlyButIsEmptyTestCase6() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        // Create an empty ObservableList
        ObservableList<Person> people = FXCollections.observableArrayList();
        // Ensure that an empty list is returned for an empty file
        assertEquals(people, writingToFiles.getPersons(tempFile.toString()));
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 7: Existing file for PIT (Path Identification Test) with data
    // because PIT said for line if file exist negation survived
    @Test
    public void testGetPersons_ExistingFileTestForPit1() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Add some data to the file
            writer.write("TestName,TestUsername,TestPassword,Librarian,30,+355686767,Role.Librarian,100.0\n");
        }
        // Ensure that persons are retrieved from the file successfully
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test Case 8: Non-existing file for PIT (Path Identification Test)
    @Test
    public void testGetPersons_NonExistingFileTestForPit2() {
        // Intentionally delete the file if it exists to simulate non-existence
        File tempFile = new File("nonExistingFile.txt");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        // Ensure that an empty list is returned for a non-existing file
        assertEquals(0, writingToFiles.getPersons("nonExistingFile.txt").size());
    }
}

