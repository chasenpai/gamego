package com.gamego.controller;



import com.gamego.dto.ReviewDto;
import com.gamego.dto.ReviewSearchDto;

import com.gamego.entity.Review;
import com.gamego.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/reviews")
public class ReviewController {

    private final ReviewService reviewService;



    @GetMapping(value = "/users/new")
    public String reviewForm(Model model){
        model.addAttribute("reviewDto", new ReviewDto());
        return "review/reviewForm";
    }

    @PostMapping(value = "/users/new")
    public String gameNew(@Valid ReviewDto reviewDto, BindingResult bindingResult,
                          Model model, @RequestParam("reviewImgFile") List<MultipartFile> reviewImgFileList, Principal principal){

        if(bindingResult.hasErrors()){
            return "review/reviewForm";
        }

        if(reviewImgFileList.get(0).isEmpty() && reviewDto.getId() == null){
            model.addAttribute("errorMessage", "대표 이미지를 등록해주세요.");
            return "review/reviewForm";
        }

        try {
            reviewService.saveReview(reviewDto, reviewImgFileList, principal.getName());
        } catch (Exception e){
            model.addAttribute("errorMessage", "리뷰 등록 중 에러가 발생하였습니다.");
            return "review/reviewForm";
        }

        return "redirect:/reviews/all/list";
    }

    @GetMapping(value = "/users/{reviewId}")
    public String reviewUpdate(@PathVariable("reviewId") Long reviewId, Model model){

        try{
            ReviewDto reviewDto = reviewService.reviewDetail(reviewId);
            model.addAttribute("reviewDto", reviewDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 리뷰입니다..");
            model.addAttribute("reviewDto", new ReviewDto());
            return "review/reviewForm";
        }
        return "review/reviewForm";
    }

    @PostMapping(value = "/users/update")
    public String reviewUpdate(@Valid ReviewDto reviewDto, BindingResult bindingResult,
                             @RequestParam("reviewImgFile") List<MultipartFile> reviewImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "review/reviewForm";
        }
        if(reviewImgFileList.get(0).isEmpty() && reviewDto.getId() == null){
            model.addAttribute("errorMessage", "대표 이미지를 등록해주세요");
            return "review/reviewForm";
        }

        try{
            reviewService.updateReview(reviewDto, reviewImgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage", "리뷰 수정 중 에러가 발생했습니다.");
            return "review/reviewForm";
        }

        return "redirect:/reviews/all/list";
    }


    @GetMapping(value = "/all/{reviewId}")
    public String reviewDetail(@PathVariable("reviewId") Long reviewId, Model model){

        try{
            ReviewDto reviewDto = reviewService.reviewDetail(reviewId);
            model.addAttribute("reviewDto", reviewDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 리뷰입니다.");
            model.addAttribute("reviewDto", new ReviewDto());
            return "review/reviewForm";
        }
        return "review/reviewForm";
    }


    @GetMapping(value = {"/all/list", "/all/list/{page}"})
    public String reviewList(ReviewSearchDto reviewSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Review> reviews = reviewService.getReviewList(reviewSearchDto, pageable);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewSearchDto", reviewSearchDto);
        model.addAttribute("maxPage", 5);

        return "review/reviewList";
    }


    @GetMapping(value = "/all/view/{reviewId}")
    public String reviewDetail(Model model, @PathVariable("reviewId") Long reviewId, Principal principal){
        ReviewDto reviewDto = reviewService.reviewDetail(reviewId);
        model.addAttribute("review", reviewDto);
        if(principal != null){
            model.addAttribute("username", principal.getName());
        }

        return "review/reviewDetail";
    }



    @GetMapping(value = "/users/delete/{reviewId}")
    public String reviewDelete(@PathVariable("reviewId") Long reviewId, Model model) throws Exception {
            reviewService.deleteReview(reviewId);
        return "redirect:/reviews/all/list";
    }



}
