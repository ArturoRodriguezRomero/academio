package com.autentia.academio.service.storage.imp;

import com.autentia.academio.service.storage.StorageService;
import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;


public class StorageServiceImpTest {

    private final String TEMP_OS_FOLDER = System.getProperty("java.io.tmpdir");
    private final StorageService sut = new StorageServiceImp(TEMP_OS_FOLDER);

    @Test
    public void whenGenerateStorePathShouldReturnValidPath() {
        String filename = "filename.extension";

        Path path = sut.generateStorePath(filename);

        assertTrue(path.toString().startsWith(TEMP_OS_FOLDER + "filename"));
        assertTrue(path.toString().endsWith(".extension"));
    }

    @Test
    public void whenStoreFileExists() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        Path path = Paths.get(TEMP_OS_FOLDER + "filename.extension");

        sut.store(inputStream, path);

        assertTrue(Files.exists(path));
    }

    @Test
    public void clientDownload() {
        String filePath = "/Users/arturo.rodriguez/Downloads/client-download-test/test.pdf";

        sut.clientDownload(filePath);
    }
}