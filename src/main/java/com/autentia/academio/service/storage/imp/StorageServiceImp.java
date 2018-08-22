package com.autentia.academio.service.storage.imp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.autentia.academio.service.storage.StorageService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("storageService")
public class StorageServiceImp implements StorageService, Serializable {

    protected final String storageFolder;

    public  StorageServiceImp(@Value("${storageFolder}") String storageFolder){
        this.storageFolder = storageFolder;
    }

    public Path generateStorePath(String fileName){
        try {
            Path folder = Paths.get(storageFolder);
            String filename = FilenameUtils.getBaseName(fileName);
            String extension = FilenameUtils.getExtension(fileName);
            return Files.createTempFile(folder, filename + "-", "." + extension);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void store(InputStream inputStream, Path path) {
        try {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInputStream clientDownload(String filePath) {
        File file = new File(filePath);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }

}