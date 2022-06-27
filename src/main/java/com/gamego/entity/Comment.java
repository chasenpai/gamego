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
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //다대일 매핑, 지연 로딩 설정
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    @Column(nullable = false)
    private String content;

    private int rating;

    public void updateRating(int rating){
        this.rating = rating;
    }

    public void updateContent(String content){
        this.content = content;
    }

}
