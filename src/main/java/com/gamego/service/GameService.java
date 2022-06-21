package com.gamego.service;

import com.gamego.dto.GameDto;
import com.gamego.dto.GameImgDto;
import com.gamego.dto.GameSearchDto;
import com.gamego.entity.Game;
import com.gamego.entity.GameImg;
import com.gamego.repository.GameImgRepository;
import com.gamego.repository.GameRepository;
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
public class GameService {

    private final GameRepository gameRepository;

    private final GameImgService gameImgService;

    private final GameImgRepository gameImgRepository;

    public Long saveGame(GameDto gameDto, List<MultipartFile> GameImgFileList) throws Exception{

        //게임 등록
        Game game = gameDto.createGame();
        gameRepository.save(game);

        //이미지 등록
        for(int i=0;i<GameImgFileList.size();i++){
            GameImg gameImg = new GameImg();
            gameImg.setGame(game);

            if(i == 0)
                gameImg.setRepImgYn("Y");
            else
                gameImg.setRepImgYn("N");

            gameImgService.saveGameImg(gameImg, GameImgFileList.get(i));
        }

        return game.getId();
    }


    @Transactional(readOnly = true) //트렌젝션을 읽기 전용으로 설정
    public GameDto gameDetail(Long gameId){

        List<GameImg> gameImgList = gameImgRepository.findByGameIdOrderByIdAsc(gameId); //오름차순으로 게임 이미지 조회
        List<GameImgDto> gameImgDtoList = new ArrayList<>();

        for(GameImg gameImg : gameImgList){ //조회한 GameImg 엔티티를 GameImgDto 객체로 만들어 리스트에 저장
            GameImgDto gameImgDto = GameImgDto.of(gameImg);
            gameImgDtoList.add(gameImgDto);
        }

        Game game = gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
        GameDto gameDto = GameDto.of(game);
        gameDto.setGameImgDtoList(gameImgDtoList);

        return gameDto;
    }


    public Long updateGame(GameDto gameDto, List<MultipartFile> gameImgFileList) throws Exception{

        //등록 화면으로부터 전달 받은 게임 아이디로 게임 엔티티를 조회한뒤, 업데이트함
        Game game = gameRepository.findById(gameDto.getId()).orElseThrow(EntityNotFoundException::new);
        game.updateGame(gameDto);

        List<Long> gameImgIds = gameDto.getGameImgIds(); //게임 이미지 리스트를 조회

        //게임 이미지를 업데이트하기 위해 게임 이미지 아이디와 정보를 파라미터로 전달
        for(int i = 0; i < gameImgFileList.size(); i++){
            gameImgService.updateGameImg(gameImgIds.get(i), gameImgFileList.get(i));
        }

        return game.getId();
    }


    @Transactional(readOnly = true)
    public Page<Game> getAdminGamePage(GameSearchDto gameSearchDto, Pageable pageable){
        return gameRepository.getAdminGamePage(gameSearchDto, pageable);
    }


}