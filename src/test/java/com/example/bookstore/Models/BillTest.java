package com.example.bookstore.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBooksValidValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, "TEST",3,9,"dd-MM-yyyy");
        bill.setBooks(books);
        assertEquals(books, bill.getBooks());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBooksNull() {

        Bill bill = new Bill(null,"TEST",5,8,"dd-MM-yyyy");
        assertThrows(IllegalArgumentException.class, () -> bill.setBooks(bill.getBooks()));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBillIdValidValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,"bill123", 4,39,"dd-MM-yyyy");
        bill.setBillId(bill.getBillId());
        assertEquals("bill123", bill.getBillId());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBillIdNullValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,null, 4,39,"dd-MM-yyyy");
        assertThrows(IllegalArgumentException.class, () -> bill.setBillId(null));

    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @ParameterizedTest
    @CsvSource({
            "A,4,39.0,\"dd-MM-yyyy\"",
            "123456789012345,4,39.0,\"dd-MM-yyyy\"",
    })
    public void testSetBillIdMinAndMaxValue(String billid, int quantity, double totalAmount, String dateOfTransaction) {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, billid,  quantity, totalAmount, dateOfTransaction);
        assertEquals(billid, bill.getBillId());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @ParameterizedTest
    @CsvSource({
            ",4,39.0,\"dd-MM-yyyy\"",
            "12345678901234567,4,39.0,\"dd-MM-yyyy\"",
    })
    public void testSetBillIdLessThanMinAndMoreThanMax(String billid, int quantity, double totalAmount, String dateOfTransaction) {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, billid,  quantity, totalAmount, dateOfTransaction);
        assertThrows(IllegalArgumentException.class, () -> bill.setBillId(billid));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetQuantityValidValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,"bill123", 4,39,"dd-MM-yyyy");
        bill.setQuantity(bill.getQuantity());
        assertEquals(4, bill.getQuantity());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @ParameterizedTest
    @CsvSource({
            "TEST,1,39.0,\"dd-MM-yyyy\"",
            "TEST,10,39.0,\"dd-MM-yyyy\"",
    })
    public void testSetQuantityMinAndMaxValue(String billid, int quantity, double totalAmount, String dateOfTransaction) {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, billid,  quantity, totalAmount, dateOfTransaction);
        assertEquals(quantity, bill.getQuantity());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @ParameterizedTest
    @CsvSource({
            "TEST,0,39.0,\"dd-MM-yyyy\"",
            "TEST,11,39.0,\"dd-MM-yyyy\"",
    })
    public void testSetQuantityLessThanMinAndMoreThanMax(String billid, int quantity, double totalAmount, String dateOfTransaction) {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, billid,  quantity, totalAmount, dateOfTransaction);
        assertThrows(IllegalArgumentException.class, () -> bill.setQuantity(quantity));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTotalAmountValidValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,"bill123", 4,23.3,"dd-MM-yyyy");
        assertEquals(23.3, bill.getTotalAmount());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTotalAmountMinValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, "TEST",3,0,"dd-MM-yyyy");
        bill.setTotalAmount(bill.getTotalAmount());
        assertEquals(0, bill.getTotalAmount());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTotalAmountLessThanMin() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,"TEST",0,-1,"dd-MM-yyyy");
        assertThrows(IllegalArgumentException.class, () -> bill.setTotalAmount(bill.getTotalAmount()));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetDateOfTransactionValidValue() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books, "TEST",3,9,"dd-MM-yyyy");
        bill.setDateOfTransaction(bill.getDateOfTransaction());
        assertEquals("dd-MM-yyyy", bill.getDateOfTransaction());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetDateOfTransactionNull() {
        Book book1 = new Book("12345", "NewBook1", 34.4, 21, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        Book book2 = new Book("12346", "NewBook2", 24.4, 30.66, 45.3, "newAuthor", "Comedy", "newSupplier", 34, LocalDate.of(2023,1,1));
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Bill bill = new Bill(books,"TEST",5,8,null);
        assertThrows(IllegalArgumentException.class, () -> bill.setDateOfTransaction(bill.getDateOfTransaction()));
    }
}
