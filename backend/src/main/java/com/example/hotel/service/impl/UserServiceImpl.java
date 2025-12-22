package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.UserDto;
import com.example.hotel.service.GetHotelInterface;
import com.example.hotel.service.GetRoomDetails;
import com.example.hotel.service.UserService;
import com.example.hotel.service.user.UserAuth;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService   {
  private final UserAuth userAuth;
  private final GetHotelInterface getHotels;
  private final GetRoomDetails getRoomDetails;
  
  
  public UserServiceImpl(UserAuth userAuth, GetHotelInterface getHotels,GetRoomDetails getRoomDetails ){
	  this.userAuth = userAuth;
	  this.getHotels = getHotels;
	  this.getRoomDetails = getRoomDetails;
	 
  }
  
  
  @Override
	public UserDto login(UserDto userDto,HttpSession session) {
	 
	  try {
		  return userAuth.login(userDto,session);
		
	} catch (Exception e) {
	}
	 throw new RuntimeException("Invalid credentials");		
	}
  @Override
	public String register(UserDto userDto) {
		// TODO Auto-generated method stub
		return userAuth.register(userDto);
	}
  
  @Override
	public String logout(HttpSession session) {
		// TODO Auto-generated method stub
		return userAuth.logout(session);
	}
//  
  @Override
	public List<HotelDto> getHotels() {
		// TODO Auto-generated method stub
		return getHotels.getHotels();
	}
 
}
