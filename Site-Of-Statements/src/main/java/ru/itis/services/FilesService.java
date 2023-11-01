package ru.itis.services;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void upload(String fileName, InputStream fileInputStream);
    void download(String fileName, OutputStream fileOutputStream);
}
