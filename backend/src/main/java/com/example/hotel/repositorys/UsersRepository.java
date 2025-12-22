package com.example.hotel.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entitys.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
 boolean existsByUserName(String userName);
 boolean existsByUserEmail(String userEmail);
 Users findByUserName(String userName);
}
