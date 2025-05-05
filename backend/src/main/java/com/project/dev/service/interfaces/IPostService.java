package com.project.dev.service.interfaces;

import com.project.dev.entity.request.PostCreateRequest;
import com.project.dev.entity.request.PostUpdateRequest;
import com.project.dev.entity.vo.PostVO;
import com.project.dev.exceptions.PostException;

import java.util.List;

/**
 * Service interface for managing blog posts and their lifecycle operations.
 */
public interface IPostService {

    /**
     * Creates a new post in draft state.
     * @param request Contains post creation data
     * @return The created post
     * @throws PostException If validation fails or creation error occurs
     */
    PostVO createPost(PostCreateRequest request) throws PostException;

    /**
     * Updates an existing post.
     * @param id Post ID to update
     * @param request Update data
     * @return The updated post
     * @throws PostException If post not found or validation fails
     */
    PostVO updatePost(Long id, PostUpdateRequest request) throws PostException;

    /**
     * Retrieves a post by ID.
     * @param id Post ID to retrieve
     * @return The found post
     * @throws PostException If post not found
     */
    PostVO getPostById(Long id) throws PostException;

    /**
     * Gets all posts (filtered by visibility based on permissions).
     * @return List of posts
     */
    List<PostVO> getAllPosts();

    /**
     * Gets posts by specific author.
     * @param author Author identifier
     * @return List of author's posts
     */
    List<PostVO> getPostsByAuthor(String author);

    /**
     * Deletes a post by ID.
     * @param id Post ID to delete
     * @throws PostException If post not found or deletion fails
     */
    void deletePost(Long id) throws PostException;

    /**
     * Publishes a draft post, making it publicly visible.
     * @param id Post ID to publish
     * @return The published post
     * @throws PostException If post not found, already published, or validation fails
     */
    PostVO publishPost(Long id) throws PostException;

    /**
     * Archives a post, removing it from public view but preserving it.
     * @param id Post ID to archive
     * @return The archived post
     * @throws PostException If post not found or archiving fails
     */
    PostVO archivePost(Long id) throws PostException;

    /**
     * Gets all posts in draft state (typically for authors/editors).
     * @return List of draft posts
     */
    List<PostVO> getDraftPosts();

    /**
     * Gets all published posts (publicly visible).
     * @return List of published posts
     */
    List<PostVO> getPublishedPosts();
}