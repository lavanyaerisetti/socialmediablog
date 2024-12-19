package com.lavanya.socialmediablog.service.impl;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.entity.CommentEntity;
import com.lavanya.socialmediablog.entity.PostEntity;
import com.lavanya.socialmediablog.exceptions.ResourceNotFoundException;
import com.lavanya.socialmediablog.repository.CommentRepository;
import com.lavanya.socialmediablog.repository.PostRepository;
import com.lavanya.socialmediablog.service.CommentService;
import com.lavanya.socialmediablog.utils.CommentEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentEntityMapper commentEntityMapper;
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<CommentEntity> commentEntityList = commentRepository.findByPostId(postId);
        if(commentEntityList!=null) {
            List<CommentDto> commentDtoList = commentEntityList.stream().map(commentEntity -> commentEntityMapper.mapCommentEntityToDto(commentEntity)).collect(Collectors.toList());
            return commentDtoList;
        }
        return null;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {
        CommentEntity commentEntity=commentRepository.findByPostIdAndCommentId(postId,commentId);
        if(commentEntity!=null){
            CommentDto commentDto=commentEntityMapper.mapCommentEntityToDto(commentEntity);
            return commentDto;
        }
        return null;
    }

    @Override
    public List<CommentDto> getCommentByPostIdAndCommentUserName(long postId, String userName) {
        List<CommentEntity> commentEntityList=commentRepository.findByPostIdAndCommentUserName(postId,userName);
        if(commentEntityList!=null){
            List<CommentDto> commentDtoList=commentEntityList.stream().map(commentEntity -> commentEntityMapper.mapCommentEntityToDto(commentEntity)).collect(Collectors.toList());
            return commentDtoList;
        }
        return null;
    }

    @Override
    public CommentDto createCommentEntity(long postId, CommentDto commentDto) {
        //Convert CommentDto to CommentEntity
        CommentEntity commentEntity = this.commentEntityMapper.mapCommentDtoToEntity(commentDto);

        // Fetch Post Entity from Post Table using postId
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Attach or Set Comment to Particular or associated Post Entity
        commentEntity.setPostEntity(postEntity);

        // Save Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);

        //Map Comment Entity to Comment DTO and return newly create Comment DTO
        return this.commentEntityMapper.mapCommentEntityToDto(newlySavedCommentEntity);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {
        //Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found ::"+ commentId));

        // Validate comment belong to that particular Post
        if(commentEntity != null && postEntity != null) {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }

        // if valid  then Update Old Comment Details with new Comment Dto
        if (commentEntity != null && commentDto != null) {
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setBody(commentDto.getBody());
        }

        // Save updated Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);


        return this.commentEntityMapper.mapCommentEntityToDto(newlySavedCommentEntity);
    }


    @Override
    public String deleteCommentsByPostId(long postId) {
        this.commentRepository.deleteCommentsByPostId(postId);
        return "Successfully Deleted Comments For Post : "+ postId;
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        //Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found ::"+ commentId));

        // Validate comment belong to that particular Post
        if(commentEntity != null && postEntity != null) {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }

        this.commentRepository.delete(commentEntity);
        return "Successfully Deleted Comment : "+ commentId;
    }

    }



