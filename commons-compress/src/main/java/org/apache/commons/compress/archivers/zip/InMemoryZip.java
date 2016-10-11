package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.input.RandomAccessDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InMemoryZip extends ZipArchive {

    /**
     * Reads archive contents from the provided data, assuming "UTF8" for file names.
     *
     * @param in the archive's data.
     *
     * @throws IOException if an error occurs while reading the archive.
     */
    public InMemoryZip(final byte[] in) throws IOException {
        this(in, ZipEncodingHelper.UTF8);
    }

    /**
     * Reads archive contents from the provided data, assuming the specified
     * encoding for file names and scanning for unicode extra fields.
     *
     * @param in the archive's data.
     * @param encoding the encoding to use for file names, use null
     * for the platform's default encoding
     *
     * @throws IOException if an error occurs while reading the archive.
     */
    public InMemoryZip(final byte[] in, final String encoding) throws IOException {
        this(new ByteArrayInputStream(in), encoding, true);
    }

    /**
     * Reads archive contents from a given stream, assuming the specified
     * encoding for file names.
     *
     * @param in the archive.
     * @param encoding the encoding to use for file names, use null
     * for the platform's default encoding
     * @param useUnicodeExtraFields whether to use InfoZIP Unicode
     * Extra Fields (if present) to set the file names.
     *
     * @throws IOException if an error occurs while reading the archive.
     */
    public InMemoryZip(final ByteArrayInputStream in, final String encoding, final boolean useUnicodeExtraFields)
            throws IOException {
        super(new RandomAccessDataInputStream(in), encoding, useUnicodeExtraFields);
    }

}
