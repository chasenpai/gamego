package com.gamego.config;

import com.gamego.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//WebSecurityConfigurerAdapterf을 상속받는 클래스에 선언하면 자동으로 SpringSecurityFilterChain이 포함된다
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    //비밀번호를 BCryptPasswordEncoder 해시 함수로 암호화한다.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //http 요청에 대한 보안을 설정한다
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin()
                .loginPage("/members/login") //로그인 페이지 URL
                .defaultSuccessUrl("/") //로그인 완료 시 이동할 URL
                .usernameParameter("email") //로그인 시 사용할 파라미터로 email
                .failureUrl("/members/login/error") //로그인 실패시 URL
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃 URL
                .logoutSuccessUrl("/"); //로그아웃 완료 시 URL

    }

    //스프링 시큐리티에서 AuthenticationManage 를 통해 인증이 이루어지고 이를 생성하는것이
    //AuthenticationManagerBuilder 이다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //userDetailService 를 구현하고 있는 객체로 memberService 를 지정하고 passwordEncoder 를 지정하여 비밀번호를 암호화한다.
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }



}
