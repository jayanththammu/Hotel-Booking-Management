package com.example.hotel.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entitys.Admins;
 
@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {
	
	boolean existsByAdminUsername(String adminUsername);
	boolean existsByAdminEmail(String adminEmail);
	Admins findByAdminUsername(String adminUsername);
	
}
