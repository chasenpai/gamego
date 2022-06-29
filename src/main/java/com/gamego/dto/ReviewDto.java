package com.gamego.dto;

import com.gamego.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ReviewDto {

    private Long id;

    private String title;

    private String content;

    private int hits;

    private static ModelMapper modelMapper = new ModelMapper();

    public Review createReview(){
        return modelMapper.map(this, Review.class);
    }

    public static ReviewDto of(Review review){
        return modelMapper.map(review, ReviewDto.class);
    }
}
