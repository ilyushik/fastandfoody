package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Services.Service.UploadingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadingServiceImpl implements UploadingService {
    @Override
    public void uploadProfileImage(int accountId, MultipartFile file) {
        try {
            Path staticPath = Paths.get("src", "main", "resources", "static", "images", "profile");
            String filename = file.getOriginalFilename();
            String[] parts = filename.split("\\.");
            String extension = parts[parts.length - 1];
            Files.copy(file.getInputStream(), staticPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
