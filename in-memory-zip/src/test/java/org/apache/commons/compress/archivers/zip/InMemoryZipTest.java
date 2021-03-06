package org.apache.commons.compress.archivers.zip;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InMemoryZipTest {

    private static Map<String, byte[]> expectedContents;

    @Parameter
    public String archiveFileName;

    @BeforeClass
    public static void setUp() throws UnsupportedEncodingException {
        expectedContents = ImmutableMap.of(
                "file1.txt", "line 1\nline 2".getBytes("UTF-8"),
                "file2.txt", "first line\nsecond line".getBytes("UTF-8"));
    }

    @Parameters(name = "{index}: archiveFileName={0}")
    public static Collection<String> data() {
        return Arrays.asList("regular_zip.zip", "slfx_zip.exe");
    }

    @Test
    public void shouldUnzipRegularZipArchive() throws IOException, URISyntaxException {
        //given
        URL archiveUrl = Resources.getResource(archiveFileName);
        byte[] archiveContents = Resources.toByteArray(archiveUrl);
        //when
        InMemoryZip inMemoryZip = new InMemoryZip(archiveContents);
        //then
        assertExtractedContents(expectedContents, extractContents(inMemoryZip));
    }

    private static void assertExtractedContents(Map<String, byte[]> expectedContents, Map<String, byte[]> stringMap) {
        assertEquals("Size differs", expectedContents.size(), stringMap.size());
        for (Map.Entry<String, byte[]> entry : expectedContents.entrySet()) {
            assertArrayEquals(entry.getValue(), stringMap.get(entry.getKey()));
        }
    }

    private static Map<String, byte[]> extractContents(InMemoryZip inMemoryZip) throws IOException {
        Map<String, byte[]> extractedEntries2Contents = Maps.newHashMap();
        Enumeration<ZipArchiveEntry> entries = inMemoryZip.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry archiveEntry = entries.nextElement();
            byte[] entryContents = new byte[(int) archiveEntry.getSize()];
            InputStream contentStream = inMemoryZip.getInputStream(archiveEntry);
            IOUtils.readFully(contentStream, entryContents);
            extractedEntries2Contents.put(archiveEntry.getName(), entryContents);
        }
        return extractedEntries2Contents;
    }

}