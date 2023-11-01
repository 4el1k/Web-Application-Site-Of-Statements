package ru.itis.services.impl;

import ru.itis.services.FilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalFilesService implements FilesService {
    private String storagePath;
    public LocalFilesService(String directoryPath){
        this.storagePath = directoryPath;
    }

    @Override
    public void upload(String fileName, InputStream fileInputStream) {
        try {
            Files.copy(fileInputStream, Paths.get(storagePath +  fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void download(String fileName, OutputStream fileOutputStream) {
        try {
            Files.copy(Paths.get(storagePath + fileName), fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
