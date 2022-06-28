package com.gamego.controller;

import com.gamego.dto.CommentDto;
import com.gamego.dto.GameDto;
import com.gamego.dto.GameSearchDto;
import com.gamego.entity.Comment;
import com.gamego.entity.Game;
import com.gamego.service.CommentService;
import com.gamego.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
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
@RequestMapping(value = "/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    private final CommentService commentService;

    @GetMapping(value = "/admin/new")
    public String gameForm(Model model){
        model.addAttribute("gameDto", new GameDto());
        return "game/gameForm";
    }

    @PostMapping(value = "/admin/new")
    public String gameNew(@Valid GameDto gameDto, BindingResult bindingResult,
                          Model model, @RequestParam("gameImgFile") List<MultipartFile> gameImgFileList){

        if(bindingResult.hasErrors()){
            return "game/gameForm";
        }

        if(gameImgFileList.get(0).isEmpty() && gameDto.getId() == null){
            model.addAttribute("errorMessage", "대표 이미지를 등록해주세요.");
            return "game/gameForm";
        }

        try {
            gameService.saveGame(gameDto, gameImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "게임 등록 중 에러가 발생하였습니다.");
            return "game/gameForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/admin/{gameId}")
    public String gameDetail(@PathVariable("gameId") Long gameId, Model model){

        try{
            GameDto gameDto = gameService.gameDetail(gameId);
            model.addAttribute("gameDto", gameDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("gameDto", new GameDto());
            return "game/gameForm";
        }
        return "game/gameForm";
    }


    @PostMapping(value = "/admin/{gameId}")
    public String gameUpdate(@Valid GameDto gameDto, BindingResult bindingResult,
                             @RequestParam("gameImgFile") List<MultipartFile> gameImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "game/gameForm";
        }
        if(gameImgFileList.get(0).isEmpty() && gameDto.getId() == null){
            model.addAttribute("errorMessage", "대표 이미지를 등록해주세요");
            return "game/gameForm";
        }

        try{
            gameService.updateGame(gameDto, gameImgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage", "게임 수정 중 에러가 발생했습니다.");
            return "game/gameForm";
        }

        return "redirect:/games/admin/games";
    }

    @PostMapping(value = "/admin/delete")
    public String gameDelete(GameDto gameDto, @RequestParam("gameImgFile") List<MultipartFile> gameImgFileList,
                               Model model){

        try {
            gameService.deleteGame(gameDto, gameImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "게임 삭제 중 에러가 발생했습니다.");
            return "game/gameForm";
        }

        return "redirect:/games/admin/games";
    }


    @GetMapping(value = {"/admin/games", "/admin/games/{page}"}) //페이지 번호가 있는 경우와 없는 경우 두가지로 매핑
    public String gameManage(GameSearchDto gameSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5); //페이지 번호와 가져올 데이터 수를 설정
        Page<Game> games = gameService.getAdminGamePage(gameSearchDto, pageable); //조회 조건과 페이징 정보를 넘기고 Page<Game> 객체를 반환받음
        model.addAttribute("games", games);
        model.addAttribute("gameSearchDto", gameSearchDto); //페이지 전환 시 기존 검색 조건을 유지하기 위해 뷰에 재전달
        model.addAttribute("maxPage", 5); //페이지 번호의 최대 개수 설정

        return "game/gameManage";
    }


    @GetMapping(value = {"/all/view/{gameId}", "/all/view/{gameId}/{page}"})
    public String gameDetail(Model model, @PathVariable("gameId") Long gameId, @PathVariable("page") Optional<Integer> page){
        GameDto gameDto = gameService.gameDetail(gameId);
        model.addAttribute("game", gameDto);
        model.addAttribute("commentDto", new CommentDto());
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Comment> comments = commentService.commentList(gameId, pageable);
        Long avg = commentService.getAvg(gameId);
        if(avg == null){
            avg = Long.valueOf(0);
        }
        model.addAttribute("comments", comments);
        model.addAttribute("maxPage", 5);
        model.addAttribute("avg", avg);

        return "game/gameDetail";
    }


















//    @GetMapping(value = "/show1")
//    public String gameInfo1(Model model){
//        GameDto gameDto = new GameDto();
//        gameDto.setGameTitle("GTA 5");
//        gameDto.setDeveloper("락스타 게임즈");
//        gameDto.setGenre("오픈 월드 액션 어드벤쳐");
//        gameDto.setPlatform("PC");
//
//        model.addAttribute("gameDto", gameDto);
//        return "game/gameInfo1";
//    }
//
//    @GetMapping(value = "/show2")
//    private String gameInfo2(Model model){
//
//        List<GameDto> gameDtoList = new ArrayList<>();
//
//        for(int i = 1; i <= 5; i++){
//            GameDto gameDto = new GameDto();
//            gameDto.setGameTitle("GTA"+i);
//            gameDto.setDeveloper("락스타 게임즈");
//            gameDto.setGenre("오픈 월드 액션 어드벤쳐");
//            gameDto.setPlatform("PC");
//
//            gameDtoList.add(gameDto);
//        }
//        model.addAttribute("gameListDto", gameDtoList);
//        return "game/gameInfo2";
//    }
//
//    @GetMapping(value = "/show3")
//    private String gameInfo3(Model model){
//
//        List<GameDto> gameDtoList = new ArrayList<>();
//
//        for(int i = 1; i <= 5; i++){
//            GameDto gameDto = new GameDto();
//            gameDto.setGameTitle("GTA"+i);
//            gameDto.setDeveloper("락스타 게임즈");
//            gameDto.setGenre("오픈 월드 액션 어드벤쳐");
//            gameDto.setPlatform("PC");
//
//            gameDtoList.add(gameDto);
//        }
//        model.addAttribute("gameListDto", gameDtoList);
//        return "game/gameInfo3";
//    }
}
