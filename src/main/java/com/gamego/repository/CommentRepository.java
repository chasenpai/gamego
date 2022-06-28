package com.gamego.repository;

import com.gamego.dto.CommentDto;
import com.gamego.dto.GameDto;
import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import com.gamego.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select avg(c.rating) from Comment c where c.game_id = :id", nativeQuery = true)
    Long getAvg(@Param("id") Long id);

    @Query(value = "select avg(c.rating) from Comment c where c.game_id = :id", nativeQuery = true)
    List<Long> getAvgList(@Param("id") List<Long> id);

    Page<Comment> findByGameOrderByIdDesc(Game game, Pageable pageable);

    Optional<Comment> findByMemberAndGame(Member member, Game game);

}
