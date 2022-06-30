package com.gamego.dto;

import com.gamego.entity.ReviewImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ReviewImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ReviewImgDto of(ReviewImg reviewImg){
        return modelMapper.map(reviewImg, ReviewImgDto.class);
    }

}
