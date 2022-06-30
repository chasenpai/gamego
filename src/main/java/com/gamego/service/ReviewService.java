package com.gamego.service;

import com.gamego.dto.GameDto;
import com.gamego.dto.ReviewDto;
import com.gamego.dto.ReviewImgDto;
import com.gamego.dto.ReviewSearchDto;
import com.gamego.entity.Game;
import com.gamego.entity.Member;
import com.gamego.entity.Review;
import com.gamego.entity.ReviewImg;
import com.gamego.repository.MemberRepository;
import com.gamego.repository.ReviewImgRepository;
import com.gamego.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewImgService reviewImgService;

    private final ReviewImgRepository reviewImgRepository;

    private final MemberRepository memberRepository;

    public Long saveReview(ReviewDto reviewDto, List<MultipartFile> ReviewImgFileList, String nickname) throws Exception{

        Member member = memberRepository.findByNickname(nickname);

        reviewDto.setMemberId(member.getId());

        Review review = reviewDto.createReview();
        reviewRepository.save(review);

        for(int i=0;i<ReviewImgFileList.size();i++){
            ReviewImg reviewImg = new ReviewImg();
            reviewImg.setReview(review);

            if(i == 0)
                reviewImg.setRepImgYn("Y");
            else
                reviewImg.setRepImgYn("N");
            reviewImgService.saveReviewImg(reviewImg, ReviewImgFileList.get(i));
        }

        return review.getId();
    }


    public Long updateReview(ReviewDto reviewDto, List<MultipartFile> reviewImgFileList) throws Exception{

        //등록 화면으로부터 전달 받은 게임 아이디로 게임 엔티티를 조회한뒤, 업데이트함
        Review review = reviewRepository.findById(reviewDto.getId()).orElseThrow(EntityNotFoundException::new);
        review.updateReview(reviewDto);

        List<Long> reviewImgIds = reviewDto.getReviewImgIds(); //게임 이미지 리스트를 조회

        //게임 이미지를 업데이트하기 위해 게임 이미지 아이디와 정보를 파라미터로 전달
        for(int i = 0; i < reviewImgFileList.size(); i++){
            reviewImgService.updateReviewImg(reviewImgIds.get(i), reviewImgFileList.get(i));
        }

        return review.getId();
    }




    public Long deleteReview(Long reviewId)throws Exception{

        List<Long> reviewImgIds = reviewImgRepository.getReviewImgIds(reviewId);

        for(int i = 0; i < reviewImgIds.size(); i++){
            reviewImgService.deleteReviewImg(reviewImgIds.get(i));
        }

        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
        reviewRepository.deleteById(review.getId());

        return review.getId();

    }


    @Transactional(readOnly = true) //트렌젝션을 읽기 전용으로 설정
    public ReviewDto reviewDetail(Long reviewId){

        List<ReviewImg> reviewImgList = reviewImgRepository.findByReviewIdOrderByIdAsc(reviewId); //오름차순으로 게임 이미지 조회
        List<ReviewImgDto> reviewImgDtoList = new ArrayList<>();

        for(ReviewImg reviewImg : reviewImgList){ //조회한 GameImg 엔티티를 GameImgDto 객체로 만들어 리스트에 저장
            ReviewImgDto reviewImgDto =ReviewImgDto.of(reviewImg);
            reviewImgDtoList.add(reviewImgDto);
        }

        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
        ReviewDto reviewDto = ReviewDto.of(review);
        reviewDto.setReviewImgDtoList(reviewImgDtoList);

        return reviewDto;
    }


    @Transactional(readOnly = true)
    public Page<Review> getReviewList(ReviewSearchDto reviewSearchDto, Pageable pageable){
        return reviewRepository.getReviewList(reviewSearchDto, pageable);
    }

}
