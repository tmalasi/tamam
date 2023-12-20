package com.example.bookstore.helperClasses;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class DefaultFileOutput implements FileOutputInterface{
        @Override
        public void writeDoubleToFile(double value, String filePath) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(filePath);
                 DataOutputStream dos = new DataOutputStream(fos)) {
                dos.writeDouble(value);
            }
        }
}
