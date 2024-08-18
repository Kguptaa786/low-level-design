package com.kgdev.dao;

import com.kgdev.model.File;

public interface FileDao {
    File getByFileName(String fileName);
    File save(File file);
}
