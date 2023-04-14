package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.security.jwt.JwtAuthenticationEntryPoint;
import com.web.study.security.jwt.JwtAuthenticationFilter;
import com.web.study.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //security filter
		http.csrf().disable();
		http.httpBasic().disable();// 웹 기본 인증 방식
		http.formLogin().disable();// form태그를 통한 로그인\
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//세션 비활성(무상태성[client가 누구였는지 기억하지 않음])
		
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**") //요청이 로그인이나 회원가입이면!
			.permitAll()	//인증 필요X
//			.antMatchers("/courses")
//			.hasRole("ADMIN")
			.anyRequest() //나머지 request는!			
			.authenticated() //인증이 필요하다 (AuthenticationContextHolder에 가서 만들어진 Authentication이 있는지 확인 한다)
			.and()									//usernamePass....class는 고정
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling() //예외 핸들러
			.authenticationEntryPoint(jwtAuthenticationEntryPoint); //Authentication객체 실패시 error보내는 용도
	}
}
