package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(String username) {
        return fileMapper.selectFile(username);
    }

    public List<File> getFilesFromUser(Integer userId) {
        return fileMapper.selectFilesFromUser(userId);
    }

    public List<File> getAllFiles() {
        return fileMapper.selectAllFiles();
    }

    public void insetFile() {
        File file = new File();
        fileMapper.insertFile(file);
    }

    public void updateFile(File file) {
        fileMapper.insertFile(file);
    }

    public Integer deleteFile(File file) {
        Integer fileId = file.getFileId();
        return fileMapper.deleteFile(fileId);
    }
}

