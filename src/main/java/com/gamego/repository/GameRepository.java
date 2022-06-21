package com.gamego.repository;

import com.gamego.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, QuerydslPredicateExecutor<Game>, GameRepositoryCustom {

    List<Game> findByGameTitle(String gameTitle);

}
