package com.example.bookstore.Models;


import com.example.bookstore.helperClasses.Role;

public class Person {
    //TODO maybe test getters

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
        if (birthday == null || birthday.isEmpty()) {
            throw new IllegalArgumentException("Birthday cannot be null");
        }
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        } else if (phone.length()<6) {
            throw new IllegalArgumentException("Phone length needs to be at least 6");
        }
        else{
            this.phone = phone;
        }
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
        int minUsernameLength=5;
        int maxUserNameLength=15;
        if (userName == null) {
            throw new IllegalArgumentException("UserName cannot be null");
        } else if (userName.length()<minUsernameLength || userName.length()>maxUserNameLength) {
            throw new IllegalArgumentException("userName length needs to be from 5-15");
        }
        else{
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }


    //test +add req
    public void setPassword(String password) {
        int minPasswordLength =5;
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        } else if (password.length()< minPasswordLength) {
            throw new IllegalArgumentException("Password length needs to be from 5");
        }
        else{
            this.password = password;
        }}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        else{
            this.role=role;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int minNameLength =3;
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else if (name.length()< minNameLength) {
            throw new IllegalArgumentException("Name length needs to be from 5");
        }
        else{
            this.name = name;
        }
    }

    //TODO fix default test maybe

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
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
    @Override
    public String toString() {
        return name + "," + birthday + "," +
                phone + "," + salary +
                "," + userName + "," + password + "," + role.toString();
    }
}

