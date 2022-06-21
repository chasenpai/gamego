package com.gamego.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_img")
@Getter
@Setter
public class GameImg extends BaseEntity{

    @Id
    @Column(name = "game_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl;

    private String repImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY) //다대일 매핑, 지연 로딩 설정
    @JoinColumn(name = "game_id")
    private Game game;

    public void updateGameImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }



}
