package com.project.dev.entity.po;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "claim")
public class ClaimPO {

    @Id
    private Integer claim_id;
    @Column(nullable = false, length = 50)
    private String claim_title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Version
    private Integer version; // Optimistic locking

}
