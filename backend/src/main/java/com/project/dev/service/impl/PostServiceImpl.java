package com.project.dev.service.impl;

import com.project.dev.entity.po.PostPO;
import com.project.dev.entity.request.PostCreateRequest;
import com.project.dev.entity.request.PostUpdateRequest;
import com.project.dev.entity.vo.PostVO;
import com.project.dev.exceptions.ExceptionEnum;
import com.project.dev.exceptions.PostException;
import com.project.dev.repository.PostRepository;
import com.project.dev.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional
    public PostVO createPost(PostCreateRequest request) throws PostException {
        validatePostRequest(request);

        PostPO post = new PostPO();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(request.getAuthor());
        post.setStatus("DRAFT");

        PostPO savedPost = postRepository.save(post);
        return convertToVO(savedPost);
    }

    @Override
    @Transactional
    public PostVO updatePost(Long id, PostUpdateRequest request) throws PostException {
        // 1. Get the post
        PostPO post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ExceptionEnum.POST_NOT_FOUND));

        // 2. Get current user from Spring Security context (JWT)
        String currentUsername = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // 3. Check if the current user is the post owner
        if (!post.getAuthor().equals(currentUsername)) {
            throw new PostException(ExceptionEnum.UNAUTHORIZED_EDIT);
        }

        // 4. Update fields if provided
        if (request.getTitle() != null) {
            post.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }

        PostPO updatedPost = postRepository.save(post);
        return convertToVO(updatedPost);
    }

//    @Override
//    public PostVO getPostById(Long id) throws PostException {
//        PostVO post = postRepository.findById(id);
//        if (post == null){
//            throw new PostException(ExceptionEnum.POST_NOT_FOUND);
//        }
//
//
//        return convertToVO(post);  // Fixed typo in method name (V0 -> VO)
//    }

    @Override
    public List<PostVO> getAllPosts() {
        List<PostPO> allPosts = postRepository.findAll();
        if (allPosts == null){
//            return List.of();
            return Collections.emptyList();  // Fixed null check
        }

        return allPosts.stream()  // Fixed stream operation
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostVO> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePost(Long id) throws PostException {
        PostPO post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ExceptionEnum.POST_NOT_FOUND));
        postRepository.delete(post);
    }

    @Override
    @Transactional
    public PostVO publishPost(Long id) throws PostException {
        PostPO post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ExceptionEnum.POST_NOT_FOUND));

        if ("PUBLISHED".equals(post.getStatus())) {
            throw new PostException(ExceptionEnum.POST_ALREADY_PUBLISHED);
        }

        post.setStatus("PUBLISHED");
        PostPO updatedPost = postRepository.save(post);
        return convertToVO(updatedPost);
    }

    @Override
    public PostVO archivePost(Long id) throws PostException {
        return null;
    }

    @Override
    public List<PostVO> getDraftPosts() {
        return List.of();
    }

    @Override
    public List<PostVO> getPublishedPosts() {
        return List.of();
    }

    private void validatePostRequest(PostCreateRequest request) throws PostException {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new PostException(ExceptionEnum.TITLE_REQUIRED);
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            throw new PostException(ExceptionEnum.CONTENT_REQUIRED);
        }
        if (request.getAuthor() == null || request.getAuthor().trim().isEmpty()) {
            throw new PostException(ExceptionEnum.AUTHOR_REQUIRED);
        }
    }

    private PostVO convertToVO(PostPO postPO) {
        if (postPO == null) {
            return null;
        }

        PostVO postVO = new PostVO();
        // Copies all matching properties from source to target
        BeanUtils.copyProperties(postPO, postVO);
        return postVO;
    }
}