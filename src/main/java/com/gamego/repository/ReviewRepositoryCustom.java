package com.gamego.repository;

import com.gamego.dto.ReviewSearchDto;
import com.gamego.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<Review> getReviewList(ReviewSearchDto reviewSearchDto, Pageable pageable);
}
