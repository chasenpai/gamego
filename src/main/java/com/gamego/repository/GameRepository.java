package com.gamego.repository;

import com.gamego.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByGameTitle(String gameTitle);

}
