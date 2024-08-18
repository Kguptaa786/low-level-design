package com.kgdev.dao;

import com.kgdev.model.File;

import java.util.HashMap;
import java.util.Map;

public class InMemoryFileDao implements FileDao {
    private final Map<String, File> fileMap;
    public InMemoryFileDao() {
        this.fileMap = new HashMap<>();
    }

    @Override
    public File getByFileName(String fileName) {
        return fileMap.getOrDefault(fileName, null);
    }

    @Override
    public File save(File file) {
        fileMap.putIfAbsent(file.getFileName(), file);
        return file;
    }
}
