package com.kgdev.service;

import com.kgdev.model.File;

public interface FileService {

    File open(String fileName);
    void close(String fileName);
    String read(String fileName, int position);
    void write(String fileName, String content, int position);
    void lSeek(String fileName, int position);

}
