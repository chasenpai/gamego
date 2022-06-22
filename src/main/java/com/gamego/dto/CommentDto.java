package com.gamego.dto;

import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private String content;

    private Game game;

    private static ModelMapper modelMapper = new ModelMapper();

    public Comment createComment(){
        return modelMapper.map(this, Comment.class);
    }
}
