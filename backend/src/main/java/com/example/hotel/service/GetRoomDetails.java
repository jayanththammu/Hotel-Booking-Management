package com.example.hotel.service;

import java.util.List;

import com.example.hotel.dtos.RoomDto;

public interface GetRoomDetails {
	List<RoomDto> getRoomDetails(Long hotelId);
}
