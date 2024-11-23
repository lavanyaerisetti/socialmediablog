package com.lavanya.socialmediablog.service.impl;

import com.lavanya.socialmediablog.dto.PostDto;
import com.lavanya.socialmediablog.entity.PostEntity;
import com.lavanya.socialmediablog.repository.PostRepository;
import com.lavanya.socialmediablog.service.PostService;
import com.lavanya.socialmediablog.utils.PostEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
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











