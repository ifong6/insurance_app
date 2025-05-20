package com.project.dev.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.project.dev.entity.po.ClaimPO;
import com.project.dev.repository.ClaimRepository;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class S3CsvReader {

    @Autowired
    public S3Template s3Template;

    @Autowired
    public ClaimRepository claimRepository;

    // Method to read CSV from S3 and process it
    public void readCsvFromS3(String bucketName, String key) throws IOException, CsvValidationException {
        // Download the CSV file from S3
        S3Resource file = s3Template.download(bucketName, key);
        InputStream stream = file.getInputStream();

        // data from S3 is in raw bytes. "InputStreamReader" converts it to text
        // CSVReader would split the text into, e.g. ["1", "Emergency Room Visit", "Patient had appendicitis"]
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(stream))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .withQuoteChar('"')  // Handle quoted fields
                        .build())
                .build();

        // 1. Read the CSV file
        String[] row;
        // Skip the header row first
        reader.readNext();

        while ((row = reader.readNext()) != null) { // Read row-by-row
            // 1. Extract CSV fields
            String id = row[0];
            String title = row[1];
            String description = row[2];

            // 2. Map to Entity
            ClaimPO entry = new ClaimPO();
            entry.setClaim_id(Integer.parseInt(id));
            entry.setClaim_title(title);
            entry.setDescription(description);

            // 3. Save to Database
            claimRepository.save(entry);

            System.out.printf("Saved to Claim table in AWS RDS: ID=%s, Title=%s%n", id, title);

        }
    }




}
