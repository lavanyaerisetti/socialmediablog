package com.lavanya.socialmediablog.controller;

import com.lavanya.socialmediablog.payload.PostResponse;
import com.lavanya.socialmediablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/api/posts")
public class PostControllerV2 {

    @Autowired
private PostService postService;

    @GetMapping
    public PostResponse fetchAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = "0", required = false) String sortBy,
                                      @RequestParam(value = "sortDirection", defaultValue = "0", required = false) String sortDirection){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDirection);
    }

}
