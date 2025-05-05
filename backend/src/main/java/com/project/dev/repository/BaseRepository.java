package com.project.dev.repository;

import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T, ID> extends CrudRepository<T, ID> {
    // This is now a properly generic base repository
    // T = Entity type (e.g., CustomerPO)
    // ID = Type of the primary key (e.g., Long, String, UUID)
}
