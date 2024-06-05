package com.expense.tracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.User.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String Email);

}
