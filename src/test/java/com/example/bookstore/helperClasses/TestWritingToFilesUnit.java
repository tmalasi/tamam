package com.example.bookstore.helperClasses;

import com.example.bookstore.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class TestWritingToFilesUnit {
    // Test case: Check if the temporary file is created correctly
    // Test case: Check if the temporary file is created correctly
    @Test
    public void testReadCredentials_CheckTempFileIsCreatedCorrectly() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample credentials to the temporary file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create sample librarian and manager credentials
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

            // Write credentials to the file
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }

        // Call the method to read credentials and check if the temporary file is created and not empty
        writingToFiles.readCredentials(null, null, tempFile.toString());
        assertFileExistsAndNotEmpty(Path.of(tempFile.toString()));

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if valid credentials in the temporary file can be matched
    @Test
    public void testReadCredentials_CheckValidCredentialsOnTempFile_MatchFound() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample credentials to the temporary file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create sample librarian and manager credentials
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

            // Write credentials to the file
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }

        // Read data from the temporary file and find the role
        String username = "SampleAddress";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());

        // Check if the role matches the expected value
        assertEquals("Librarian", role);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an invalid username in the temporary file results in null
    @Test
    public void testReadCredentials_NonValidUsernameInTemporaryFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample credentials to the temporary file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create sample librarian and manager credentials
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

            // Write credentials to the file
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }

        // Read data from the temporary file and find the role with a non-valid username
        String username = "SampleAddresss";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());

        // Check if the role is null
        assertNull(role);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an invalid password in the temporary file results in null
    @Test
    public void testReadCredentials_NonValidPasswordInTemporaryFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample credentials to the temporary file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create sample librarian and manager credentials
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

            // Write credentials to the file
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }

        // Read data from the temporary file and find the role with a non-valid password
        String username = "SampleAddress";
        String password = "SamplePhoneee";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());

        // Check if the role is null
        assertNull(role);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if reading from an empty file results in null
    @Test
    public void testReadCredentials_EmptyFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Read data from the temporary file and find the role
        String username = "SampleAddress";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());

        // Check if the role is null
        assertNull(role);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if reading from a file with null credentials results in null
    @Test
    public void testReadCredentials_NullCredentials() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample credentials to the temporary file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            // Create sample librarian and manager credentials
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

            // Write credentials to the file
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }

        // Read data from the temporary file with null credentials
        String role = writingToFiles.readCredentials(null, null, tempFile.toString());

        // Check if the role is null
        assertNull(role);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an exception is thrown when reading with a null file path
    @Test
    public void testReadCredentials_ExceptionThrown() {
        // Call the method to read credentials with a null file path
        String role = writingToFiles.readCredentials(null, null, null);

        // Check if the role is null
        assertNull(role);
    }

    // Test case: Check if the roles file is created correctly
    @Test
    public void testWriteRoles_CheckFileIsCreatedCorrectly() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Create an empty list of persons
        ArrayList<Person> personArrayList = new ArrayList<>();

        // Write roles to the file
        writingToFiles.writeRoles(tempFile.toString(), personArrayList);

        // Check if the file is created and not empty
        assertFileExistsAndNotEmpty(Path.of(tempFile.toString()));

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check the content of the file when writing nothing
    @Test
    public void testWriteRoles_CheckContentOfFileWhenYouDontWriteAnything() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Create an empty list of persons
        ArrayList<Person> personArrayList = new ArrayList<>();

        // Write roles to the file
        writingToFiles.writeRoles(tempFile.toString(), personArrayList);

        // Read the content of the file
        String fileContent = readFileContents(tempFile.toString());

        // Check if the content matches the expected default admin role
        assertEquals("admin,admin,Administrator\n", fileContent);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check the content of the file when writing specific roles
    @Test
    public void testWriteRoles_CheckContentOfFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Create sample librarian and manager credentials
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

        // Create a list of persons
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);

        // Write roles to the file
        writingToFiles.writeRoles(tempFile.toString(), personArrayList);

        // Read the content of the file
        String fileContent = readFileContents(tempFile.toString());

        // Check if the content matches the expected roles
        assertEquals("""
                admin,admin,Administrator
                SampleAddress,SamplePhone,Librarian
                Author 2,2024,Manager
                """, fileContent);

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an exception is thrown when writing roles with a null file path
    @Test
    public void testWriteRoles_ThrowException() {
        // Check if writing roles with a null file path throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.writeRoles(null, null);
        });
    }
