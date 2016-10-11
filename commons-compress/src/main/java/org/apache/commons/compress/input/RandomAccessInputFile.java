package org.apache.commons.compress.input;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessInputFile extends RandomAccessFile implements RandomAccessDataInput {

    public RandomAccessInputFile(String name) throws FileNotFoundException {
        super(name, "r");
    }

    public RandomAccessInputFile(File file) throws FileNotFoundException {
        super(file, "r");
    }

    @Override
    public long getPos() throws IOException {
        return getFilePointer();
    }
}
