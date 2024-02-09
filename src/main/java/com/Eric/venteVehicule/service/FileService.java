package com.Eric.venteVehicule.service;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Value("${image.mount.path}")
    private  String imageMountPath;
    public String saveFile(MultipartFile file) throws Exception {
        Path targetPath = Path.of(imageMountPath, file.getOriginalFilename());
        FileUtils.writeByteArrayToFile(targetPath.toFile(), file.getBytes());

        return targetPath.toString();
    }
}
