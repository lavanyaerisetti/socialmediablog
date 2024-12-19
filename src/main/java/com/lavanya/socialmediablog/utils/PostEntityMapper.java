package com.lavanya.socialmediablog.utils;

import com.lavanya.socialmediablog.dto.PostDto;
import com.lavanya.socialmediablog.entity.PostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostEntityMapper {
    @Autowired
    private ModelMapper modelMapper;

    //map or convert post entity to post dto
    public PostDto mapPostEntityToPostDto(final PostEntity postEntity) {
        if (postEntity != null) {
//        PostDto postDto = new PostDto();
//        postDto.setId(postEntity.getId());
//        postDto.setTitle(postEntity.getTitle());
//        postDto.setDescription(postEntity.getDescription());
//        postDto.setContent(postEntity.getContent());
            return this.modelMapper.map(postEntity, PostDto.class);
        }
        return null;
    }

        // map or convert post dto to post entity
        public PostEntity mapPostDtoToPostEntity(final PostDto postDto) {
            if (postDto != null) {
//            PostEntity postEntity  = new PostEntity();
//            postEntity.setTitle(postDto.getTitle());
//            postEntity.setDescription(postDto.getDescription());
//            postEntity.setContent(postDto.getContent());
                return this.modelMapper.map(postDto, PostEntity.class);
            }
            return null;
        }
    }


