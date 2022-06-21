package com.gamego.service;

import com.gamego.entity.GameImg;
import com.gamego.repository.GameImgRepository;
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
public class GameImgService {

    @Value("${gameImgLocation}")
    private String gameImgLocation;

    private final GameImgRepository gameImgRepository;

    private final FileService fileService;

    public void saveGameImg(GameImg GameImg, MultipartFile GameImgFile) throws Exception{
        String oriImgName = GameImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(gameImgLocation, oriImgName,
                    GameImgFile.getBytes());
            imgUrl = "/images/Game/" + imgName;
        }

        //게임 이미지 정보 저장
        GameImg.updateGameImg(oriImgName, imgName, imgUrl);
        gameImgRepository.save(GameImg);
    }


    public void updateGameImg(Long gameImgId, MultipartFile gameImgFile)throws Exception{

        //이미지를 수정한 경우 업데이트
        if(!gameImgFile.isEmpty()){
            GameImg savedGameImg = gameImgRepository.findById(gameImgId).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedGameImg.getImgName())){
                fileService.deleteFile(gameImgLocation+ "/" + savedGameImg.getImgName());
            }

            String oriImgName = gameImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(gameImgLocation, oriImgName, gameImgFile.getBytes());
            String imgUrl = "/images/game/" + imgName;
            savedGameImg.updateGameImg(oriImgName, imgName, imgUrl);

            //게임 등록 때와 다르게 save 메소드를 사용하지 않아도 영속화된 엔티티의 데이터를 변경하는 것만으로도
            //변경 감지 기능이 작동하여 트랜젝션이 끝날때 update 쿼리를 실행합니다.
        }


    }


   

}
