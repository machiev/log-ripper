package org.apache.commons.compress.input;


import java.io.Closeable;
import java.io.DataInput;
import java.io.IOException;

public interface RandomAccessDataInput extends DataInput, Closeable {

    int read() throws IOException;

    int read(byte b[]) throws IOException;

    int read(byte b[], int off, int len) throws IOException;

    long getPos() throws IOException;

    void seek(long pos) throws IOException;

    long length() throws IOException;
}
