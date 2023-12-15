package com.example.bookstore.Models;


import com.example.bookstore.helperClasses.Role;

public abstract class Person {

    private String name, birthday, phone;

    private int salary;
    private String userName;
    private String password;
    private Role role;

    public Person(String name, String userName, String password, String Birthday, int salary, String Phone,  Role role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.birthday = Birthday;
        this.phone = Phone;
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    //test
    public void setSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("cant be negative");
        } else {
            this.salary = salary;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }


    //test +add req
    public void setPassword(String password) {
        if (password.length() < 3) {
            throw new IllegalArgumentException("Password must be at least 3 characters long.");
        } else {
            this.password = password;
        }}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Person createPerson(String name, String username, String password,
                                      String birthday, int salary, String phone, Role role) {
        switch (role) {
            case Librarian:
                return new Librarian(name, username, password, birthday, salary, phone, Role.Librarian);
            case Manager:
                return new Manager(name, username, password, birthday, salary, phone, Role.Manager);
            case Administrator:
                return new Administrator(name, username, password, birthday, salary, phone, Role.Administrator);
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
    @Override
    public String toString() {
        return name + "," + birthday + "," +
                phone + "," + salary +
                "," + userName + "," + password + "," + role.toString();
    }
}

