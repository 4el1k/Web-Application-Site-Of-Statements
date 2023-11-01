package ru.itis.services;

import ru.itis.dto.SavePostForm;

import java.io.InputStream;

public interface SavePostService {
    boolean save(SavePostForm form, InputStream fileInputStream);
}
