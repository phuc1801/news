package com.vnnet.newsconsole.service;



import com.vnnet.newscommon.config.Constants;
import com.vnnet.newscommon.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Override
    public String store(MultipartFile file, String filename, String location) throws Exception {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            throw new IOException("Cannot store file with relative path outside current directory " + filename);
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Files.copy(inputStream, Paths.get(location).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (inputStream != null)
                inputStream.close();
        }
        return filename;
    }

    @Override
    public void deleteFile(String fileName, String localtion) {
        if (!StringUtils.isBlank(fileName)) {
            Path rootLocation = Paths.get(localtion + fileName);
            if(rootLocation.toFile().exists())
                FileSystemUtils.deleteRecursively(rootLocation.toFile());
        }
    }

    @Override
    public Path load(String filename) {
        Path rootLocation = Paths.get(Constants.PRODUCT_IMAGE_PATH);
        return rootLocation.resolve(filename);
    }

    @Override
    public Path loadRecord(String filename) {
        Path rootLocation = Paths.get(Constants.BROADCASTING_RECORD_PATH);
        return rootLocation.resolve(filename);
    }

    @Override
    public Path loadNewsImage(String filename) {
        Path rootLocation = Paths.get(Constants.NEWS_IMAGE_PATH);
        return rootLocation.resolve(filename);
    }


}