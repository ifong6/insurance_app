package com.project.dev.repository;

import com.project.dev.entity.po.CustomerPO;
import org.springframework.data.repository.CrudRepository;

/**
    Customer: The entity class your repository manages (e.g., @Entity public class Customer { ... })
    Integer: The type of the entity's primary key (@Id private Integer id;)

     Why Keep Entities in Repositories?
     1. JPA/Hibernate Requirement
        Repositories work with @Entity classes, not DTOs
        Annotations like @Id, @Column only work on entities

     2. Database Operations
        CRUD operations expect entity objects
        Transactions and lazy loading depend on entities

     3. Layer Separation
        Repository ↔ Service: Communicate via Entities
        Service ↔ Controller: Communicate via DTOs
 */
public interface CustomerRepository
        extends BaseRepository<CustomerPO, String> {

    CustomerPO findByEmail(String email);

    boolean existsByEmail(String email);
}

