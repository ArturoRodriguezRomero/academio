package com.autentia.academio.service.storage;

import com.autentia.academio.exceptions.NotFoundException;
import com.autentia.academio.exceptions.NotGeneratedPathException;
import com.autentia.academio.exceptions.NotStoredException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;

public interface StorageService {

    Path generateStorePath(String fileName) throws NotGeneratedPathException;

    void store(InputStream inputStream, Path path) throws NotStoredException;

    FileInputStream clientDownload(String filePath) throws NotFoundException;

}
