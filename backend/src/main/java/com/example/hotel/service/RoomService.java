package com.example.hotel.service;

import java.util.List;

import com.example.hotel.dtos.RoomDto;

public interface RoomService {
	String addRoom(Long hotelId,RoomDto room);
	String updateRoom(Long hotelId,Long roomId,RoomDto room);
	String delRoom(Long hotelId,Long roomNo);
	RoomDto getRoom(Long hotelId,Long roomNO);
	List<RoomDto> getAllRooms(Long hotelId);
	
}
