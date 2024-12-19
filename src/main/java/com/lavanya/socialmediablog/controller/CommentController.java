package com.lavanya.socialmediablog.controller;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        CommentDto savedCommentDto =  this.commentService.createCommentEntity(postId, commentDto);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")

    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> commentDtoList = this.commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    // GET Single Comment REST API - /posts/{post-id}/comments/{comment-id}
    @GetMapping("/posts/{postId}/comments/{commentId:\\d+}")
    public ResponseEntity<CommentDto> fetchCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId) {
        CommentDto commentDto =  this.commentService.getCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // GET Single Comment REST API - /posts/{post-id}/comments/{userName}
    @GetMapping("/posts/{postId}/comments/{userName:[a-zA-Z]+}")
    public ResponseEntity<List<CommentDto>> fetchCommentByPostIdAndCommentUserName(@PathVariable long postId, @PathVariable String userName) {
        List<CommentDto> commentDtoList =  this.commentService.getCommentByPostIdAndCommentUserName(postId, userName);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentsByPostIdAndCommentId(@PathVariable long postId,@PathVariable long commentId,@RequestBody CommentDto commentDto){
        CommentDto updatedCommentDto=this.commentService.updateCommentByPostIdAndCommentId(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedCommentDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments")

    public ResponseEntity<String> deleteCommentsByPostId(@PathVariable long postId){
        String message=this.commentService.deleteCommentsByPostId(postId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")

    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(@PathVariable long postId,@PathVariable long commentId){
        String message=this.commentService.deleteCommentByPostIdAndCommentId(postId,commentId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
