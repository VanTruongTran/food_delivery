package com.project.food_delivery.service;

import com.project.food_delivery.model.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImp implements FileUploadService {
    private Path rootPath;//đường dẫn lưu file

    @Autowired
    public FileUploadServiceImp(FileStorageProperties fileStorageProperties) throws IOException {
        //định nghĩa đường dẫn root
        this.rootPath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        //tạo thư mục nếu đường dẫn chưa tồn tại
        if (Files.notExists(this.rootPath)) {
            Files.createDirectories(this.rootPath);
        }
    }

    @Override
    public boolean storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), this.rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            System.out.println("ERROR IN STORING FILE, " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Resource loadFileByName(String fileName) {
        try {
            Path path = this.rootPath.resolve(fileName).normalize();
            //byte[]
            //base64
            //resource
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) {
            System.out.println("ERROR IN LOADING FILE, " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
