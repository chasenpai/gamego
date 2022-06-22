package com.gamego.controller;

import com.gamego.dto.GameSearchDto;
import com.gamego.dto.MainGameDto;
import com.gamego.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GameService gameService;

    @GetMapping(value = "/")
    public String main(GameSearchDto gameSearchDto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
        Page<MainGameDto> games = gameService.getMainPage(gameSearchDto, pageable);
        model.addAttribute("games", games);
        model.addAttribute("gameSearchDto", gameSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}
