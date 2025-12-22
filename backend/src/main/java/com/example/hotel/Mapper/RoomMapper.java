package com.example.hotel.mapper;

import java.util.List;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Room;

public interface RoomMapper {
	RoomDto getRoomDto(Room room);
	List<RoomDto> getRoomDtos(List<Room> rooms);
}
