package com.example.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entitys.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
