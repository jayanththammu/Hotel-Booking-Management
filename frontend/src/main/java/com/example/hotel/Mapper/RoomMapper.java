package com.example.hotel.Mapper;

import java.util.List;

import com.example.hotel.entitys.Room;
import com.example.hotel.models.RoomDto;

public class RoomMapper {
	public RoomDto createRoomDto(Room room) {
		RoomDto roomDto = new RoomDto()
				.setRoomNo(room.getRoomNo())
				.setPricePerNight(room.getPricePerNight())
				.setRoomShares(room.getRoomShares())
				.setRoomType(room.getRoomType())
				.setStatus(room.getStatus());
	return roomDto;
	}
	public List<RoomDto> getRoomDtos(List<Room> rooms){
		List<RoomDto> roomDtos = rooms.stream().map(r -> createRoomDto(r)).toList();
		
		return roomDtos;
	}
}