//FROM KEVIN
    // Test case: Check if the file is created correctly but contains no books
    @Test
    public void testGetBooks_CheckFileIsCreatedCorrectlyButIsEmpty() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Create an empty observable list of books
        ObservableList<Book> books = FXCollections.observableArrayList();

        // Check if the list of books matches the expected empty list
        assertEquals(books, writingToFiles.getBooks(tempFile.toString()));

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the observable list of books is not null
    @Test
    public void testGetBooks_ObservableListNotNull() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample book entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            writer.write(book1 + "\n");
            writer.write(book2 + "\n");
        }

        // Check if the observable list of books is not null
        assertNotNull(writingToFiles.getBooks(tempFile.toString()).size());

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the observable list contains the correct number of books
    @Test
    public void testGetBooks_ContainsTheCorrectNumberOfBooks() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample book entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            writer.write(book1 + "\n");
            writer.write(book2 + "\n");
        }

        // Check if the observable list contains the correct number of books
        assertEquals(2, writingToFiles.getBooks(tempFile.toString()).size());

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the attributes of the book are correctly populated
    @Test
    public void testGetBooks_AttributesOfTheBookCorrectlyPopulated() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample book entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
            writer.write(book1 + "\n");
            writer.write(book2 + "\n");
        }

        // Get the observable list of books from the file
        ObservableList<Book> books = writingToFiles.getBooks(tempFile.toString());

        // Check if the attributes of the first book are correctly populated
        Book firstBook = books.getFirst();
        assertEquals("NewBook1", firstBook.getTitle());
        assertEquals("newAuthor", firstBook.getAuthor());
        assertEquals("Comedy", firstBook.getCategory());
        assertEquals("12345", firstBook.getIsbn());
        assertEquals(34, firstBook.getStock());

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an exception is thrown when getting books with a null file path
    @Test
    public void testGetBooks_ThrowException() {
        // Check if getting books with a null file path throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getBooks(null);
        });
    }

    // Test case: Check if the persons file is created correctly but contains no persons
    @Test
    public void testGetPersons_CheckFileIsCreatedCorrectlyButIsEmpty() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Create an empty observable list of persons
        ObservableList<Person> people = FXCollections.observableArrayList();

        // Check if the list of persons matches the expected empty list
        assertEquals(people, writingToFiles.getPersons(tempFile.toString()));

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the observable list of persons is not null
    @Test
    public void testGetPersons_ObservableListNotNull() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample person entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
            Person person3 = new Administrator("SampleName3", "sample3", "12345678", "9202-2-2-", 5454, "89878766", Role.Administrator);
            writer.write(person1 + "\n");
            writer.write(person2 + "\n");
            writer.write(person3 + "\n");
        }

        // Check if the observable list of persons is not null
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the observable list contains the correct number of persons
    @Test
    public void testGetPersons_ContainsTheCorrectNumberOfPersons() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample person entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
            Person person3 = new Administrator("SampleName3", "sample3", "12345678", "9202-2-2-", 5454, "89878766", Role.Administrator);
            writer.write(person1 + "\n");
            writer.write(person2 + "\n");
            writer.write(person3 + "\n");
        }

        // Check if the observable list contains the correct number of persons
        assertEquals(3, writingToFiles.getPersons(tempFile.toString()).size());

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if the attributes of the person are correctly populated
    @Test
    public void testGetPersons_AttributesOfTheBookCorrectlyPopulated() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Write sample person entries to the file
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
            Person person3 = new Administrator("SampleName3", "sample3", "12345678", "9202-2-2-", 5454, "89878766", Role.Administrator);
            writer.write(person1 + "\n");
            writer.write(person2 + "\n");
            writer.write(person3 + "\n");
        }

        // Get the observable list of persons from the file
        ObservableList<Person> people = writingToFiles.getPersons(tempFile.toString());

        // Check if the attributes of the first person are correctly populated
        Person firstPerson = people.getFirst();
        assertEquals("SampleName", firstPerson.getName());
        assertEquals("SampleAddress", firstPerson.getUserName());
        assertEquals("SamplePhone", firstPerson.getPassword());
        assertEquals(30, firstPerson.getSalary());
        assertEquals(Role.Librarian, firstPerson.getRole());

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Check if an exception is thrown when getting persons with a null file path
    @Test
    public void testGetPersons_ThrowException() {
        // Check if getting persons with a null file path throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getPersons(null);
        });
    }

    // Test case: Get the number of librarians when the list in WritingToFiles is empty
    @Test
    public void testGetNumberOfLibrarians_inWritingToFilesButIsEmptyList() {
        // Create an empty list of persons
        ArrayList<Person> people = new ArrayList<>();

        // Check if the number of librarians is 0
        assertEquals("0", writingToFiles.getNumberOfLibrarians(people));
    }

    // Test case: Get the number of librarians when there are no librarians in the list
    @Test
    public void testGetNumberOfLibrarians_inWritingToFilesButThereAreNoLibrarians() {
        // Create a list of persons with a manager but no librarians
        ArrayList<Person> people = new ArrayList<>();
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
        people.add(person2);

        // Check if the number of librarians is 0
        assertEquals("0", writingToFiles.getNumberOfLibrarians(people));
    }

    // Test case: Get the number of librarians when there is at least one librarian in the list
    @Test
    public void testGetNumberOfLibrarians_inWritingToFiles() {
        // Create a list of persons with one librarian and one manager
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

        people.add(person1);
        people.add(person2);

        // Check if the number of librarians is 1
        assertEquals("1", writingToFiles.getNumberOfLibrarians(people));
    }

    // Test case: Get the number of managers when the list in WritingToFiles is empty
    @Test
    public void testGetNumberOfManagers_inWritingToFilesButIsEmptyList() {
        // Create an empty list of persons
        ArrayList<Person> people = new ArrayList<>();

        // Check if the number of managers is 0
        assertEquals("0", writingToFiles.getNumberOfManagers(people));
    }

    // Test case: Get the number of managers when there are no managers in the list
    @Test
    public void testGetNumberOfManagers_inWritingToFilesButThereAreNoManagers() {
        // Create a list of persons with a librarian but no managers
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        people.add(person1);

        // Check if the number of managers is 0
        assertEquals("0", writingToFiles.getNumberOfManagers(people));
    }

    // Test case: Get the number of managers when there is at least one manager in the list
    @Test
    public void testGetNumberOfManagers_inWritingToFiles() {
        // Create a list of persons with one librarian and one manager
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);

        people.add(person1);
        people.add(person2);

        // Check if the number of managers is 1
        assertEquals("1", writingToFiles.getNumberOfManagers(people));
    }

    // Test case: Throws exception when attempting to get the number of bills from a file that is not a directory
    @Test
    public void testGetNumberOfBills_WhenWePassAFileNotADirectory_ThrowsException() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");

        // Check if getting the number of bills from a non-directory file throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getNumberOfBills(tempFile.toString());
        });

        // Clean up: Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case: Get the number of bills in a non-existing directory
    @Test
    public void testGetNumberOfBills_InTheNONExistingDirectory() {
        // Get the number of bills in a non-existing directory
        int numberOfBills = writingToFiles.getNumberOfBills("path/to/non-existing-directory");

        // Check if the number of bills is 0
        assertEquals(0, numberOfBills);
    }

    // Test case: Get the number of bills in an existing directory; if greater than 0, correct number of files
    @Test
    public void testGetNumberOfBills_InTheExistingDirectoryIfBiggerThen0ThenCorrectNumberOfFiles() {
        // Get the number of bills in an existing directory
        int numberOfBills = writingToFiles.getNumberOfBills("res/Bills");

        // Check if the number of bills is greater than 0
        boolean isBiggerThen0 = (numberOfBills > 0);
        assertTrue(isBiggerThen0);
    }
    //TILL HERE
    // Test case: Check if the bill file is created correctly
    @Test
    public void testWriteBills_CheckFileIsCreatedCorrectly() {
        // Get the list of books from the books file
        ObservableList<Book> books = writingToFiles.getBooks("res/books.txt");

        // Expected file path for the bill
        String expectedFilePath = "res/Bills/" + 1000 + ".txt";

        // Ensure the file does not exist before calling writeBill
        assertFalse(new File(expectedFilePath).exists());

        // Write a bill and check if the file is created
        writingToFiles.writeBill(String.valueOf(1000), 1234, books, expectedFilePath);

        // Ensure the file is now created
        assertTrue(new File(expectedFilePath).exists());

        // Clean up: Delete the created file
        deleteFile(expectedFilePath);
    }

    // Test case: Check if the content of the bill file is created correctly
    @Test
    public void testWriteBills_CheckFileContentIsCreatedCorrectly() {
        // Create sample books
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));

        // Create a list of books
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        // Expected file path for the bill
        String expFile = "res/Bills/" + "123i9" + ".txt";

        // Ensure the file does not exist before calling writeBill
        assertFalse(new File(expFile).exists());

        // Write a bill and check if the file is created
        writingToFiles.writeBill("123i9", 345, books, expFile);

        // Ensure the file is now created
        assertTrue(new File(expFile).exists());

        // Read the content of the file
        String fileContent = readFileContents(expFile);

        // Check if the content matches the expected format
        assertEquals("Bill Id: 123i9\n" +
                "Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "1: ISBN: 12345 ,Title: NewBook1 ,Author: newAuthor\n" +
                "2: ISBN: 12346 ,Title: NewBook2 ,Author: newAuthor\n" +
                "Total: 345.0\n", fileContent);

        // Clean up: Delete the created file
        deleteFile(expFile);
    }

    // Test case: Throws exception when attempting to write a bill with null parameters
    @Test
    public void testWriteBills_ThrowException() {
        // Check if writing a bill with null parameters throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.writeBill("123i8", 100000, null, null);
        });
    }


    // Unit testing for GetTotalBill

    // Test case for an existing file with a mock file that returns a specific value
    @Test
    void testGetTotalBillWithMockExistingFile() {
        // Create a mock file operations object with a predefined result
        FileOperations fileOperations = new MockFileOperations(true, 123.4);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call getTotalBill with the file path and check if the result matches the expected value
        double totalBill = WriteBill.getTotalBill("existingFile.bin");
        assertEquals(123.4, totalBill, 0.001);
    }

    // Test case for a non-existing file with a mock file that returns a specific value
    @Test
    void testGetTotalBillWithMockNonExistingFile() {
        // Create a mock file operations object with a predefined result for a non-existing file
        FileOperations fileOperations = new MockFileOperations(false, 0.0);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call getTotalBill with the file path and check if the result matches the expected value
        double totalBill = WriteBill.getTotalBill("nonExistingFile.bin");
        assertEquals(0.0, totalBill, 0.001);
    }

    // Test case for an IOException scenario with a mock file operations object
    @Test
    void testGetTotalBillWithMockIOException() {
        // Create a mock file operations object with an IOException scenario
        FileOperations fileOperations = new MockFileOperations(true, 0.0) {
            @Override
            public double readDoubleFromFile(String filepath) throws IOException {
                // Simulate an IOException in the readDoubleFromFile method
                throw new IOException("Mock IOException");
            }
        };
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call getTotalBill with the file path and check if the result matches the expected value
        double totalBill = WriteBill.getTotalBill("existingFile.bin");
        assertEquals(0.0, totalBill, 0.001);
    }

