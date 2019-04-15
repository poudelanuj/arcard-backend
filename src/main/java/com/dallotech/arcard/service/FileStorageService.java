package com.dallotech.arcard.service;

import com.dallotech.arcard.config.FileStorageProperties;
import com.dallotech.arcard.exception.FileStorageException;
import com.dallotech.arcard.exception.ImageFileNotFoundException;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.repository.UserRepository;
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

@Service("fileStorageService")
public class FileStorageService {

    private final Path qrStorageLocation;
    private final Path profileStorageLocation;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {

            this.qrStorageLocation = Paths.get(fileStorageProperties.getQrDir())
                    .toAbsolutePath().normalize();
            this.profileStorageLocation = Paths.get(fileStorageProperties.getProfileDir())
                    .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.qrStorageLocation);
            Files.createDirectories(this.profileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, User user) {
        // Normalize file name
        String fileName = user.getId()+StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.profileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            user.setImagePath(fileName);
            userRepository.save(user);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }



    public Resource loadFileAsResource(String fileName, String imageType) {
        try {
            Path filePath;
            if(imageType.equals("qr")){
                filePath = this.qrStorageLocation.resolve(fileName).normalize();
            }else{
                filePath = this.profileStorageLocation.resolve(fileName).normalize();
            }

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new ImageFileNotFoundException("Given File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ImageFileNotFoundException("File not found " + fileName, ex);
        }
    }



}
