package com.lavanya.socialmediablog.service.impl;

import com.lavanya.socialmediablog.dto.PostDto;
import com.lavanya.socialmediablog.entity.PostEntity;
import com.lavanya.socialmediablog.payload.PostResponse;
import com.lavanya.socialmediablog.repository.PostRepository;
import com.lavanya.socialmediablog.service.PostService;
import com.lavanya.socialmediablog.utils.PostEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServcieImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostEntityMapper postEntityMapper;

    //Get all Posts
    @Override

    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        if (postEntities != null) {
            return postEntities.stream()
                    .map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<PostEntity> postEntityList=postRepository.findAll(pageable);
        if(postEntityList!=null){
            List<PostDto> postDtoList=postEntityList.stream().map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity)).collect(Collectors.toList());
            PostResponse postResponse=PostResponse.builder().content(postDtoList).pageNo(postEntityList.getNumber())
                                      .pageSize(postEntityList.getSize()).totalElements(postEntityList.getTotalElements())
                    .totalPages(postEntityList.getTotalPages()).isLastPage(postEntityList.isLast()).build();

        return postResponse;
        }

    return null;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection) {

        Pageable pageable =null;

        if(sortBy !=null && sortDirection !=null){

            Sort sort=sortDirection.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
            pageable=PageRequest.of(pageNo,pageSize,sort);
        }else{
            pageable=PageRequest.of(pageNo,pageSize);
        }

        Page<PostEntity> postEntitiesList=postRepository.findAll(pageable);
        if(postEntitiesList!=null){
            List<PostDto> postDtoList=postEntitiesList.stream().map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity)).collect(Collectors.toList());
            PostResponse postResponse=PostResponse.builder().content(postDtoList)
                    .pageNo(postEntitiesList.getNumber()).pageSize(postEntitiesList.getSize())
                    .totalPages(postEntitiesList.getTotalPages()).totalElements(postEntitiesList.getTotalElements())
                    .isLastPage(postEntitiesList.isLast()).build();
            return postResponse;
        }
        return null;
    }


    //Get PostById
    @Override
    public PostDto getPosyById(Long id) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        return optionalPostEntity.map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity)).orElseThrow(() -> new RuntimeException("Post By Id not found " + id));

    }

    //Create Post

    @Override

    public PostDto createPost(PostDto postDtoToBeCreated) {
        PostEntity entityToSave = this.postEntityMapper.mapPostDtoToPostEntity(postDtoToBeCreated);
        return this.postEntityMapper.mapPostEntityToPostDto(this.postRepository.save(entityToSave));

    }

    //Update post

    @Override
    public PostDto updatePost(PostDto postDto, Long postIdToBeUpdated) {

        PostEntity postToBeUpdated = postRepository.findById(postIdToBeUpdated)
                              .orElseThrow(()->new RuntimeException("Post not found by Id " +postIdToBeUpdated));
        postToBeUpdated.setTitle(postDto.getTitle());
        postToBeUpdated.setDescription(postDto.getDescription());
        postToBeUpdated.setContent(postDto.getContent());

        PostEntity updatedPostEntity= postRepository.save(postToBeUpdated);

        return postEntityMapper.mapPostEntityToPostDto(updatedPostEntity);

        }

    //Delete PostById
@Override
    public boolean deletePostById(Long postByIdToBeDeleted){
        Optional<PostEntity> postToBeDeleted=postRepository.findById(postByIdToBeDeleted);
        if(postToBeDeleted.isPresent()){
            postRepository.delete(postToBeDeleted.get());
            return true;
        }

        return false;

    }


}











