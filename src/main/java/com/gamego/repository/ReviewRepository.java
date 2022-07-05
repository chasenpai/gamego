package com.gamego.repository;

import com.gamego.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends JpaRepository<Review, Long>, QuerydslPredicateExecutor<Review>, ReviewRepositoryCustom {

    @Modifying
    @Query("update Review r set r.hits = r.hits + 1 where r.id =:id")
    void increaseHits(Long id);

}
