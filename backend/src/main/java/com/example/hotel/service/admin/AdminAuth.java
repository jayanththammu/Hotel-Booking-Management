package com.example.hotel.service.admin;

import com.example.hotel.dtos.AdminDto;

import jakarta.servlet.http.HttpSession;

public interface AdminAuth {
	
	String login(AdminDto adminDto,HttpSession session);
	String register(  AdminDto adminDto);
	String logOut(HttpSession session);
	
}
