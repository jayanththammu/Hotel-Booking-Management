package com.example.hotel.services;

import java.util.List;

import com.example.hotel.models.AdminLoginDto;
import com.example.hotel.models.AdminRegisterDto;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.RoomDto;

import jakarta.servlet.http.HttpSession;

public interface AdminService {
	
	void register(AdminRegisterDto reg);
	String login(AdminLoginDto body, HttpSession session);
	Long addHotel(HotelDto hotel);
	Long updateHotel(Long id,HotelDto hotel);
	HotelDto getHotel(Long id);
	Long deleteHotel(Long id);
	List<HotelDto> getAllHotels();
	String updateRoom(Long hotelId,Long roomId,RoomDto roomDto);
	
}