package com.gamego.entity;

import com.gamego.constant.Released;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //Game 클래스를 엔티티로 선언
@Table(name = "game") //어떤 테이블과 매핑될지 이름을 지정
@Getter @Setter
@ToString
public class Game {

    @Id //기본키 설정
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키 생성 전략을 자동으로
    @Column(name = "game_id")

    private Long id; //게임 코드

    @Column(nullable = false, length = 40) //NotNull 설정, 필드의 크기 지정
    private String gameTitle; //게임 제목

    @Column(nullable = false, length = 40)
    private String developer; //개발사

    @Column(nullable = false, length = 20)
    private  String genre; //장르

    @Column(nullable = false, length = 20)
    private String platform; //플랫폼

    @Column(nullable = false, length = 40)
    private String releaseDate; //출시일

    @Lob
    @Column(nullable = false)
    private String gameDetail; //게임 상세

    @Enumerated(EnumType.STRING)
    private Released released; //출시 여부

    private LocalDateTime regDate; //등록일
    
    private LocalDateTime updateDate; //수정일
}
