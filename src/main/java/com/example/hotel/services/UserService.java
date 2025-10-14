package com.example.hotel.services;

import java.util.List;

import com.example.hotel.models.HotelDto;
import com.example.hotel.models.UserLogDto;
import com.example.hotel.models.UserRegiDto;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	String register(UserRegiDto reg) throws Exception;
	String login(UserLogDto log,HttpSession session);
	List<HotelDto> viewAllHotels();
	String bookHotelById(Long hotelId,Long roomId,int noOfDays,HttpSession session) throws Exception;
	String cancelBookedHotelById(Long id);
	HotelDto searchHotelByName(String name);
}