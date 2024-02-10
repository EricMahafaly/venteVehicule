package com.Eric.venteVehicule.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FileService {

    @Autowired
    private ResourceLoader resourceLoader;

    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename(); // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName)); // to generated random string
            // values for file name.

            File file = this.convertToFile(multipartFile, fileName); // to convert multipartFile to File
            String URL = this.uploadFile(file, fileName); // to get uploaded file link
            file.delete();
            return URL;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("ventevehicule-b035b.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Resource resource = resourceLoader
                .getResource("classpath:ventevehicule-b035b-firebase-adminsdk-sgy6c-1cc6cbb2f2.json");
        InputStream serviceAccount = resource.getInputStream();
        Credentials credentials = GoogleCredentials
                .fromStream(serviceAccount);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/venteVehicule-storage.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
