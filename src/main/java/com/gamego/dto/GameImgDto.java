package com.gamego.dto;

import com.gamego.entity.GameImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class GameImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    //서로 다른 클래스의 값을 필드의 이름과 자료형이 같으면 getter, setter 를 통해 값을 복사해서 객체를 반환해줌
    private static ModelMapper modelMapper = new ModelMapper();

    public static GameImgDto of(GameImg gameImg){
        return modelMapper.map(gameImg, GameImgDto.class);
    }


}
