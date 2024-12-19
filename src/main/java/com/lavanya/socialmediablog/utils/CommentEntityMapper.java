package com.lavanya.socialmediablog.utils;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

    public CommentDto mapCommentEntityToDto(final CommentEntity commentEntity){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setUserName(commentEntity.getUserName());
        commentDto.setEmail(commentEntity.getEmail());
        commentDto.setBody(commentEntity.getBody());
        return commentDto;

    }

    public CommentEntity mapCommentDtoToEntity(final CommentDto commentDto){
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setUserName(commentDto.getUserName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());
        return commentEntity;

    }

}
