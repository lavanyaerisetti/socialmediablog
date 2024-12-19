package com.lavanya.socialmediablog.utils;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CommentDto mapCommentEntityToDto(final CommentEntity commentEntity) {
        if (commentEntity != null) {
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(commentEntity.getId());
//        commentDto.setUserName(commentEntity.getUserName());
//        commentDto.setEmail(commentEntity.getEmail());
//        commentDto.setBody(commentEntity.getBody());
            return this.modelMapper.map(commentEntity, CommentDto.class);

        }
        return null;
    }

    public CommentEntity mapCommentDtoToEntity(final CommentDto commentDto) {
        if (commentDto != null) {
//        CommentEntity commentEntity=new CommentEntity();
//        commentEntity.setId(commentDto.getId());
//        commentEntity.setUserName(commentDto.getUserName());
//        commentEntity.setEmail(commentDto.getEmail());
//        commentEntity.setBody(commentDto.getBody());
            return this.modelMapper.map(commentDto, CommentEntity.class);

        }
        return null;

    }
}
