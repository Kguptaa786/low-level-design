package com.kgdev.service;

import com.kgdev.constant.BaseConstant;
import com.kgdev.dao.FileDao;
import com.kgdev.dao.FileSystemDao;
import com.kgdev.model.File;

import java.util.concurrent.locks.ReadWriteLock;

public class FileServiceImpl implements FileService{

    private final FileDao fileDao;
    private final FileSystemDao fileSystemDao;
    private final ReadWriteLock lock;
    public FileServiceImpl(FileDao fileDao, FileSystemDao fileSystemDao, ReadWriteLock lock) {
        this.fileDao = fileDao;
        this.fileSystemDao = fileSystemDao;
        this.lock = lock;
    }

    @Override
    public File open(String fileName) {
        if(fileSystemDao.getCountOfOpenFile() >= BaseConstant.MAX_OPEN_FILE_COUNT){
            throw new IllegalArgumentException("Max open file limit reached. Please close file");
        }
        File file = fileDao.getByFileName(fileName);

        if(file == null){
            file = File.create(fileName, "");
        }
        file.setOpen(true);
        fileSystemDao.incrementOpenFile();
        return fileDao.save(file);
    }

    @Override
    public void close(String fileName) {
        File file = fileDao.getByFileName(fileName);
        if(file == null){
            throw new IllegalArgumentException("No file is present with name : "+fileName);
        }
        if(!file.isOpen()) {
            throw new IllegalArgumentException("File is not opened. Please open a file then perform write operation");
        }
        file.setOpen(false);
        fileDao.save(file);
        fileSystemDao.decrementOpenFile();
    }

    @Override
    public String read(String fileName, int position) {
        File file = fileDao.getByFileName(fileName);
        if(file == null){
            throw new IllegalArgumentException("No file is present with name : "+fileName);
        }
        if(position > file.getEndPointer()) {
            throw new IllegalArgumentException("Position exceed the end pointer");
        }
        if(!file.isOpen()) {
            throw new IllegalArgumentException("File is not opened. Please open a file then perform write operation");
        }
        String content;
        lock.readLock().lock();
        try {
            content = file.getContent().substring(position);
        } catch (Exception e){
            throw new IllegalArgumentException("Error occurred : "+e.getMessage());
        } finally {
            lock.readLock().unlock();
        }
        return content;
    }

    @Override
    public void write(String fileName, String content, int position) {
        File file = fileDao.getByFileName(fileName);
        if(file == null){
            throw new IllegalArgumentException("No file is present with name : "+fileName);
        }
        if(position > file.getEndPointer()) {
            throw new IllegalArgumentException("Position exceed the end pointer");
        }
        if(!file.isOpen()) {
            throw new IllegalArgumentException("File is not opened. Please open a file then perform write operation");
        }
        lock.writeLock().lock();
        try {
            String firstPartOfContent = file.getContent().substring(0, position);
            String secondPartOfContent = file.getContent().substring(position);
            String newContent = firstPartOfContent + content + secondPartOfContent;
            file.setContent(newContent);
            file.setEndPointer(newContent.length());
            fileDao.save(file);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void lSeek(String fileName, int position) {
        File file = fileDao.getByFileName(fileName);
        if(file == null){
            throw new IllegalArgumentException("No file is present with name : "+fileName);
        }
        if(position > file.getEndPointer()) {
            throw new IllegalArgumentException("Position exceed the end pointer");
        }
        lock.writeLock().lock();
        try {
            file.setEndPointer(position);
            fileDao.save(file);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
