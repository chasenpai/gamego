package com.gamego.repository;

import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import com.gamego.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByGameOrderByIdDesc(Game game);

    Optional<Comment> findByMemberAndGame(Member member, Game game);

}
