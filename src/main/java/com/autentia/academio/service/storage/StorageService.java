package com.autentia.academio.service.storage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {

    Path generateStorePath(String fileName);

    void store(InputStream inputStream, Path path);

    FileInputStream clientDownload(String filePath);

}
