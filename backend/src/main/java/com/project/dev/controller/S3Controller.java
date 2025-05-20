package com.project.dev.controller;

import com.opencsv.exceptions.CsvValidationException;
import com.project.dev.service.S3CsvReader;
import com.project.dev.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3CsvReader s3CsvReader;
    @Autowired
    private S3Service S3Service;

    @GetMapping("/read-csv/{bucketName}/{fileName}")
    public String saveCsvFromS3(
        @PathVariable String bucketName, @PathVariable String fileName) throws CsvValidationException, IOException {
        String key = "uploads/" + fileName;
        s3CsvReader.readCsvFromS3(bucketName, key);

        return "CSV file:" + fileName + " from S3bucket" + bucketName
                + "is read and saved to RDS claim table successfully!";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = "uploads/" + file.getOriginalFilename();

        // Convert MultipartFile to InputStream
        try (InputStream inputStream = file.getInputStream()) {
            S3Service.uploadFile(key, inputStream);
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
