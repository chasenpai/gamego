package com.gamego.controller;

import com.gamego.dto.CommentDto;
import com.gamego.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/new")
    public String addComment(CommentDto commentDto, @RequestParam("gameId") Long gameId, Principal principal){
        try{
            commentService.savaComment(commentDto, gameId, principal.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/games/all/view/" + gameId;
    }
}
