package machiev.stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Stream that can be reset and positioned by seek method.
 */
public class RandomAccessDataInputStream extends DataInputStream {

    private final long length;

    /**
     * Creates a RandomAccessDataInputStream that uses the given ByteArrayInputStream.
     *
     * @param in stream that will be wrapped by RandomAccessDataInputStream.
     */
    public RandomAccessDataInputStream(ByteArrayInputStream in) {
        super(in);
        length = in.available();
    }

    public long getPos() throws IOException {
        return length - available();
    }

    public void seek(long n) throws IOException {
        reset();
        skip(n);
    }

    public long length() {
        return length;
    }
}
