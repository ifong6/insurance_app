package com.project.dev.controller;

import com.project.dev.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = "uploads/" + file.getOriginalFilename();

        // Convert MultipartFile to InputStream
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(key, inputStream);
        } // InputStream auto-closed by try-with-resources

        return "File uploaded to S3: " + key;
    }

//    @GetMapping("/download/{key}")
//    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
//        byte[] file = s3Service.downloadFile(key);
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=\"" + key + "\"")
//                .body(file);
//    }
}
