package com.gamego.repository;

import com.gamego.dto.*;
import com.gamego.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("title", searchBy)){
            return QReview.review.title.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("createdBy", searchBy)){
            return QReview.review.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<Review> getReviewList(ReviewSearchDto reviewSearchDto, Pageable pageable) {

        QueryResults<Review> results = queryFactory
                .selectFrom(QReview.review)
                .where(searchByLike(reviewSearchDto.getSearchBy(),reviewSearchDto.getSearchQuery()))
                .orderBy(QReview.review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Review> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
