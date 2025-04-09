package com.vnnet.newsconsole.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


public interface StorageService {
    String store(MultipartFile file, String filename, String location) throws  Exception;

    void deleteFile(String fileName, String location);

    Path load(String filename);

    Path loadRecord(String filename);

    Path loadNewsImage(String filename);
}