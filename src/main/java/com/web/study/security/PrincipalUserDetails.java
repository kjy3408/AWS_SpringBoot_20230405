package com.web.study.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrincipalUserDetails implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	private int userId;
	private String username;
	private String password;
	private List<String> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	//계정들 중에서 사용기간 만료된것들은 return이 false가 됨. 아래의 메소드 중 false가 하나라도 있으면 로그인안됨.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정을 잠궈버리는 기능
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 n회 틀렸을때 계정을 잠구는 기능을 할때 사용.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 비활성 상태를 말함.(이메일 인증을 완료하거나, 전화번호 인증을 하지 않았을 때 false가 됨.)
	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
