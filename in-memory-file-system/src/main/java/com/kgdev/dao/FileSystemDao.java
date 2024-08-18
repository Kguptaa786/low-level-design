package com.kgdev.dao;


public interface FileSystemDao {
    int getCountOfOpenFile();
    void incrementOpenFile();
    void decrementOpenFile();
}
