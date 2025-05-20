package com.project.dev.controller;

import com.project.dev.entity.request.PostCreateRequest;
import com.project.dev.entity.request.PostUpdateRequest;
import com.project.dev.entity.response.BaseResponse;
import com.project.dev.entity.response.StatusEnum;
import com.project.dev.entity.vo.PostVO;
import com.project.dev.exceptions.PostException;
import com.project.dev.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("")
    public BaseResponse<PostVO> createPost(
            @RequestBody PostCreateRequest request) throws PostException {
        PostVO newPost = postService.createPost(request);

        return BaseResponse.success(newPost);
    }

    @PostMapping("/{id}")
    public BaseResponse<PostVO> publishPost(
            @PathVariable Long id) throws PostException {
        PostVO publishedPost = postService.publishPost(id);

        return BaseResponse.ok(publishedPost);
    }

    @PutMapping("/{id}")
    public BaseResponse<PostVO> updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request) throws PostException {
        PostVO updatedPost = postService.updatePost(id, request);

        return BaseResponse.ok(updatedPost);
    }

    @GetMapping
    public BaseResponse<List<PostVO>> getAllPosts() {
        List<PostVO> allPosts = postService.getAllPosts();
        return BaseResponse.ok(allPosts);
    }

    @GetMapping("/author/{authorName}")
    public BaseResponse<List<PostVO>> getPostsByAuthor(
            @RequestParam String authorName) {

        List<PostVO> posts = postService.getPostsByAuthor(authorName);

        return BaseResponse.ok(posts);
    }
}
