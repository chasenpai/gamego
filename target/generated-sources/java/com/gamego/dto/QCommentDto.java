package com.gamego.dto;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

/**
 * com.gamego.dto.QCommentDto is a Querydsl Projection type for CommentDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommentDto extends ConstructorExpression<CommentDto> {

    private static final long serialVersionUID = -1794774538L;

    public QCommentDto(Expression<Long> id, Expression<String> content, Expression<Integer> rating, Expression<String> createdBy, DateTimePath<LocalDateTime> regDate) {
        super(CommentDto.class, new Class<?>[]{long.class, String.class, int.class, String.class, String.class}, id, content, rating, createdBy, regDate);
    }

}