// Unit Testing for get total cost

    // Test case for a valid file with a mock file that returns a specific value
    @Test
    void testGetTotalCostWithValidFile() {
        // Create a mock file operations object with a predefined result
        FileOperations fileOperations = new MockFileOperations(true, 123.4);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteCost = new writingToFiles(fileOperations);

        // Call getTotalCost with the file path and check if the result matches the expected value
        double totalCost = WriteCost.getTotalCost("existing file ");
        assertEquals(123.4, totalCost, 0.001);
    }

    // Test case for a non-existing file with a mock file that returns a specific value
    @Test
    void testGetTotalCostWithNonExistingFile() {
        // Create a mock file operations object with a predefined result for a non-existing file
        FileOperations fileOperations = new MockFileOperations(false, 0.0);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteCost = new writingToFiles(fileOperations);

        // Call getTotalCost with the file path and check if the result matches the expected value
        double totalCost = WriteCost.getTotalCost("nonExistingFile.bin");
        assertEquals(0.0, totalCost, 0.001);
    }

    // Test case for an IOException scenario with a mock file operations object
    @Test
    void testGetTotalCostWithIOException() {
        // Create a mock file operations object with an IOException scenario
        FileOperations fileOperations = new MockFileOperations(true, 0.0) {
            @Override
            public double readDoubleFromFile(String filepath) throws IOException {
                // Simulate an IOException in the readDoubleFromFile method
                throw new IOException("Mock IOException");
            }
        };
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteCost = new writingToFiles(fileOperations);

        // Call getTotalCost with the file path and check if the result matches the expected value
        double totalCost = WriteCost.getTotalCost("existingFile.bin");
        assertEquals(0.0, totalCost, 0.001);
    }

    // Test case for getting the number of books sold from an existing file with a mock file that returns a specific value
    @Test
    void testGetBooksSoldWithExistingFileMocking() {
        // Create a mock file operations object with a predefined result
        FileOperations fileOperations = new MockFileOperations(true, 123);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBooksSold = new writingToFiles(fileOperations);
        // Call getBooksSold with the file path and check if the result matches the expected value
        int booksSold = WriteBooksSold.getBooksSold("res/booksSold.bin");
        assertEquals(123, booksSold);
    }

    // Test case for getting the number of books sold from a non-existing file with a mock file that returns a specific value
    @Test
    void testGetBooksSoldWithNonExistingFileMocking() {
        // Create a mock file operations object with a predefined result for a non-existing file
        FileOperations fileOperations = new MockFileOperations(false, 0.0);
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBooksSold = new writingToFiles(fileOperations);
        // Call getBooksSold with the file path and check if the result matches the expected value
        int booksSold = WriteBooksSold.getBooksSold("nonExistingFile.bin");
        assertEquals(0, booksSold);
    }

    // Test case for an IOException scenario with a mock file operations object
    @Test
    void testGetBooksSoldWithIOExceptionMocking() {
        // Create a mock file operations object with an IOException scenario
        FileOperations fileOperations = new MockFileOperations(true, 0.0) {
            @Override
            public double readDoubleFromFile(String filepath) throws IOException {
                // Simulate an IOException in the readDoubleFromFile method
                throw new IOException("Mock IOException");
            }
        };
        // Create an instance of writingToFiles with the mock file operations
        writingToFiles WriteBooksSold = new writingToFiles(fileOperations);
        // Call getBooksSold with the file path and check if the result matches the expected value
        int booksSold = WriteBooksSold.getBooksSold("existingFile.bin");
        assertEquals(0, booksSold);
    }
