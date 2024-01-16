package com.example.bookstore.helperClasses;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestingWritingToFilesIntegration {

    // Integration testing with the default file operations for getTotalCost
    @Test
    void shouldCalculateTotalCostWithValidTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalC", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(1165.32);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Assert that the file exists
        assertTrue(tempFile.exists());

        // Call the calculateTotalCost method with the path of the temporary file
        double totalCost = WriteBill.getTotalCost(tempFile.getPath());

        // Assert that the totalCost matches the expected value
        assertEquals(1165.32, totalCost, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case for calculateTotalCost with non-existing temp file
    @Test
    void shouldReturnZeroTotalCostForNonExistingTempFile() {
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateTotalCost method with a non-existing file path
        double totalCost = WriteBill.getTotalCost("\"nonExistingFile.bin\"");

        // Assert that the totalCost is 0.0 for a non-existing file
        assertEquals(0.0, totalCost);
    }

    // Test case for calculateTotalCost with an existing but empty temp file
    @Test
    void shouldReturnZeroTotalCostForExistingEmptyTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalC", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Assert that the file exists
        assertTrue(tempFile.exists());

        // Call the calculateTotalCost method with the path of the temporary file
        double totalCost = WriteBill.getTotalCost(tempFile.getPath());

        // Assert that the totalCost is 0.0 for an empty file
        assertEquals(0.0, totalCost, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Integration testing with the default file operations for getTotalBill
    @Test
    void shouldCalculateTotalBillWithExistingTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(1165.32);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateTotalBill method with the path of the temporary file
        double totalBill = WriteBill.getTotalBill(tempFile.getPath());

        // Assert that the totalBill matches the expected value
        assertEquals(1165.32, totalBill, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case for calculateTotalBill with non-existing temp file
    @Test
    void shouldReturnZeroTotalBillForNonExistingTempFile() {
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateTotalBill method with a non-existing file path
        double totalBill = WriteBill.getTotalBill("nonExistingFile.bin");

        // Assert that the totalBill is 0.0 for a non-existing file
        assertEquals(0.0, totalBill, 0.001);
    }

    // Test case for calculateTotalBill with an existing but empty temp file
    @Test
    void shouldReturnZeroTotalBillForExistingEmptyTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateTotalBill method with the path of the temporary file
        double totalBill = WriteBill.getTotalBill(tempFile.getPath());

        // Assert that the totalBill is 0.0 for an empty file
        assertEquals(0.0, totalBill, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Integration testing with the default file operations for getBooksSold
    @Test
    void shouldCalculateBooksSoldWithExistingTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempBooksSold", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(11);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateBooksSold method with the path of the temporary file
        double totalBooksSold = WriteBill.getBooksSold(tempFile.getPath());

        // Assert that the totalBooksSold matches the expected value
        assertEquals(11.00, totalBooksSold, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case for calculateBooksSold with non-existing temp file
    @Test
    void shouldReturnZeroBooksSoldForNonExistingTempFile() {
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateBooksSold method with a non-existing file path
        double totalBooksSold = WriteBill.getBooksSold("nonExistingFile.bin");

        // Assert that the totalBooksSold is 0.0 for a non-existing file
        assertEquals(0.0, totalBooksSold, 0.001);
    }

    // Test case for calculateBooksSold with an existing but empty temp file
    @Test
    void shouldReturnZeroBooksSoldForExistingEmptyTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);

        // Call the calculateBooksSold method with the path of the temporary file
        double totalBooksSold = WriteBill.getBooksSold(tempFile.getPath());

        // Assert that the totalBooksSold is 0.0 for an empty file
        assertEquals(0.0, totalBooksSold, 0.001);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Integration Testing for writeTotalBill
    @Test
    void shouldWriteTotalBillSuccessfullyWithTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".txt");
        double total = 123.4;
        FileOutputInterface fileOutput = new DefaultFileOutput();
        writingToFiles.writeTotalBill(total, tempFile.toString(), fileOutput);

        // Read the content of the temporary file
        double fileContent;
        try (FileInputStream fis = new FileInputStream(tempFile);
             DataInputStream dis = new DataInputStream(fis)) {
            fileContent = dis.readDouble();
        }

        // Assert that the file content matches the expected total
        assertEquals(total, fileContent);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case for writeTotalBill throwing an exception with a non-writable temp file
    @Test
    void shouldThrowExceptionOnWriteTotalBillWithNonWritableTempFile() {

        FileOutputInterface fileOutput = new DefaultFileOutput();
        // Specify a path that is not writable to simulate an IOException
        String nonWritableFilePath = "/path/to/nonwritablefile.txt";

        // Use assertThrows to verify that an IOException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                writingToFiles.writeTotalBill(42.0, nonWritableFilePath, fileOutput));
    }

    // Integration Test for writeTotalCost
    @Test
    void shouldWriteTotalCostSuccessfullyWithTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalC", ".txt");
        double total = 123.4;
        FileOutputInterface fileOutput = new DefaultFileOutput();
        writingToFiles.writeTotalCost(total, tempFile.toString(), fileOutput);

        // Read the content of the temporary file
        double fileContent;
        try (FileInputStream fis = new FileInputStream(tempFile);
             DataInputStream dis = new DataInputStream(fis)) {
            fileContent = dis.readDouble();
        }

        // Assert that the file content matches the expected total
        assertEquals(total, fileContent);

        // Delete the temporary file
        tempFile.deleteOnExit();
    }

    // Test case for writeTotalCost throwing an exception with a non-writable temp file
    @Test
    void shouldThrowExceptionOnWriteTotalCostWithNonWritableTempFile() {

        FileOutputInterface fileOutput = new DefaultFileOutput();
        // Specify a path that is not writable to simulate an IOException
        String nonWritableFilePath = "/path/to/nonwritablefile.txt";

        // Use assertThrows to verify that an IOException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                writingToFiles.writeTotalCost(42.0, nonWritableFilePath, fileOutput));
    }
}
