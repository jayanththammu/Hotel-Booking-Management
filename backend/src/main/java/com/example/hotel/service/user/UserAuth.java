package com.example.hotel.service.user;

import com.example.hotel.dtos.UserDto;

import jakarta.servlet.http.HttpSession;
 
public interface UserAuth {
	
	UserDto login(UserDto userDto,HttpSession session);
	String register(UserDto userDto);
	String logout(HttpSession session);
}
