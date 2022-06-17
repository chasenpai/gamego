package com.gamego.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class GameDto {

    private Long id;

    private String gameTitle;

    private String developer;

    private String genre;

    private String platform;

    private String releaseDate;

    private String gameDetail;

    private String released;

    private LocalDateTime regDate;

    private LocalDateTime updateTime;

}
