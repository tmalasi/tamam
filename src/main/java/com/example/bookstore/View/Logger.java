package com.example.bookstore.View;

public interface Logger {
    default void log(String text){
        System.out.println(text);
    }
}
