package com.gamego.service;


import com.gamego.entity.ReviewImg;
import com.gamego.repository.ReviewImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewImgService {

    @Value("${reviewImgLocation}")
    private String reviewImgLocation;

    private final ReviewImgRepository reviewImgRepository;

    private final FileService fileService;

    public void saveReviewImg(ReviewImg reviewImg, MultipartFile ReviewImgFile) throws Exception{
        String oriImgName = ReviewImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(reviewImgLocation, oriImgName,
                    ReviewImgFile.getBytes());
            imgUrl = "/images/review/" + imgName;
        }

        reviewImg.updateReviewImg(oriImgName, imgName, imgUrl);
        reviewImgRepository.save(reviewImg);
    }


    public void updateReviewImg(Long reviewImgId, MultipartFile reviewImgFile)throws Exception{

        if(!reviewImgFile.isEmpty()){
            ReviewImg savedReviewImg = reviewImgRepository.findById(reviewImgId).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(savedReviewImg.getImgName())){
                fileService.deleteFile(reviewImgLocation+ "/" + savedReviewImg.getImgName());
            }

            String oriImgName = reviewImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(reviewImgLocation, oriImgName, reviewImgFile.getBytes());
            String imgUrl = "/images/review/" + imgName;
            savedReviewImg.updateReviewImg(oriImgName, imgName, imgUrl);

        }
    }




    public void deleteReviewImg(Long reviewImgId) throws Exception{
        ReviewImg savedReviewImg = reviewImgRepository.findById(reviewImgId).orElseThrow(EntityNotFoundException::new);
        fileService.deleteFile(reviewImgLocation+ "/" + savedReviewImg.getImgName());
    }

}
