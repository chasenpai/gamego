package com.gamego.dto;

import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import com.gamego.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private String content;

    private Long gameId;

    private Long memberId;

    private int rating;

    private String createdBy;

    private String regDate;

    private static ModelMapper modelMapper = new ModelMapper();

    public Comment createComment(){
        return modelMapper.map(this, Comment.class);
    }

    public CommentDto(){

    }



}
