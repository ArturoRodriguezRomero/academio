package com.autentia.academio.service.storage.imp;

import com.autentia.academio.service.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.junit.Ignore;
import org.junit.Test;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


@Ignore
public class StorageServiceImpTest {

    private final String TEMP_OS_FOLDER = FilenameUtils.getFullPathNoEndSeparator(this.getClass().getClassLoader().getResource("test.pdf").getPath());
    private final StorageService sut = new StorageServiceImp(TEMP_OS_FOLDER);

    @Test
    public void whenGenerateStorePathShouldReturnValidPath() {
        String filename = "filename.extension";

        Path path = sut.generateStorePath(filename);

        assertTrue(path.toString().startsWith(TEMP_OS_FOLDER + "/filename"));
        assertTrue(path.toString().endsWith(".extension"));
    }

    @Test
    public void whenStoreFileExists() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        Path path = Paths.get(TEMP_OS_FOLDER + "filename.extension");

        sut.store(inputStream, path);

        assertTrue(Files.exists(path));
        Files.delete(path);
        assertFalse(Files.exists(path));
    }

    @Test
    public void clientDownload() {
        URL testUrl = this.getClass().getClassLoader().getResource("test.pdf");
        String filePath = testUrl.getPath();

        sut.clientDownload(filePath);
    }
}