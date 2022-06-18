package com.gamego.controller;

import com.gamego.constant.Released;
import com.gamego.dto.GameDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/games")
public class GameController {

    @GetMapping(value = "/admin/create")
    public String gameForm(Model model){
        model.addAttribute("gameDto", new GameDto());
        return "game/gameForm";
    }





















    @GetMapping(value = "/show1")
    public String gameInfo1(Model model){
        GameDto gameDto = new GameDto();
        gameDto.setGameTitle("GTA 5");
        gameDto.setDeveloper("락스타 게임즈");
        gameDto.setGenre("오픈 월드 액션 어드벤쳐");
        gameDto.setPlatform("PC");

        model.addAttribute("gameDto", gameDto);
        return "game/gameInfo1";
    }

    @GetMapping(value = "/show2")
    private String gameInfo2(Model model){

        List<GameDto> gameDtoList = new ArrayList<>();

        for(int i = 1; i <= 5; i++){
            GameDto gameDto = new GameDto();
            gameDto.setGameTitle("GTA"+i);
            gameDto.setDeveloper("락스타 게임즈");
            gameDto.setGenre("오픈 월드 액션 어드벤쳐");
            gameDto.setPlatform("PC");

            gameDtoList.add(gameDto);
        }
        model.addAttribute("gameListDto", gameDtoList);
        return "game/gameInfo2";
    }

    @GetMapping(value = "/show3")
    private String gameInfo3(Model model){

        List<GameDto> gameDtoList = new ArrayList<>();

        for(int i = 1; i <= 5; i++){
            GameDto gameDto = new GameDto();
            gameDto.setGameTitle("GTA"+i);
            gameDto.setDeveloper("락스타 게임즈");
            gameDto.setGenre("오픈 월드 액션 어드벤쳐");
            gameDto.setPlatform("PC");

            gameDtoList.add(gameDto);
        }
        model.addAttribute("gameListDto", gameDtoList);
        return "game/gameInfo3";
    }
}
