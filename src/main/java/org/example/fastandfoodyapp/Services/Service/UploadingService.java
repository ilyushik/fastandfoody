package org.example.fastandfoodyapp.Services.Service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadingService {
    public void uploadProfileImage(int accountId, MultipartFile file);
}
