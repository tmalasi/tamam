package com.example.bookstore.helperClasses;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DefaultFileOperations implements FileOperations {
    @Override
    public boolean fileExists(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    @Override
    public double readDoubleFromFile(String filepath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filepath);
             DataInputStream dis = new DataInputStream(fis)) {
            return dis.readDouble();
        }
    }
}