//BY KEVIN
    // Test case for writing books and checking if the file is created correctly
    @Test
    public void testWriteBooks_CheckFileIsCreatedCorrectly() throws IOException {
        // Create two book objects
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
        // Create an ArrayList of books
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        // Call writeBooks to write books to the file
        writingToFiles.writeBooks(tempFile.toString(), books);
        // Check if the file exists
        assertTrue(new File(tempFile.toString()).exists());
        tempFile.deleteOnExit();
    }

    // Test case for writing books and checking if the file content is created correctly
    @Test
    public void testWriteBooks_CheckFileContentIsCreatedCorrectly() throws IOException {
        // Create two book objects
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023, 1, 1));
        // Create an ArrayList of books
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        // Call writeBooks to write books to the file
        writingToFiles.writeBooks(tempFile.toString(), books);
        // Read the contents of the file
        String fileContent = readFileContents(tempFile.toString());
        // Check if the file content matches the expected value
        assertEquals("""
                12345,NewBook1,34.4,21.0,45.3,newAuthor,Comedy,newSupplier,34,2023-01-01
                12346,NewBook2,24.4,30.66,45.3,newAuthor,Comedy,newSupplier,34,2023-01-01
                """, fileContent);
        tempFile.deleteOnExit();
    }

    // Test case for checking if an exception is thrown when writing books with null parameters
    @Test
    public void testWriteBooks_ThrowException() {
        // Check if writing books with null parameters throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.writeBooks(null, null);
        });
    }

    // Test case for writing persons and checking if the file is created
    @Test
    public void testWritePersons_CheckFileIsCreated() throws IOException {
        // Create an ArrayList of Person objects
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        people.add(person1);
        people.add(person2);
        // Call writePersons to write persons to the file
        writingToFiles.writePersons(tempFile.toString(), people);
        // Check if the file exists
        assertTrue(new File(tempFile.toString()).exists());
        tempFile.deleteOnExit();
    }

    // Test case for writing persons and checking if the file content is created correctly
    @Test
    public void testWritePersons_CheckFileContentIsCreatedCorrectly() throws IOException {
        // Create an ArrayList of Person objects
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "+3556767", Role.Manager);
        // Create a temporary file
        File tempFile = File.createTempFile("prefix", "txt");
        people.add(person1);
        people.add(person2);
        // Call writePersons to write persons to the file
        writingToFiles.writePersons(tempFile.toString(), people);
        // Read the contents of the file
        String fileContent = readFileContents(tempFile.toString());
        // Check if the file content matches the expected value
        assertEquals("""
                SampleName,SampleEmail,SampleUsername,30,SampleAddress,SamplePhone,Librarian,100.0
                Book 2,9202-2-2-,+3556767,2323,Author 2,2024,Manager
                """, fileContent);
        tempFile.deleteOnExit();
    }

    // Test case for checking if an exception is thrown when writing persons with null parameters
    @Test
    public void testWritePersons_ThrowException() {
        // Check if writing persons with null parameters throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.writePersons(null, null);
        });
    }
    //TILL HERE
