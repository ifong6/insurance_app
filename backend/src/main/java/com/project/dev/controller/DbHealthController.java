package com.project.dev.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbHealthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;  // For MySQL checks

    @Autowired
    private MongoTemplate mongoTemplate;

    // MySQL check
    @GetMapping("/health/db/mysql")
    public String checkMySQLConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");  // Simple validation query
            return "✅ MySQL connection successful!";
        } catch (Exception e) {
            return "❌ MySQL connection failed: " + e.getMessage();
        }
    }

    // MongoDB-specific check
    @GetMapping("/health/db/mongo")
    public String checkMongoConnection() {
        try {
            mongoTemplate.getDb().runCommand(new Document("ping", 1));
            return "✅ MongoDB connection successful!";
        } catch (Exception e) {
            return "❌ MongoDB connection failed: " + e.getMessage();
        }
    }
}