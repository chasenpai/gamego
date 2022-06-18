package com.gamego.service;

import com.gamego.entity.Member;
import com.gamego.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //로직 처리중 에러 발생시 변경된 데이터를 로직 수행 이전 상태로 콜백해줌
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    //@RequiredArgsConstructor로 final로 선언된 필드에 생성자를 생성해줌.
    //빈에 생성자가 1개이고 생성자의 파라미터 타입이 빈으로 등록이 가능하면 @Autowired 없이 의존성 주입 가능
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateEmail(member);
        validateDuplicateNickname(member);
        return memberRepository.save(member);
    }

    public void validateDuplicateEmail(Member member){ //이미 가입된 이메일은 예외를 발생시킴
        Member emailCheck = memberRepository.findByEmail(member.getEmail());
        if(emailCheck != null){
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }

    public void validateDuplicateNickname(Member member){ //이미 사용중인 닉네임은 예외를 발생시킴
        Member nicknameCheck = memberRepository.findByNickname(member.getNickname());
        if(nicknameCheck != null){
            throw new IllegalStateException("이미 사용중인 닉네임입니다.");
        }
    }

    //UserDetailService 의 메소드를 오버라이딩
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        return User
                .builder()
                .username(member.getNickname())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
        //UserDetail 을 구현하고 있는 Member 객체를 반환해준다.
        //Member 객체를 생성하기 위해서 생성자로 회원 이메일, 비밀번호, role 파라미터를 넘겨준다
    }
}
