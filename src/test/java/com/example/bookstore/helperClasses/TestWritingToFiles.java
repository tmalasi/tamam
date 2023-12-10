package com.example.bookstore.helperClasses;

import com.example.bookstore.Controllers.Controller;
import com.example.bookstore.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class TestWritingToFiles {
    @Test
    public void testReadCredentials_ValidCredentials_MatchFound() {
        try{
        String username = "admin";
        String password = "admin";

        String role = writingToFiles.readCredentials(username, password);
        assertEquals("Administrator", role); }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testReadCredentials_NonValidUsername() {
        try{
        String username = "adminn";
        String password = "admin";

        String role = writingToFiles.readCredentials(username, password);
        assertNull(role);}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testReadCredentials_NonValidPassword() {
        try{
        String username = "admin";
        String password = "1234556";

        String role = writingToFiles.readCredentials(username, password);
        assertNull(role);}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadCredentials_NullCredentials() {
        try {
            String role = writingToFiles.readCredentials(null, null);
            assertNull(role);
        }   catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testWriteRoles_CheckFileIsCreatedCorrectlly(){
        Path tempFile = createTempFilePath();
        writingToFiles.writeRoles(tempFile.toString());
        assertFileExistsAndNotEmpty(tempFile);
        deleteFile(tempFile);

    }
    @Test
    public void testWriteRoles_CheckContentOfFile(){
        Path tempFile = createTempFilePath();
        writingToFiles.writeRoles(tempFile.toString());
        assertFileExistsAndNotEmpty(tempFile);
        assertFileContent(tempFile, "admin,admin,Administrator");
        deleteFile(tempFile);
    }

    @Test
    public void testGetBooks_ObservableListNotNull(){
        ObservableList<Book> books = writingToFiles.getBooks();
        assertNotNull(books);
    }

    @Test
    public void testGetBooks_ContainsTheCorrectNumberOfBooks(){
        ObservableList<Book> books = writingToFiles.getBooks();
        assertEquals(7, books.size());
    }

    @Test
    public void testGetBooks_AttributesOfTheBookCorrectlyPopulated(){
        ObservableList<Book> books = writingToFiles.getBooks();
        Book firstBook = books.get(0);
        assertEquals("Pride and Prejudice", firstBook.getTitle());
        assertEquals("Jane Austin", firstBook.getAuthor());
        assertEquals("Romance",firstBook.getCategory());
        assertEquals("9781428108325",firstBook.getIsbn());
        assertEquals(12,firstBook.getStock());
        assertEquals(15.99,firstBook.getSellPrice());
        assertEquals(9.99,firstBook.getOriginalPrice());
        assertEquals(10.99,firstBook.getPurchasePrice());
    }

    @Test
    public void testGetPersons_ObservableListNonNull(){
        ObservableList<Person> people=writingToFiles.getPersons();
        assertNotNull(people);
    }

    @Test
    public void testGetPersons_ContainsTheCorrectNumberOfPersons(){
        ObservableList<Person> people=writingToFiles.getPersons();
        assertEquals(6,people.size());
    }

    @Test
    public void testGetPersons_AttributesOfPersonAreCorrectllyPopulated(){
        ObservableList<Person> people=writingToFiles.getPersons();
        Person teaPerson =people.get(4);
        assertTrue(teaPerson instanceof Administrator);
        assertEquals("malasi",teaPerson.getUserName());
        assertEquals("tea",teaPerson.getName());
        assertEquals(454454454,teaPerson.getSalary());

    }

    @Test
    public void testGetNumberOfLibrarians_inWrittingToFiles(){
        String numberOfLibrarians = writingToFiles.getNumberOfLibrarians();
        assertEquals("3",numberOfLibrarians);
    }


    @Test
    public void testGetNumberOfManagers_inWrittingToFiles(){
        String numberOfManagers = writingToFiles.getNumberOfManagers();
        assertEquals("2", numberOfManagers);
    }

    @Test
    public void testGetNumberOfBills_InTheExistingDirectory() {
        int numberOfBills = writingToFiles.getNumberOfBills("res/Bills");
        assertEquals(25, numberOfBills);
    }
    @Test
    public void testGetNumberOfBills_InTheNONExistingDirectory() {
        int numberOfBills = writingToFiles.getNumberOfBills("path/to/non-existing-directory");
        assertEquals(0, numberOfBills);
    }

    @Test
    public void testWriteBill(){
        ObservableList<Book> books = writingToFiles.getBooks();
        writingToFiles.writeBill(String.valueOf(1000), 1234, books);
        String expectedFilePath = "res/Bills/" + 1000 + ".txt";
        assertTrue(new File(expectedFilePath).exists());
        deleteFile(expectedFilePath);
    }

    @Test
    public void testGetTotalBill_forNONExistingPath(){
        double totalBill=writingToFiles.getTotalBill("path/to/non-existing-bill");
        assertEquals(0.0,totalBill);
    }

    @Test
    public void testGetTotalCost_forNONExistingPath(){
        double totalCost=writingToFiles.getTotalCost("path/to/non-existing-bill");
        assertEquals(0.0,totalCost);
    }

    @Test
    public void testGetBooksSold_forNONExistingPath(){
        double booksSold =writingToFiles.getTotalCost("path/to/non-existing-bill");
        assertEquals(0, booksSold);
    }

    //fix this
//    @Test
//    public void testWriteBooks_ContainsTheRightInformation(){
//        Book sampleBook = new Book("SampleTitle", "SampleAuthor", 20.0, 15.0, 5.0, "SampleGenre", "SamplePublisher", "SampleLanguage", 2022, LocalDate.of(2022, 1, 1));
//        Controller.books.add(sampleBook);
//        writingToFiles.writeBooks();
//        String fileContent = readFileContents("res/books.txt");
//        assertTrue(fileContent.contains(sampleBook.toString()), "File should contain the sample book information");
//    }
//
//    //fix this
//    @Test
//    public void testWritePersons_ContaionsRightInformation(){
//        Person samplePerson = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
//        Controller.people.add(samplePerson);
//        writingToFiles.writePersons();
//        String fileContent = readFileContents("res/persons.txt");
//        assertTrue(fileContent.contains(samplePerson.toString()), "File should contain the sample person information");
//    }

    @Test
    public void testWriteTotalBill_OfExistingFile() {
        double sampleTotal = 1165.32;
        writingToFiles.writeTotalBill(sampleTotal, "res/totalBill.bin");
        double actualTotal = readTotal("res/totalBill.bin");
        assertEquals(sampleTotal, actualTotal);
    }
    @Test
    void testWriteTotalCost_OfExistingFile() {
        double sampleTotalCost = 756.78;
        writingToFiles.writeTotalCost(sampleTotalCost);
        double actualTotalCost = readTotal("res/totalCost.bin");
        assertEquals(sampleTotalCost, actualTotalCost, "File should contain the sample total cost");
    }

    private double readTotal(String filepath) {
            File file = new File(filepath);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file);
                     DataInputStream dis = new DataInputStream(fis)) {
                    return dis.readDouble();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0.0; // Return 0 if the file does not exist
        }



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

    private void deleteFile(Path tempFile) {
        try {
            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertFileContent(Path tempFile, String expectedContent) {
        try {
            String fileContent = Files.readString(tempFile);
            assertTrue(fileContent.contains(expectedContent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path createTempFilePath() {
        try {
            return Files.createTempFile("tempRoles", ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
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