package com.gamego.repository;

import com.gamego.entity.GameImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GameImgRepository extends JpaRepository<GameImg, Long> {

    List<GameImg> findByGameIdOrderByIdAsc(Long gameId);

}
