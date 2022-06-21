package com.gamego.repository;

import com.gamego.constant.Released;
import com.gamego.dto.GameSearchDto;
import com.gamego.entity.Game;
import com.gamego.entity.QGame;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;


import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class GameRepositoryCustomImpl implements GameRepositoryCustom {

    private JPAQueryFactory queryFactory; //동적인 쿼리를 작성하기 위해 사용하는 클래스

    public GameRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager); //JPAQueryFactory 의 생성자로 엔티티 매니저 객체를 넣는다
    }

    private BooleanExpression searchReleased(Released searchReleased){
        //출시 상태 조건이 전체(null)일 경우 null 을 리턴. null 은 where 절에서 무시됨
        return searchReleased == null ? null : QGame.game.released.eq(searchReleased);
    }

    private BooleanExpression regDateAfter(String searchDateType){
        //searchDateType 의 값에 따라서 dateTime 의 값을 이전 시간으로 설정함
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        }else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        }else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        }else if(StringUtils.equals("3m", searchDateType)){
            dateTime = dateTime.minusMonths(3);
        }else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }else if(StringUtils.equals("1y", searchDateType)){
            dateTime = dateTime.minusYears(1);
        }

        return QGame.game.regDate.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        //searchBy 의 값에 따라서 검색어가 포함된 게임 또는 작성자의 아이디가 포함된 조건에 따라 조회
        if(StringUtils.equals("gameTitle", searchBy)){
            return QGame.game.gameTitle.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("createdBy", searchBy)){
            return QGame.game.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Game> getAdminGamePage(GameSearchDto gameSearchDto, Pageable pageable) {

        QueryResults<Game> results = queryFactory
                .selectFrom(QGame.game) // 조회할 엔티티를 지정
                .where(regDateAfter(gameSearchDto.getSearchDateType()), //조건을 지정해주는데, 콤마 = and 조건으로 인식한다
                        searchReleased(gameSearchDto.getSearchReleased()),
                        searchByLike(gameSearchDto.getSearchBy(),
                                gameSearchDto.getSearchQuery()))
                        .orderBy(QGame.game.id.desc())
                        .offset(pageable.getOffset()) //데이터를 가지고 올 시작 인덱스를 지정
                        .limit(pageable.getPageSize()) //한 번에 가지고 올 최대 개수를 지정
                        .fetchResults(); //조회한 리스트와 전체 개수를 포함하는 QueryResults 를 반환

        List<Game> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
