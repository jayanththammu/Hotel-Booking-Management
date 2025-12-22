package com.example.hotel.service.impl;
 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel.dtos.UserDto;
import com.example.hotel.entitys.Users;
import com.example.hotel.mapper.GetUserDto;
import com.example.hotel.repositorys.UsersRepository;
import com.example.hotel.service.user.UserAuth;

import jakarta.servlet.http.HttpSession;

@Service
public class UserAuthImpl implements UserAuth {
	
	private  final UsersRepository userRepo;
	private final PasswordEncoder encoder;
	private final GetUserDto getUserDto;
 
	public UserAuthImpl(UsersRepository userRepo,
			 			PasswordEncoder encoder,
			 			 GetUserDto getUserDto) {
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.getUserDto = getUserDto; 
	}

	 @Override
	public UserDto login(UserDto userDto,HttpSession session) {
		 
		Users user = userRepo.findByUserName(userDto.getUserName());
		if(user == null)
			throw new RuntimeException("Username Invalid");
		
		if(!encoder.matches(userDto.getPassword(), user.getPassword()))
			throw new RuntimeException("Password Invalid");
		
		session.setAttribute("LoggedInUser", user);
		
		
		return getUserDto.getUserDto(user);
	}
	 
	 @Override
	public String register(UserDto userDto) {
	 
		 if( userRepo.existsByUserName(userDto.getUserName())) {
			 throw new RuntimeException("UserName Already Exists");
		 }
		 if(userRepo.existsByUserEmail(userDto.getEmail())) {
			 throw new RuntimeException("Email Already Exists");
		 }
		 
		 
		 String hashedPassword = encoder.encode(userDto.getPassword());
		 Users user = new Users.Builder()
				 			.setUserName(userDto.getUserName())
				 			.setUserEmail(userDto.getEmail())
				 			.setUserNumber(userDto.getNumber())
				 			.setPassword(hashedPassword)
				 			.setFirstName(userDto.getFirstName())
				 			.setLastName(userDto.getLastName())
		 					.build();
		 
		 
		userRepo.save(user);
		return "Account created successfully";
	}
	 
	 @Override
	public String logout(HttpSession session) {
		// TODO Auto-generated method stub
		 session.invalidate();
		return "Logout Successfull";
	}

}
