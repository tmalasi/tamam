package com.example.bookstore.Models;

import com.example.bookstore.helperClasses.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTotalBilledNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Librarian librarian=new Librarian("TestName","TestUsername","TestPasword","01-01-2002",400,"+3555555",Role.Librarian,-1);
            librarian.setTotalBilled(librarian.getTotalBilled());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    //TODO fix this
    @Test
    public void testSetTotalBilledValueJustMinValue() {
        Librarian librarian=new Librarian("TestName","TestUsername","TestPasword","01-01-2002",400,"+3555555",Role.Librarian,0);
        librarian.setTotalBilled(librarian.getTotalBilled());
        assertEquals(20, librarian.getTotalBilled());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetTotalBilledValuePositive() {
        Librarian librarian=new Librarian("TestName","TestUsername","TestPasword","01-01-2002",400,"+3555555",Role.Librarian,20);
        librarian.setTotalBilled(librarian.getTotalBilled());
        assertEquals(20, librarian.getTotalBilled());
    }

    @Test
    public void testTheConstructorWithoutTotalBilledToStringMethod() {
        Librarian librarian=new Librarian("TestName","TestUsername","TestPassword","01-01-2002",400,"+3555555",Role.Librarian);
        assertEquals("TestName,01-01-2002,+3555555,400,TestUsername,TestPassword,Librarian,0.0",librarian.toString());

    }
    @Test
    void testPersonToStringMethod() {
        Librarian librarian= new Librarian("TestName","TestUsername","TestPassword","01-01-2002",233,"+355686767", Role.Librarian,30);
        assertEquals("TestName,01-01-2002,+355686767,233,TestUsername,TestPassword,Librarian,30.0",librarian.toString());
    }

}
