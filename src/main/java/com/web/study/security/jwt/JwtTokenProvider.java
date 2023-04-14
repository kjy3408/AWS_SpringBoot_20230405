package com.web.study.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.exception.CustomException;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private final Key key;
	
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) { //key객체를 만드는 방법...
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenRespDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});
		
		authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length()); //위 반복중 만들어진 제일 끝에있는 쉼표하나 지우기.
		
		String authorities = authoritiesBuilder.toString();
		
		long now = (new Date()).getTime();
		// 1000 == 1초
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 30)); //토큰 만료 시간.
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToken = Jwts.builder()
								.setSubject(authentication.getName())
								.claim("userId", userDetails.getUserId())
								.claim("auth", authorities)
								.setExpiration(tokenExpiresDate)
								.signWith(key, SignatureAlgorithm.HS256)
								.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()		//key값으로 token을 해체하여 데이터를 꺼냄
			.parseClaimsJws(token);			

			return true;
			
		}catch (SecurityException | MalformedJwtException e) {
			// Security 라이브러리에 오류가 있거나, JSON의 포맷이 잘못된 형식의 JWT가 들어왔을 때 예외	
			// signatureException이 포함되어 있음
			log.error("Invalid JWT Token", e);
			
		}catch (ExpiredJwtException e) {
			// 토큰의 유효기간이 만료된 경우의 예외
			log.error("Expired JWT Token", e);
			
		}catch (UnsupportedJwtException e) {
			// jwt의 형식을 지키지 않은 경우  (Header.Payload.Signature)
			log.error("Unsupported JWT Token", e);
			
		}catch (IllegalArgumentException  e) {
			// Bearer가 없을 경우 null로 처리하기에 예외
			log.error("IllegalArgument JWT Token", e);
			
		}catch (Exception e) {
			log.error("JWT Token Error", e);
		}
		
		return false;
	}
	
	public Authentication getAuthentication(String accessToken) {
		Claims claims = parseClaims(accessToken);
		Object roles = claims.get("auth");
		
		if(roles == null) {
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		String[] rolesArray = roles.toString().split(",");
		
		Arrays.asList(rolesArray).forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role)); 
		});
		//principal이 필요하기에 UserDetails생성
		UserDetails userDetails = new User(claims.getSubject(), "", authorities); //User는 spring에서 기본적으로 지원해주는 녀석
		//매개변수로 principal 자리에 userDetails가 들어감.
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities); //Authentication을 impl 하고 있기에 return 가능
	}
	
	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody();
			

		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
	
	
}
