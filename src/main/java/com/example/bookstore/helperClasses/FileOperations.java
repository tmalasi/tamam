package com.example.bookstore.helperClasses;

import java.io.IOException;

public interface FileOperations {
    boolean fileExists(String filepath);
    double readDoubleFromFile(String filepath) throws IOException;

}