// Unit Testing for writing Total Bill

    // Test case for writing total bill successfully with a mock implementation of FileOutputInterface
    @Test
    void testWriteTotalBillSuccessMock() {
        // Set up data
        double total = 123.4;
        String filePath = "testFile.bin";
        // Create a mock implementation of FileOutputInterface
        FileOutputInterface fileOutput = new MockFileOutput(false);
        // Check that writing total bill does not throw an exception
        assertDoesNotThrow(() -> writingToFiles.writeTotalBill(total, filePath, fileOutput));
    }

    // Test case for writing total bill with an exception thrown by the mock implementation of FileOutputInterface
    @Test
    void testWriteTotalBillWithExceptionMock() {
        // Set up data
        double total = 123.4;
        String filePath = "testFile.bin";
        // Create a mock implementation of FileOutputInterface that throws an exception
        FileOutputInterface fileOutput = new MockFileOutput(true);
        // Check that writing total bill throws the expected exception
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> writingToFiles.writeTotalBill(total, filePath, fileOutput));
        assertEquals("Mock write error", exception.getMessage());
    }
// Unit Testing for writing Total Cost

    // Test case for writing total cost successfully with a mock implementation of FileOutputInterface
    @Test
    void testWriteTotalCostSuccessMock() {
        // Set up data
        double total = 123.4;
        String filePath = "testFile.bin";
        // Create a mock implementation of FileOutputInterface
        FileOutputInterface fileOutput = new MockFileOutput(false);
        // Check that writing total cost does not throw an exception
        assertDoesNotThrow(() -> writingToFiles.writeTotalCost(total, filePath, fileOutput));
    }

    // Test case for writing total cost with an exception thrown by the mock implementation of FileOutputInterface
    @Test
    void testWriteTotalCostWithExceptionMock() {
        // Set up data
        double total = 123.4;
        String filePath = "testFile.bin";
        // Create a mock implementation of FileOutputInterface that throws an exception
        FileOutputInterface fileOutput = new MockFileOutput(true);
        // Check that writing total cost throws the expected exception
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> writingToFiles.writeTotalCost(total, filePath, fileOutput));
        assertEquals("Mock write error", exception.getMessage());
    }

    // Helper method to read the contents of a file
    private String readFileContents(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return content.toString();
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.delete()) {
            System.err.println("Failed to delete the temporary file: " + filePath);
        }
    }

    private void assertFileExistsAndNotEmpty(Path filePath) {
        assertTrue(Files.exists(filePath), "File should exist");
        assertTrue(Files.isRegularFile(filePath), "Should be a regular file");
        try {
            assertTrue(Files.size(filePath) > 0, "File should not be empty");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
