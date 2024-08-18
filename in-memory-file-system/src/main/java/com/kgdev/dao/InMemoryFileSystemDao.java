package com.kgdev.dao;

import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryFileSystemDao implements FileSystemDao{
    private final AtomicInteger count;

    public InMemoryFileSystemDao() {
        this.count = new AtomicInteger(0);
    }

    @Override
    public int getCountOfOpenFile() {
        return count.get();
    }

    @Override
    public void incrementOpenFile() {
        count.incrementAndGet();
    }

    @Override
    public void decrementOpenFile() {
        count.decrementAndGet();
    }
}
