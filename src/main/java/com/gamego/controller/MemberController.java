package com.gamego.controller;

import com.gamego.dto.MemberDto;
import com.gamego.entity.Member;
import com.gamego.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/join")
    public String joinForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "member/joinForm";
    }

    @PostMapping(value = "/join")
    public String memberJoin(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        //유효성 검증이 필요한 객체 앞에 @Valid를 선언하고, 파라미터로 bindingResult 객체를 추가한다
        //검사 결과를 bindingReuslt에 담고, 에러가 있을 경우 회원가입 페이지로 이동한다
        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        try {
            Member member = Member.createUser(memberDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            //회원 가입시 이메일 또는 닉네임이 중복되면 에러 메세지를 뷰로 전달
            model.addAttribute("joinError" , e.getMessage());
            return "member/joinForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String memberLogin(){
        return "/member/loginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
        return "/member/loginForm";
    }

}
