package com.gamego.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainGameDto {

    private Long id;

    private String gameTitle;

    private String developer;

    private String genre;

    private String platform;

    private String imgUrl;

    @QueryProjection
    public MainGameDto(Long id, String gameTitle, String developer, String genre, String platform, String imgUrl){
        this.id = id;
        this.gameTitle = gameTitle;
        this.developer = developer;
        this.genre = genre;
        this.platform = platform;
        this.imgUrl = imgUrl;
    }


}
