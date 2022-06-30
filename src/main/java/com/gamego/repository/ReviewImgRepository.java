package com.gamego.repository;

import com.gamego.entity.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {

    List<ReviewImg> findByReviewIdOrderByIdAsc(Long reviewId);

    @Query(value = "select i.review_img_id from review_img i where i.review_id = :id", nativeQuery = true)
    List<Long> getReviewImgIds(@Param("id") Long reviewId);
}
