package com.gamego.entity;

import com.gamego.dto.ReviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@Setter
@ToString
public class Review extends BaseEntity{

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private int hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewImg> reviewImgList = new ArrayList<>();

    public void updateReview(ReviewDto reviewDto){
        this.title = reviewDto.getTitle();
        this.content = reviewDto.getContent();
    }

    public void incHits(int hits){
        this.hits = hits + 1;
    }



}
