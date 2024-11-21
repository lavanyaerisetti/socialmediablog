package com.lavanya.socialmediablog.controller;

import com.lavanya.socialmediablog.dto.PostDto;
import com.lavanya.socialmediablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //Get all Posts
    //v1/api/posts
    @GetMapping
    public List<PostDto> fetchAllPosts(){
        return this.postService.getAllPosts();
    }

    // Get Posts By Id
    // /v1/api/posts/{postId}

    @GetMapping("/{postId}")

    public PostDto fetchPostById(@PathVariable long postId){
        return this.postService.getPosyById(postId);
    }

    // Create post
    // /v1/api/post

    @PostMapping

    public PostDto savePost(@RequestBody PostDto postDto){
        return this.postService.createPost(postDto);
    }

    // Update Post

    @PutMapping("/{postId}")

    public PostDto updatePost(@RequestBody PostDto postDto,@PathVariable long postId){
        return this.postService.updatePost(postDto,postId);

    }


    // Delete Post

 @DeleteMapping("/{postId}")

    public boolean deletePostById(@PathVariable long postId){
        this.postService.deletePostById(postId);
        return true;
 }


}
