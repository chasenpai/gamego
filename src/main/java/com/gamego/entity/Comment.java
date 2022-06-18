package com.gamego.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //다대일 매핑, 지연 로딩 설정
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, length = 20)
    private String writer;

    @Lob
    @Column(nullable = false)
    private String content;

    private int hits;

}
