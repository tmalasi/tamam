package com.example.bookstore.Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    //This is a method for EquivalenceTesting
    @Test
    public void testSetCategoryWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book();
            book.setCategory(null);
        });
    }
    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetCategoryWhenItIsSmallerThanMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book();
            book.setCategory("");
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetCategoryWhenItsMinimumValidLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "A");
        book.setCategory(book.getCategory());
        assertEquals("A", book.getCategory());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetCategoryWhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setCategory(book.getCategory());
        assertEquals("Comedy", book.getCategory());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetCategoryWhenCloseTOMaxLength() {
        Book book = new Book();
        book.setCategory("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getCategory());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetCategoryWhenMoreThanMaxLength() {
        Book book = new Book("123", "Invalid Book", 19.99, 29.99, "abcdefghijklmnopqrstuvwxyzt", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setCategory(book.getCategory());
        });
    }

    //This is a method for EquivalenceTesting
    @Test
    void testSetISBN_WithNullISBN() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book(null, "Invalid Book", 19.99, 29.99, "Fiction", 50);
            book.setIsbn(book.getIsbn());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetISBNWhenItIsSmallerThanMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book();
            book.setIsbn("");
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetISBNWhenItsMinimumValidLength() {
        Book book = new Book("1", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setIsbn(book.getIsbn());
        assertEquals("1", book.getIsbn());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetISBN_WhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setIsbn(book.getIsbn());
        assertEquals("123", book.getIsbn());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetISBN_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setIsbn("abcdefghijklmno");
        assertEquals("abcdefghijklmno", book.getIsbn());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetISBN_WhenMoreThanMaxLength() {
        Book book = new Book("abcdefghijklmnopq", "Invalid Book", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setIsbn(book.getIsbn());
        });
    }

    //Method for EquivalenceTesting
    @Test
    void testSetTitle_WithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", null, 19.99, 29.99, "Fiction", 50);
            book.setTitle(book.getTitle());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTitle_WhenLessThanMinLength() {
        Book book = new Book("123", "IT", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle(book.getTitle());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTitle_WhenCloseToMinLength() {
        Book book = new Book();
        book.setTitle("Its");
        assertEquals("Its", book.getTitle());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTitle_WhenNormalLength() {
        Book book = new Book("123", "SampleTitle", 23.3, 34.4, "Comedy");
        book.setTitle(book.getTitle());
        assertEquals("SampleTitle", book.getTitle());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTitle_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setTitle("The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs.");
        assertEquals("The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs.", book.getTitle());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTitle_WhenMoreThanMaxLength() {
        Book book = new Book("123", "The quick brown fox jumps over the lazy dog. Pack my box with five dozen liquor jugs. " +
                "How razorback-jumping frogs can level six piqued gymnasts!:)", 19.99, 29.99, "Comedy", 50);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle(book.getTitle());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testSetPurchasePrice_WithNegativePurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", "Invalid Book", -1, 29.99, "Fiction", 50);
            book.setPurchasePrice(book.getPurchasePrice());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchasePrice_WhenPurchasePriceZero () {
        Book book = new Book("123", "SampleTitle", 0, 34.4, "Comedy");
        book.setPurchasePrice(book.getPurchasePrice());
        assertEquals(0, book.getPurchasePrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchasePrice_WhenPurchasePriceValidValue () {
        Book book = new Book("123", "SampleTitle", 1000, 34.4, "Comedy");
        book.setPurchasePrice(book.getPurchasePrice());
        assertEquals(1000, book.getPurchasePrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchasePrice_WhenPurchasePriceMaxValue () {
        Book book = new Book("123", "SampleTitle", 5000, 34.4, "Comedy");
        book.setPurchasePrice(book.getPurchasePrice());
        assertEquals(5000, book.getPurchasePrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testSetPurchasePrice_WithMoreThanMaxPurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123", "Invalid Book", 5001, 29.99, "Fiction", 50);
            book.setPurchasePrice(book.getPurchasePrice());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testSetOriginalPrice_WithNegativeOriginalPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,-3,45.5,
                    "SampleAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setOriginalPrice(book.getOriginalPrice());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetOriginalPrice_WhenOriginalPriceZero () {
        Book book = new Book("123","SampleTitle",45.5,0,45.5,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setOriginalPrice(book.getOriginalPrice());
        assertEquals(0, book.getOriginalPrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetOriginalPrice_WhenPurchasePriceValid () {
        Book book = new Book();
        book.setOriginalPrice(345);
        assertEquals(345, book.getOriginalPrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testSetSellPrice_WithSellPriceSmallerThanPurchasePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,-3,45.4,
                    "SampleAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setSellPrice(book.getSellPrice());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSellPrice_EqualWithPurchasePrice() {
        Book book = new Book("123","SampleTitle",45.5,0,45.5,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSellPrice(book.getSellPrice());
        assertEquals(45.5, book.getSellPrice());
    }

    //Method for EquivalenceTesting
    @Test
    public void testSetSellPrice_BiggerThanPurchasePriceNormalValue () {
        Book book = new Book("123","SampleTitle",45.5,0,45.6,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSellPrice(book.getSellPrice());
        assertEquals(45.6, book.getSellPrice());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSellPrice_ValidValueAGeneralCase () {
        Book book = new Book("123","SampleTitle",45.5,15,45.6,
                "comedy","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSellPrice(book.getSellPrice());
        assertEquals(45.6, book.getSellPrice());
    }

    //Method for EquivalenceTesting
    @Test
    void testSetAuthor_WithNullSetAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,0,55.5,
                    null,"Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
            book.setAuthor(book.getAuthor());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetAuthor_LessThanMinLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setAuthor("");
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetAuthor_WhenMinimumValidLength() {
        Book book = new Book();
        book.setAuthor("a");
        assertEquals("a", book.getAuthor());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetAuthor_WhenNormalLength() {
        Book book = new Book("123","SampleTitle",45.5,0,55.5,
                "TestAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setAuthor(book.getAuthor());
        assertEquals("TestAuthor", book.getAuthor());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetAuthor_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setAuthor("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getAuthor());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetAuthor_WhenMoreThanMaxLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setAuthor("abcdefghijklmnopqrstuvwxyzs");
        });
    }

    //Method for EquivalenceTesting
    @Test
    void testSetSupplier_WithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,0,55.5,
                    "TestAuthor","Comedy",null,43, LocalDate.of(2023,1,1));
            book.setSupplier(book.getSupplier());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSupplier_WhenLessThanLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setSupplier("");
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSupplier_WhenMinimumValidLength() {
        Book book = new Book();
        book.setSupplier("a");
        assertEquals("a", book.getSupplier());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSupplier_WhenNormalLength() {
        Book book = new Book("123","SampleTitle",45.5,0,55.5,
                "TestAuthor","Comedy","SampleSupplier",43, LocalDate.of(2023,1,1));
        book.setSupplier(book.getSupplier());
        assertEquals("SampleSupplier", book.getSupplier());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSupplier_WhenCloseToMaxLength() {
        Book book = new Book();
        book.setSupplier("abcdefghijklmnopqrstuvwxy");
        assertEquals("abcdefghijklmnopqrstuvwxy", book.getSupplier());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSupplier_WhenMoreThanMaxLength() {
        Book book = new Book();
        assertThrows(IllegalArgumentException.class, () -> {
            book.setSupplier("abcdefghijklmnopqrstuvwxyzs");
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testSetStock_WithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,87,55.5,
                    "TestAuthor","Comedy",null,-1, LocalDate.of(2023,1,1));
            book.setStock(book.getStock());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetStock_WhenTheValueOfItIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",0, LocalDate.of(2023,1,1));
        book.setStock(book.getStock());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetStock_WhenNormalValueBiggerThan0_UpperBound() {
        Book book = new Book();
        book.setStock(1);
        assertEquals(1, book.getStock());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetStock_WhenValidValueInRange() {
        Book book = new Book();
        book.setStock(10);
        assertEquals(10, book.getStock());
    }

    //Method for EquivalenceTesting
    @Test
    void testSetPurchaseDate_WithNullPurchaseDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("123","SampleTitle",45.5,67,55.5,
                    "TestAuthor","Comedy","SampleSupplier",0, null);
            book.setPurchaseDate(book.getPurchaseDate());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchaseDate_WhenMoreThanOneYearFromCurrentDate() {
        Book book = new Book();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearLater = currentDate.plusYears(1).plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> {
            book.setPurchaseDate(oneYearLater);
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchaseDate_WhenExactlyOneYearFromCurrentDate() {
        Book book = new Book();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearLater = currentDate.plusYears(1);
        book.setPurchaseDate(oneYearLater);
        assertEquals(oneYearLater,book.getPurchaseDate());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPurchaseDate_WhenDateIsTheLocalDateOfNow() {
        Book book = new Book();
        book.setPurchaseDate(LocalDate.now());
        assertEquals(LocalDate.now(), book.getPurchaseDate());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testCanAddBook_WithStockZero() {
            Book book = new Book("123","SampleTitle",45.5,67,55.5,
                    "TestAuthor","Comedy","SampleSupplier",0, LocalDate.of(2023,1,1));
            assertFalse(Book.canAddBook(book,2));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testCanAddBook_WithEnteredQuantityZero() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertFalse(Book.canAddBook(book,0));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testCanAddBook_WithEnteredQuantityMoreThanStock() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertFalse(Book.canAddBook(book,3));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testCanAddBook_WithEnteredQuantityEqualWithTheStock() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertTrue(Book.canAddBook(book,2));
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    void testCanAddBook_WithEnteredQuantityLessThanTheStock() {
        Book book = new Book("123","SampleTitle",45.5,67,55.5,
                "TestAuthor","Comedy","SampleSupplier",2, LocalDate.of(2023,1,1));
        assertTrue(Book.canAddBook(book,1));
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
