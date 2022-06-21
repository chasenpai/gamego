package com.gamego.dto;

import com.gamego.constant.Released;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSearchDto {

    private String searchDateType; //현재 시간과 게임 등록일을 비교해서 데이터를 조회

    private Released searchReleased; //게임의 출시여부로 데이터를 조회

    private String searchBy; //조회시 기준

    private String searchQuery = ""; //게임 검색

}
