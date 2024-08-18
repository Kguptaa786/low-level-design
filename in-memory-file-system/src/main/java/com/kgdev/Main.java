package com.kgdev;

import com.kgdev.dao.FileDao;
import com.kgdev.dao.FileSystemDao;
import com.kgdev.dao.InMemoryFileDao;
import com.kgdev.dao.InMemoryFileSystemDao;
import com.kgdev.service.FileService;
import com.kgdev.service.FileServiceImpl;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {

        FileDao fileDao = new InMemoryFileDao();
        FileSystemDao fileSystemDao = new InMemoryFileSystemDao();
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        FileService fileService = new FileServiceImpl(fileDao, fileSystemDao, lock);




    }
}


