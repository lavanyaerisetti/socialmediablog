package com.lavanya.socialmediablog.service;

import com.lavanya.socialmediablog.dto.PostDto;
import com.lavanya.socialmediablog.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    //Get all Posts

    List<PostDto> getAllPosts();


    PostResponse getAllPosts(int pageNo, int pageSize);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);
    //Get PostById
      PostDto getPosyById(Long Id);

    //Create Post

      PostDto createPost(PostDto postDtoToBeCreated);

    //Update Post

      PostDto updatePost(PostDto postDto,Long postIdToBeUpdated);

    //Delete PostById

      boolean deletePostById(Long postIdToBeDeleted);

}
