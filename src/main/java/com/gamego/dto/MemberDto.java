package com.gamego.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberDto {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            //message = "비밀번호는 대문자, 소문자, 숫자 ,특수문자를 모두 포함하여 8자리 이상, 20자리 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "닉네임을 입력해주세요")
    private String nickname;

}
