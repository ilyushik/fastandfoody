package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Image, Long> {
    public Optional<Image> findByName(String name);

    @Query("SELECT i.imageData FROM Image i")
    public List<byte[]> findAllImageData();
}
