package com.example.bookstore.Models;

import com.example.bookstore.helperClasses.Role;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBirthdayWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("TestName", "TestUsername", "TestPassword", null, 233, "+355686767", Role.Administrator);
            person.setBirthday(person.getBirthday());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBirthdayWhenItIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("TestName", "TestUsername", "TestPassword", "", 233, "+355686767", Role.Administrator);
            person.setBirthday(person.getBirthday());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetBirthdayWhenNormalLength() {
        Person person = new Person("TestName", "TestUsername", "TestPassword", "01-01-2002", 233, "+355686767", Role.Administrator);
        person.setBirthday(person.getBirthday());
        assertEquals("01-01-2002", person.getBirthday());
    }

    //Method for EquivalenceTesting
    @Test
    public void testSetPhoneWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "TestUsername", "TestPassword", "01-01-2002", 400, null, Role.Administrator);
            person.setPhone(person.getPhone());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPhoneWhenLengthSmallerThan6() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "TestUsername", "TestPassword", "01-01-2002", 400, "12345", Role.Administrator);
            person.setPhone(person.getPhone());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPhoneWhenLengthIsEqualTo6() {
        Person person = new Administrator("TestName", "TestUsername", "TestPassword", "01-01-2002", 400, "123456", Role.Administrator);
        person.setPhone(person.getPhone());
        assertEquals("123456", person.getPhone());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPhoneWhenLengthLargerThan6() {
        Person person = new Administrator("TestName", "TestUsername", "TestPassword", "01-01-2002", 400, "1234567", Role.Administrator);
        person.setPhone(person.getPhone());
        assertEquals("1234567", person.getPhone());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPhoneWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "TestUsername", "TestPassword", "01-01-2002", 400, "", Role.Administrator);
            person.setPhone(person.getPhone());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSalaryWhenItIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", -1, "+35555555", Role.Manager);
            person.setSalary(person.getSalary());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSalaryWhenItIsEqualTo0() {
        Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", 0, "+35555555", Role.Manager);
        person.setSalary(person.getSalary());
        assertEquals(0, person.getSalary());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetSalaryWhenItIsValidValue() {
        Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", 1, "+35555555", Role.Manager);
        person.setSalary(person.getSalary());
        assertEquals(1, person.getSalary());
    }

    //Method for EquivalenceTesting
    @Test
    public void testSetUserNameWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", null, "TestPassword", "01-01-2002", 400, "123456", Role.Administrator);
            person.setUserName(person.getUserName());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetUserNameWhenItIsSmallerThanMinLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "null", "TestPassword", "01-01-2002", 400, "123456", Role.Administrator);
            person.setUserName(person.getUserName());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetUserNameWhenItIsBiggerThanMaxLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "1234567891234567", "TestPassword", "01-01-2002", 400, "123456", Role.Administrator);
            person.setUserName(person.getUserName());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetUserNameWhenLengthIsBetweenTheBounds() {
        Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", 0, "+35555555", Role.Manager);
        person.setUserName(person.getUserName());
        assertEquals("TestUsername", person.getUserName());
    }

    //Method for BoundaryValueTesting
    @Test
    public void testSetUserNameWhenLengthIsMaxLength() {
        Person person = new Manager("TestName", "Testi", "testPassword", "01-01-2002", 0, "+35555555", Role.Manager);
        person.setUserName(person.getUserName());
        assertEquals("Testi", person.getUserName());
    }

    //Method for BoundaryValueTesting
    @Test
    public void testSetUserNameWhenLengthIsMinLength() {
        Person person = new Manager("TestName", "user123456789", "testPassword", "01-01-2002", 0, "+35555555", Role.Manager);
        person.setUserName(person.getUserName());
        assertEquals("user123456789", person.getUserName());
    }

    //Method for EquivalenceTesting
    @Test
    public void testSetPasswordWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "TestUserName", null, "01-01-2002", 400, "123456", Role.Administrator);
            person.setPassword(person.getPassword());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPassword_WhenItIsSmallerThanMinLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "null", "test", "01-01-2002", 400, "123456", Role.Administrator);
            person.setPassword(person.getPassword());
        });
    }

    //Method for BoundaryValueTesting
    @Test
    public void testSetPasswordWhenLengthIsMinLength() {
        Person person = new Manager("TestName", "TestUsername", "testi", "01-01-2002", 400, "+35555555", Role.Manager);
        person.setPassword(person.getPassword());
        assertEquals("testi", person.getPassword());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetPasswordWhenLengthIsBetweenTheBounds() {
        Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", 400, "+35555555", Role.Manager);
        person.setPassword(person.getPassword());
        assertEquals("testPassword", person.getPassword());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetRoleWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("TestName", "TestUserName", "TestPassword", "01-01-2002", 400, "123456", null);
            person.setRole(person.getRole());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetRoleWhenItValidRoleThisExampleManager() {
        Person person = new Manager("TestName", "TestUsername", "testPassword", "01-01-2002", 400, "+35555555", Role.Manager);
        person.setRole(person.getRole());
        assertEquals(Role.Manager, person.getRole());
    }

    //Method for EquivalenceTesting
    @Test
    public void testSetNameWhenItIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator(null, "TestUserName", "TestPassword", "01-01-2002", 400, "123456", Role.Manager);
            person.setName(person.getName());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetName_WhenItIsSmallerThanMinLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Administrator("IT", "null", "test", "01-01-2002", 400, "123456", Role.Administrator);
            person.setName(person.getName());
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetNameWhenItaLengthIsMinValue() {
        Person person = new Manager("Tes", "TestUsername", "testPassword", "01-01-2002", 400, "+35555555", Role.Manager);
        person.setName(person.getName());
        assertEquals("Tes", person.getName());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testSetNameWhenItaLengthIsOkej() {
        Person person = new Manager("Test", "TestUsername", "testPassword", "01-01-2002", 400, "+35555555", Role.Manager);
        person.setName(person.getName());
        assertEquals("Test", person.getName());
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testCreatePerson_WhenRoleIsNotOneOfTheCases() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person.createPerson("TestName", "TestUsername", "TestPassword", "01-01-2002", 233, "+355686767", Role.Test);
        });
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testCreatePersonWhenRoleIsLibrarian() {
        Person librarian = Person.createPerson("John Doe", "john_librarian", "password123",
                "1990-01-01", 50000, "123-456-7890", Role.Librarian);
        assertTrue(librarian instanceof Librarian);
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testCreatePersonWhenRoleIsManager() {
        Person manager = Person.createPerson("Jane Manager", "jane_manager", "pass456",
                "1985-05-15", 60000, "987-654-3210", Role.Manager);
        assertTrue(manager instanceof Manager);
    }

    //Method for BoundaryValueTesting and EquivalenceTesting
    @Test
    public void testCreatePersonWhenRoleIsAdministrator() {
        Person administrator = Person.createPerson("Admin Admin", "admin_user", "adminpass",
                "1980-10-20", 70000, "111-222-3333", Role.Administrator);
        assertTrue(administrator instanceof Administrator);
    }

    @Test
    void testPersonToStringMethod() {
        Person person = new Person("TestName", "TestUsername", "TestPassword", "01-01-2002", 233, "+355686767", Role.Administrator);
        assertEquals("TestName,01-01-2002,+355686767,233,TestUsername,TestPassword,Administrator", person.toString());
    }
}
