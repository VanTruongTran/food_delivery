package com.project.food_delivery.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public abstract boolean storeFile(MultipartFile file);

    public abstract Resource loadFileByName(String fileName);
}
