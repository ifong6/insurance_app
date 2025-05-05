package com.project.dev.repository;

import com.project.dev.entity.po.PostPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends BaseRepository<PostPO, Long> {
    // Post-specific methods can be added here
    List<PostPO> findByTitleContaining(String keyword);

    @Override
    List<PostPO> findAll();

    List<PostPO> findByAuthor(String author);


}
