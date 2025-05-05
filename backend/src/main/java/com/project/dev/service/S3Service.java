package com.project.dev.service;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {
    private final S3Template s3Template;
    private final String bucketName;

    @Autowired
    public S3Service(S3Template s3Template,
                     @Value("${spring.cloud.aws.s3.bucket-name}") String bucketName) {
        this.s3Template = s3Template;
        this.bucketName = bucketName;
    }

    public void uploadFile(String key, InputStream inputStream) {
        s3Template.upload(bucketName, key, inputStream);
    }

    public S3Resource downloadFile(String key) {
        return s3Template.download(bucketName, key);
    }
}