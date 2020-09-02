package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.catalina.webresources.FileResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(String fileId) {
        return fileMapper.selectFile(fileId);
    }

    public Resource getResourceFromFile(File file) {
        return new ByteArrayResource(file.getFileData());
    }

    public List<File> getFilesFromUser(Integer userId) {
        return fileMapper.selectFilesFromUser(userId);
    }

    public List<File> getAllFiles() {
        return fileMapper.selectAllFiles();
    }

    public Integer insertFile(MultipartFile multipartFile, Integer userId) {
        File file = new File();
        try {
            file.setFileData(multipartFile.getBytes());
            file.setFileName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setFileSize(String.valueOf(multipartFile.getSize()));
            file.setUserId(userId);
            return fileMapper.insertFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updateFile(File file) {
        return fileMapper.insertFile(file);
    }

    public Integer deleteFile(Integer fileId) {
        return fileMapper.deleteFile(fileId);
    }
}

