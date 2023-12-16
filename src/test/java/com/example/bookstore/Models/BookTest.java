package com.example.bookstore.Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    public void testSetCategoryWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book();
            book.setCategory(null);
        });
    }

    @Test
    public void testSetCategoryWhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setCategory(book.getCategory());
        assertEquals("Comedy", book.getCategory());
    }

    @Test
    public void testSetCategoryWhenCloseTOMaxLength() {
        Book book = new Book();
        book.setCategory("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getCategory());
    }

    @Test
    public void testSetCategoryWhenMoreThanMaxLength() {
        Book book = new Book("123", "Invalid Book", 19.99, 29.99, "abcdefghijklmnopqrstuvwxyzt", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setCategory(book.getCategory());
        });
    }

    @Test
    void testSetISBN_WithNullISBN() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book(null, "Invalid Book", 19.99, 29.99, "Fiction", 50);
            book.setIsbn(book.getIsbn());
        });
    }

    @Test
    public void testSetISBN_WhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setIsbn(book.getIsbn());
        assertEquals("123", book.getIsbn());
    }

    @Test
    public void testSetISBN_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setIsbn("abcdefghijklmno");
        assertEquals("abcdefghijklmno", book.getIsbn());
    }

    @Test
    public void testSetISBN_WhenMoreThanMaxLength() {
        Book book = new Book("abcdefghijklmnopq", "Invalid Book", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setIsbn(book.getIsbn());
        });
    }

    @Test
    void testSetTitle_WithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", null, 19.99, 29.99, "Fiction", 50);
            book.setTitle(book.getTitle());
        });
    }

    @Test
    public void testSetTitle_WhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setTitle(book.getTitle());
        assertEquals("SampleTitle", book.getTitle());
    }

    @Test
    public void testSetTitle_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setTitle("The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs.");
        assertEquals("The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs.", book.getTitle());
    }

    @Test
    public void testSetTitle_WhenMoreThanMaxLength() {
        Book book = new Book("123", "The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs. " +
                "How razorback-jumping frogs can level six piqued gymnasts!:)", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle(book.getTitle());
        });
    }

    @Test
    public void testSetTitle_WhenCloseToMinLength() {
        Book book = new Book();
        book.setTitle("Its");
        assertEquals("Its", book.getTitle());
    }

    @Test
    public void testSetTitle_WhenLessThanMinLength() {
        Book book = new Book("123", "IT", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle(book.getTitle());
        });
    }

    @Test
    void testSetPurchasePrice_WithNegativePurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", "Invalid Book", -1, 29.99, "Fiction", 50);
            book.setPurchasePrice(book.getPurchasePrice());
        });
    }
    @Test
    void testSetPurchasePrice_WithMoreThanMaxPurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", "Invalid Book", 5001, 29.99, "Fiction", 50);
            book.setPurchasePrice(book.getPurchasePrice());
        });
    }

    @Test
    public void testSetPurchasePrice_WhenPurchasePriceZero () {
        Book book = new Book("123", "SampleTitle", 0, 34.4, "Comedy");
        book.setPurchasePrice(book.getPurchasePrice());
        assertEquals(0, book.getPurchasePrice());
    }
    @Test
    public void testSetPurchasePrice_WhenPurchasePriceMaxValue () {
        Book book = new Book("123", "SampleTitle", 5000, 34.4, "Comedy");
        book.setPurchasePrice(book.getPurchasePrice());
        assertEquals(5000, book.getPurchasePrice());
    }

    @Test
    void testSetOriginalPrice_WithNegativeOriginalPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,-3,45.5,
                    "SampleAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setOriginalPrice(book.getOriginalPrice());
        });
    }

    @Test
    public void testSetOriginalPrice_WhenOriginalPriceZero () {
        Book book = new Book("123","SampleTitle",45.5,0,45.5,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setOriginalPrice(book.getOriginalPrice());
        assertEquals(0, book.getOriginalPrice());
    }
    @Test
    public void testSetOriginalPrice_WhenPurchasePriceNormalValue () {
        Book book = new Book();
        book.setOriginalPrice(345);
        assertEquals(345, book.getOriginalPrice());
    }

    @Test
    void testSetSellPrice_WithSellPriceSmallerThanPurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,-3,35.5,
                    "SampleAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setSellPrice(book.getSellPrice());
        });
    }

    @Test
    public void testSetSellPrice_EqualWithPurchasePrice() {
        Book book = new Book("123","SampleTitle",45.5,0,45.5,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSellPrice(book.getSellPrice());
        assertEquals(45.5, book.getSellPrice());
    }
    @Test
    public void testSetSellPrice_BiggerThanPurchasePriceNormalValue () {
        Book book = new Book("123","SampleTitle",45.5,0,55.5,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSellPrice(book.getSellPrice());
        assertEquals(55.5, book.getSellPrice());
    }

    @Test
    void testSetAuthor_WithNullSetAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,0,55.5,
                    null,"Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setAuthor(book.getAuthor());
        });
    }

    @Test
    public void testSetAuthor_WhenNormalLength() {
        Book book = new Book("123","SampleTitle",45.5,0,55.5,
                "TestAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setAuthor(book.getAuthor());
        assertEquals("TestAuthor", book.getAuthor());
    }

    @Test
    public void testSetAuthor_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setAuthor("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getAuthor());
    }

    @Test
    public void testSetAuthor_WhenMoreThanMaxLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setAuthor("abcdefghijklmnopqrstuvwxyzs");
        });
    }

    @Test
    void testSetSupplier_WithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,0,55.5,
                    "TestAuthor","Comedy",null,43, LocalDate.of(2023,1,1));
            book.setSupplier(book.getSupplier());
        });
    }

    @Test
    public void testSetSupplier_WhenNormalLength() {
        Book book = new Book("123","SampleTitle",45.5,0,55.5,
                "TestAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSupplier(book.getSupplier());
        assertEquals("SampleSupplier", book.getSupplier());
    }

    @Test
    public void testSetSupplier_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setSupplier("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getSupplier());
    }

    @Test
    public void testSetSupplier_WhenMoreThanMaxLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setSupplier("abcdefghijklmnopqrstuvwxyzs");
        });
    }

    @Test
    void testSetStock_WithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,87,55.5,
                    "TestAuthor","Comedy",null,-12, LocalDate.of(2023,1,1));
            book.setStock(book.getStock());
        });
    }

    @Test
    public void testSetStock_WhenTheValueOfItIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",0, LocalDate.of(2023,1,1));
        book.setStock(book.getStock());
        });
    }
    @Test
    public void testSetStock_WhenNormalValueBiggerThan0() {
        Book book = new Book();
        book.setStock(4);
        assertEquals(4, book.getStock());
    }
    @Test
    void testSetPurchaseDate_WithNullPurchaseDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,67,55.5,
                    "TestAuthor","Comedy","SampleSupplier",0, null);
            book.setPurchaseDate(book.getPurchaseDate());
        });
    }

    @Test
    public void testSetPurchaseDate_WhenNotMoreThanOneYearFromCurrentDate() {
        Book book = new Book();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearLater = currentDate.plusYears(1).plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setPurchaseDate(oneYearLater);
        });
    }
    @Test
    public void testSetPurchaseDate_WhenExcactlyOneYearFromCurrentDate() {
        Book book = new Book();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearLater = currentDate.plusYears(1);
        book.setPurchaseDate(oneYearLater);
        assertEquals(oneYearLater,book.getPurchaseDate());
    }

    @Test
    public void testSetPurchaseDate_WhenDateisTheLocalDateOfNow() {
        Book book = new Book();
        book.setPurchaseDate(LocalDate.now());
        assertEquals(LocalDate.now(), book.getPurchaseDate());
    }
    @Test
    void testCanAddBook_WithStockZero() {
            Book book = new Book("123","SampleTitle",45.5,67,55.5,
                    "TestAuthor","Comedy","SampleSupplier",0, LocalDate.of(2023,1,1));
            assertFalse(Book.canAddBook(book,2));
    }
    @Test
    void testCanAddBook_WithEnteredQuantityZero() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertFalse(Book.canAddBook(book,0));
    }
    @Test
    void testCanAddBook_WithEnteredQuantityMoreThanStock() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertFalse(Book.canAddBook(book,3));
    }
    @Test
    void testCanAddBook_WithEnteredQuantityEqualWithTheStock() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertTrue(Book.canAddBook(book,2));
    }


    @Test
    void testToStringMethod() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertEquals("123,SampleTitle,45.5,67.0,55.5,TestAuthor,Comedy,SampleSupplier,2,2023-01-01",book.toString());
    }
    @Test
    void testToStringBillMethod() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertEquals("ISBN: 123 ,Title: SampleTitle ,Author: TestAuthor",book.toStringBill());
    }



}