package com.gamego.repository;

import com.gamego.dto.GameDto;
import com.gamego.dto.GameSearchDto;
import com.gamego.dto.MainGameDto;
import com.gamego.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRepositoryCustom {

    //게임 조회 조건을 담고있는 gameSearchDto 와 페이징 정보를 담고있는 pageable 을 파라미터로 받는 메소드
    Page<Game> getAdminGamePage(GameSearchDto gameSearchDto, Pageable pageable);

    Page<MainGameDto> getMainPage(GameSearchDto gameSearchDto, Pageable pageable);

    Page<MainGameDto> getCategory(GameSearchDto gameSearchDto, Pageable pageable);
}
