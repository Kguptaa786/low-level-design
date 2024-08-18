package com.kgdev.model;

import lombok.Data;

import java.util.UUID;

@Data
public class File {
    private final UUID id;
    private String fileName;
    private String content;
    private int startPointer;
    private int endPointer;
    private boolean isOpen;

    private File(UUID id, String fileName, String content, int startPointer, int endPointer, boolean isOpen) {
        this.id = id;
        this.fileName = fileName;
        this.content = content;
        this.startPointer = startPointer;
        this.endPointer = endPointer;
        this.isOpen = isOpen;
    }

    public static File create(String filaName, String content) {
        return new File(UUID.randomUUID(), filaName, content,0, 0, false);
    }
}
