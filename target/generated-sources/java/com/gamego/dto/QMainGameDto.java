package com.gamego.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.gamego.dto.QMainGameDto is a Querydsl Projection type for MainGameDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainGameDto extends ConstructorExpression<MainGameDto> {

    private static final long serialVersionUID = 1307789406L;

    public QMainGameDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> gameTitle, com.querydsl.core.types.Expression<String> developer, com.querydsl.core.types.Expression<String> genre, com.querydsl.core.types.Expression<String> platform, com.querydsl.core.types.Expression<String> imgUrl) {
        super(MainGameDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class}, id, gameTitle, developer, genre, platform, imgUrl);
    }

}

