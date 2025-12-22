package com.example.hotel.service.impl;
 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel.dtos.AdminDto;
import com.example.hotel.entitys.Admins;
import com.example.hotel.repositorys.AdminRepository;
import com.example.hotel.service.admin.AdminAuth;

import jakarta.servlet.http.HttpSession;


@Service
public class AdminAuthImpl implements AdminAuth {
	
	private final AdminRepository adminRepo;
	private final PasswordEncoder encoder;
	
	public AdminAuthImpl(AdminRepository adminRepository,
			 			PasswordEncoder encoder) {
		this.adminRepo = adminRepository;
		this.encoder = encoder;
	}

	 @Override
	public String login(AdminDto adminDto,HttpSession session) {
		 
		Admins admin = adminRepo.findByAdminUsername(adminDto.getUserName());
		if(admin == null)
			throw new RuntimeException("Username Invalid");
		
		if(!encoder.matches(adminDto.getPassword(), admin.getPassword()))
			throw new RuntimeException("Password Invalid");
		
		session.setAttribute("LoggedInAdmin", admin);
		return "login Sucessfull";
	}
	 
	 @Override
	public String register(AdminDto adminDto) {
	 
		 if( adminRepo.existsByAdminUsername(adminDto.getUserName())) {
			 throw new RuntimeException("UserName Already Exists");
		 }
		 if(adminRepo.existsByAdminEmail(adminDto.getEmail())) {
			 throw new RuntimeException("Email Already Exists");
		 }
		 
		 
		 String hashedPassword = encoder.encode(adminDto.getPassword());
		 Admins admin = new Admins.Builder()
				 			.setAdminUsername(adminDto.getUserName())
				 			.setAdminEmail(adminDto.getEmail())
				 			.setAdminNumber(adminDto.getNumber())
				 			.setPassword(hashedPassword)
		 					.build();
		 
		 
		adminRepo.save(admin);
		return "Account created successfully";
	}
	 
	 @Override
	public String logOut(HttpSession session) {
		// TODO Auto-generated method stub
		 session.invalidate();
		return "Logout Successfull";
	}

	 

}
