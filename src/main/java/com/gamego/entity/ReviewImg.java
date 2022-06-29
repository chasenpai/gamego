package com.gamego.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;

import javax.persistence.*;

@Entity
@Table(name = "review_img")
@Getter
@Setter
public class ReviewImg extends BaseEntity {

    @Id
    @Column(name = "review_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public void updateReviewImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

}
