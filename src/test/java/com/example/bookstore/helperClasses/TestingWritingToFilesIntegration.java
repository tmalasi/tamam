package com.example.bookstore.helperClasses;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestingWritingToFilesIntegration {
    //Integration testing with the default file operations for get total bill
    @Test
    void testGetTotalBillWithExistingTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(1165.32);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBill = WriteBill.getTotalBill(tempFile.getPath());
        assertEquals(1165.32, totalBill, 0.001);
        tempFile.deleteOnExit();
    }

    @Test
    void testGetTotalBillWithNONExistingTempFile()  {
        // Create a temporary file
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBill = WriteBill.getTotalBill("null");
        assertEquals(0.0, totalBill, 0.001);

    }

    @Test
    void testGetTotalBillWithExistingTempFileButEmpty() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBill = WriteBill.getTotalBill(tempFile.getPath());
        assertEquals(0.0, totalBill, 0.001);
        tempFile.deleteOnExit();

    }

    //Integration testing with the default file operations for get total cost
    @Test
    void testGetTotalCostWithValidTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalC", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(1165.32);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalCost = WriteBill.getTotalCost(tempFile.getPath());
        assertEquals(1165.32, totalCost, 0.001);
        tempFile.deleteOnExit();
    }

    @Test
    void testGetTotalCostWithNONExistingTempFile() {
        // Create a temporary file
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        double totalCost = WriteBill.getTotalCost("\"existingFile.bin\"");
        assertEquals(0.0, totalCost);

    }

    @Test
    void testGetTotalCostWithExistingTempFileButEmpty() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalC", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalCost = WriteBill.getTotalCost(tempFile.getPath());
        assertEquals(0.0, totalCost, 0.001);
        tempFile.deleteOnExit();

    }
    //Integration testing with the default file operations for get total bill
    @Test
    void testGetBooksSoldWithExistingTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempBooksSold", ".bin");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(tempFile))) {
            dos.writeDouble(11);
        }

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBooksSold = WriteBill.getBooksSold(tempFile.getPath());
        assertEquals(11.00, totalBooksSold, 0.001);
        tempFile.deleteOnExit();
    }

    @Test
    void testGetTotalBooksSoldWithNONExistingTempFile() {
        // Create a temporary file
        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBooksSold = WriteBill.getBooksSold("null");
        assertEquals(0.0, totalBooksSold, 0.001);

    }

    @Test
    void testGetTotalBooksWithExistingTempFileButEmpty() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempTotalB", ".bin");

        // Use the DefaultFileOperations and writingToFiles instances
        FileOperations fileOperations = new DefaultFileOperations();
        writingToFiles WriteBill = new writingToFiles(fileOperations);
        // Call the getTotalBill method with the path of the temporary file
        double totalBooksSold = WriteBill.getBooksSold(tempFile.getPath());
        assertEquals(0.0, totalBooksSold, 0.001);
        tempFile.deleteOnExit();

    }

    //Integration Testing for write total bill
    @Test
    void testWriteTotalBillSuccessWithTempFile() throws IOException {
        File tempFile = File.createTempFile("tempTotalB", ".txt");
        double total = 123.4;
        FileOutputInterface fileOutput = new DefaultFileOutput();
        writingToFiles.writeTotalBill(total, tempFile.toString(), fileOutput);
        double fileContent;
        try (FileInputStream fis = new FileInputStream(tempFile);
             DataInputStream dis = new DataInputStream(fis)) {
            fileContent = dis.readDouble();
        }
        assertEquals(total, fileContent);
        tempFile.deleteOnExit();
    }

    @Test
    void testWriteTotalBillThrowExceptionWithTempFile() {

        FileOutputInterface fileOutput = new DefaultFileOutput();
        // Specify a path that is not writable to simulate an IOException
        String nonWritableFilePath = "/path/to/nonwritablefile.txt";

        // Use assertThrows to verify that an IOException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                writingToFiles.writeTotalBill(42.0, nonWritableFilePath, fileOutput));
    }

    //Integration Test for Write TotalCost
    @Test
    void testWriteTotalCostSuccessWithTempFile() throws IOException {
        File tempFile = File.createTempFile("tempTotalC", ".txt");
        double total = 123.4;
        FileOutputInterface fileOutput = new DefaultFileOutput();
        writingToFiles.writeTotalCost(total, tempFile.toString(), fileOutput);
        double fileContent;
        try (FileInputStream fis = new FileInputStream(tempFile);
             DataInputStream dis = new DataInputStream(fis)) {
            fileContent = dis.readDouble();
        }
        assertEquals(total, fileContent);
        tempFile.deleteOnExit();
    }

    @Test
    void testWriteTotalCostThrowExceptionWithTempFile() {

        FileOutputInterface fileOutput = new DefaultFileOutput();
        // Specify a path that is not writable to simulate an IOException
        String nonWritableFilePath = "/path/to/nonwritablefile.txt";

        // Use assertThrows to verify that an IOException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                writingToFiles.writeTotalCost(42.0, nonWritableFilePath, fileOutput));
    }
}
