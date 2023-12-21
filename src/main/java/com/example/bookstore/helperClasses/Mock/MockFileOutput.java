package com.example.bookstore.helperClasses.Mock;

import com.example.bookstore.helperClasses.FileOutputInterface;

import java.io.IOException;

public class MockFileOutput implements FileOutputInterface {
    private boolean throwExceptionOnWrite = false;

    public MockFileOutput(boolean throwExceptionOnWrite) {
        this.throwExceptionOnWrite = throwExceptionOnWrite;
    }

    @Override
    public void writeDoubleToFile(double value, String filePath) throws IOException {
        if (throwExceptionOnWrite) {
            throw new IOException("Mock write error");
        }
        // Mock successful write, do nothing
    }
}
