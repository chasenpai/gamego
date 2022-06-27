package com.gamego.dto;

import com.gamego.constant.Released;
import com.gamego.entity.Game;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameDto {

    private Long id;

    @NotBlank(message = "게임 제목을 입력해주세요.")
    private String gameTitle;

    @NotBlank(message = "개발사를 입력해주세요.")
    private String developer;

    @NotBlank(message = "장르를 입력해주세요.")
    private String genre;

    @NotBlank(message = "플랫폼을 입력해주세요.")
    private String platform;

    @NotBlank(message = "출시일을 입력해주세요.")
    private String releaseDate;

    @NotBlank(message = "게임 설명을 입력해주세요.")
    private String gameDetail;

    private Released released;

    private List<GameImgDto> gameImgDtoList = new ArrayList<>(); //게임 등록 후 수정 시 게임 이미지 정보를 저장하는 리스트

    private List<Long> gameImgIds = new ArrayList<>(); //게임 이미지 아이디를 저장하는 리스트로 수정 시에 이미지 아이디를 담음

    private List<CommentDto> commentDtoList;

    private static ModelMapper modelMapper = new ModelMapper();

    public Game createGame(){
        return modelMapper.map(this, Game.class);
    }

    public static GameDto of(Game game){
        return modelMapper.map(game, GameDto.class);
    }


}
