package com.example.bookstore.helperClasses.Mock;

import com.example.bookstore.helperClasses.FileOperations;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MockFileOperations implements FileOperations {
    private final boolean mockFileExists;
    private final double mockDoubleValue;

    public MockFileOperations(boolean mockFileExists, double mockDoubleValue) {
        this.mockFileExists = mockFileExists;
        this.mockDoubleValue = mockDoubleValue;
    }

    @Override
    public boolean fileExists(String filepath) {
        return mockFileExists;
    }

    @Override
    public double readDoubleFromFile(String filepath) throws IOException {
        if (mockFileExists) {
            return mockDoubleValue;
        } else {
            throw new FileNotFoundException("Mock file not found");
        }
    }
}
