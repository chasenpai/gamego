package com.gamego.entity;

import com.gamego.constant.Role;
import com.gamego.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 50) //unique 속성 지정
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true ,nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    //user 엔티티를 생성하는 메소드
    public static Member createUser(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        //스프링 시큐리티 설정 클래스의 BCryptPasswordEncoder Bean을 파라미터로 넘겨 비밀번호를 암호화한다
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setNickname(memberDto.getNickname());
        member.setRole(Role.USER);
        return member;
    }



}
