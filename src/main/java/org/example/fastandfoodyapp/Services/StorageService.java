package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Image;
import org.example.fastandfoodyapp.Repositories.StorageRepository;
import org.example.fastandfoodyapp.util.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Image image = storageRepository.save(Image.builder().name(file.getOriginalFilename())
//                .type(file.getContentType())
                        .type("image/png")
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (image != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = storageRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }

    public List<Image> images() {
        return storageRepository.findAll();
    }
}
