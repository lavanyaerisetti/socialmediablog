package com.lavanya.socialmediablog.service;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.entity.CommentEntity;

import java.util.List;

public interface CommentService {


     List<CommentDto> getAllCommentsByPostId(long postId);

     CommentDto getCommentByPostIdAndCommentId(long postId, long commentId);
     List<CommentDto> getCommentByPostIdAndCommentUserName(long postId,String userName);
     CommentDto createCommentEntity(long postId,CommentDto commentDto);
     CommentDto updateCommentByPostIdAndCommentId(long postId,long commentId,CommentDto commentDto);
     String deleteCommentsByPostId(long PostId);
     String deleteCommentByPostIdAndCommentId(long PostId,long CommentId);


}
