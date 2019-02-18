package com.mobin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobin.mapper.SellerMapper;
import com.mobin.model.SellerVO;
import com.mobin.service.UserService;


@Service(value = "userService")
@Transactional(rollbackFor = { Exception.class })
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private SellerMapper sellerMapper;
	
	
	

	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		SellerVO user = sellerMapper.selectSellerInfoById(id);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPasswd(), getAuthority());
	}

	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER"));
	}
}
