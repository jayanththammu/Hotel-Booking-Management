package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.repositorys.RoomRepository;
import com.example.hotel.service.GetRoomDetails;

@Component
public class GetRoomDetailsImpl implements GetRoomDetails {
	
	private final RoomRepository roomRepo;
	
	public GetRoomDetailsImpl(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}

	@Override
	public List<RoomDto> getRoomDetails(Long hotelId) {
		return roomRepo.findRoomDetailsByHotelId(hotelId);
	}

}
