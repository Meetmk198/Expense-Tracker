package com.expense.tracker.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.expense.tracker.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

	private final UserRepo userRepo = null;
	@Override
	public UserDetails loadUserByUsername(String username){
		return userRepo.findByEmail(username).orElseThrow();
	}

}
