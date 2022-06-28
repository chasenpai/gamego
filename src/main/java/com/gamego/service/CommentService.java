package com.gamego.service;

import com.gamego.dto.CommentDto;
import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import com.gamego.entity.Member;
import com.gamego.repository.CommentRepository;
import com.gamego.repository.GameRepository;
import com.gamego.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;

    private final GameService gameService;


    public Long savaComment(CommentDto commentDto, Long gameId, String nickname) throws Exception{
        Member member = memberRepository.findByNickname(nickname);
        Game game = gameRepository.findById(gameId).orElseThrow(EntityExistsException::new);

        commentDto.setGameId(game.getId());
        commentDto.setMemberId(member.getId());

        Comment comment = commentDto.createComment();
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public Page<Comment> commentList(Long gameId, Pageable pageable){
        Game game = gameService.findById(gameId);

        return commentRepository.findByGameOrderByIdDesc(game, pageable);
    }

    @Transactional(readOnly = true)
    public Long getAvg(Long gameId){
        return commentRepository.getAvg(gameId);
    }

    @Transactional(readOnly = true)
    public List<Long> getAvgList(List<Long> gameId){
        return commentRepository.getAvgList(gameId);
    }


}
