package com.example.bookstore.helperClasses;
import com.example.bookstore.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class TestWritingToFiles {
    @Test
    public void testReadCredentials_CheckFileIsCreatedCorrectlly() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }
        writingToFiles.readCredentials(null,null,tempFile.toString());
        assertFileExistsAndNotEmpty(Path.of(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_ValidCredentials_MatchFound() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }
        // Read data from the temporary file and find the role
        String username = "SampleAddress";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());
        assertEquals("Librarian", role);
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_NonValidUsername() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }
        // Read data from the temporary file and find the role
        String username = "SampleAddresss";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());
        assertNull(role);
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_NonValidPassword() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }
        // Read data from the temporary file and find the role
        String username = "SampleAddress";
        String password = "SamplePhoneee";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());
        assertNull(role);
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_EmptyFile() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        // Read data from the temporary file and find the role
        String username = "SampleAddress";
        String password = "SamplePhone";
        String role = writingToFiles.readCredentials(username, password, tempFile.toString());
        assertNull(role);
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_NullCredentials() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1.getUserName() + "," + person1.getPassword() + "," + person1.getRole().toString());
            writer.newLine();
            writer.write(person2.getUserName() + "," + person2.getPassword() + "," + person2.getRole().toString());
        }
        String role = writingToFiles.readCredentials(null, null, tempFile.toString());
        assertNull(role);
        tempFile.deleteOnExit();
    }
    @Test
    public void testReadCredentials_ExceptionThrown() {
        String role = writingToFiles.readCredentials(null, null, null);
        assertNull(role);
    }
    @Test
    public void testWriteRoles_CheckFileIsCreatedCorrectlly() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        ArrayList<Person> personArrayList= new ArrayList<>();
        writingToFiles.writeRoles(tempFile.toString(),personArrayList);
        assertFileExistsAndNotEmpty(Path.of(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testWriteRoles_CheckContentOfFileWhenYouDontWriteAnything() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        ArrayList<Person> personArrayList= new ArrayList<>();
        writingToFiles.writeRoles(tempFile.toString(),personArrayList);
        String fileContent= readFileContents(tempFile.toString());
        assertEquals("admin,admin,Administrator\n", fileContent);
        tempFile.deleteOnExit();
    }
    @Test
    public void testWriteRoles_CheckContentOfFile() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
        ArrayList<Person> personArrayList= new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        writingToFiles.writeRoles(tempFile.toString(),personArrayList);
        String fileContent= readFileContents(tempFile.toString());
        assertEquals("admin,admin,Administrator\n" +
                "SampleAddress,SamplePhone,Librarian\n" +
                "Author 2,2024,Manager\n", fileContent);
        tempFile.deleteOnExit();
    }
    @Test
    public void testWriteRoles_ThrowException() {
        ArrayList<Person> personArrayList= new ArrayList<>();
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.writeRoles(null, personArrayList);
        });
    }
    @Test
    public void testGetBooks_CheckFileIsCreatedCorrectllyButIsEmpty() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        ObservableList<Book> books = FXCollections.observableArrayList();
        assertEquals(books, writingToFiles.getBooks(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetBooks_ObservableListNotNull() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1=new Book("12345","NewBook1", 34.4,21,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            Book book2=new Book("12346","NewBook2", 24.4,30.66,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            writer.write(book1 + "\n");
            writer.write(book2+"\n");
            writer.close();
        }
        assertNotNull(writingToFiles.getBooks(tempFile.toString()).size());
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetBooks_ContainsTheCorrectNumberOfBooks() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1=new Book("12345","NewBook1", 34.4,21,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            Book book2=new Book("12346","NewBook2", 24.4,30.66,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            writer.write(book1 + "\n");
            writer.write(book2+"\n");
            writer.close();
        }
        Assertions.assertEquals(2,writingToFiles.getBooks(tempFile.toString()).size());
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetBooks_AttributesOfTheBookCorrectlyPopulated() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Book book1=new Book("12345","NewBook1", 34.4,21,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            Book book2=new Book("12346","NewBook2", 24.4,30.66,45.3,"newAuthor","Comedy","newSupplier",34, LocalDate.now());
            writer.write(book1 + "\n");
            writer.write(book2+"\n");
            writer.close();
        }
        ObservableList<Book> books = writingToFiles.getBooks(tempFile.toString());
        Book firstBook = books.getFirst();
        assertEquals("NewBook1",firstBook.getTitle());
        assertEquals("newAuthor", firstBook.getAuthor());
        assertEquals("Comedy",firstBook.getCategory());
        assertEquals("12345",firstBook.getIsbn());
        assertEquals(34,firstBook.getStock());
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetBooks_ThrowException() {
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getBooks(null);
        });
    }
    @Test
    public void testGetPersons_CheckFileIsCreatedCorrectllyButIsEmpty() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        ObservableList<Person> people = FXCollections.observableArrayList();
        assertEquals(people, writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ObservableListNotNull() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1 + "\n");
            writer.write(person2+"\n");
            writer.close();
        }
        assertNotNull(writingToFiles.getPersons(tempFile.toString()));
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ContainsTheCorrectNumberOfPersons() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1 + "\n");
            writer.write(person2+"\n");
            writer.close();
        }
        Assertions.assertEquals(2,writingToFiles.getPersons(tempFile.toString()).size());
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_AttributesOfTheBookCorrectlyPopulated() throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
            Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);
            writer.write(person1 + "\n");
            writer.write(person2+"\n");
            writer.close();
        }
        ObservableList<Person> people = writingToFiles.getPersons(tempFile.toString());
        Person firstPerson= people.getFirst();
        assertEquals("SampleName",firstPerson.getName());
        assertEquals("SampleAddress", firstPerson.getUserName());
        assertEquals("SamplePhone",firstPerson.getPassword());
        assertEquals(30,firstPerson.getSalary());
        assertEquals(Role.Librarian,firstPerson.getRole());
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetPersons_ThrowException() {
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getPersons(null);
        });

    }

    @Test
    public void testGetNumberOfLibrarians_inWrittingToFilesButIsEmptyList(){
        ArrayList<Person> people = new ArrayList<>();
        assertEquals("0", writingToFiles.getNumberOfLibrarians(people));
    }

    @Test
    public void testGetNumberOfLibrarians_inWrittingToFiles(){
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);

        people.add(person1);
        people.add(person2);

        assertEquals("1", writingToFiles.getNumberOfLibrarians(people));
    }


    @Test
    public void testGetNumberOfManagers_inWrittingToFilesButIsEmptyList(){
        ArrayList<Person> people = new ArrayList<>();
        assertEquals("0", writingToFiles.getNumberOfManagers(people));
    }
    @Test
    public void testGetNumberOfManagers_inWrittingToFiles(){
        ArrayList<Person> people = new ArrayList<>();
        Person person1 = new Librarian("SampleName", "SampleAddress", "SamplePhone", "SampleEmail", 30, "SampleUsername", Role.Librarian, 100.0);
        Person person2 = new Manager("Book 2", "Author 2", "2024", "9202-2-2-", 2323, "sllwlwls", Role.Manager);

        people.add(person1);
        people.add(person2);

        assertEquals("1", writingToFiles.getNumberOfManagers(people));
    }
    @Test
    public void testGetNumberOfBills_WhenWePassAFileNotADirectroy_ThrowsException () throws IOException {
        File tempFile = File.createTempFile( "prefix", "txt");
        assertThrows(RuntimeException.class, () -> {
            writingToFiles.getNumberOfBills(tempFile.toString());
        });
        tempFile.deleteOnExit();
    }
    @Test
    public void testGetNumberOfBills_InTheNONExistingDirectory() {
        int numberOfBills = writingToFiles.getNumberOfBills("path/to/non-existing-directory");
        assertEquals(0, numberOfBills);
    }

    @Test
    public void testGetNumberOfBills_InTheExistingDirectoryIfBiggerThen0ThenCorrectNumberOfFiles(){
        int numberOfBills=writingToFiles.getNumberOfBills("res/Bills");
        boolean isBiggerThen0= (numberOfBills>0);
        assertTrue(isBiggerThen0);
    }

    @Test
    public void testWriteBill(){
        ObservableList<Book> books = writingToFiles.getBooks("res/books.txt");
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
        writingToFiles.writeTotalCost(sampleTotalCost, "res/totalCost.bin");
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
    private void deleteFile(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Handle or log the exception
            e.printStackTrace();
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
    private Path createTempFilePath() throws IOException {
        return Files.createTempFile("tempFile", ".txt");
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