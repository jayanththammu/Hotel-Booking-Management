package com.example.hotel.Factory;

import java.util.List;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;

public interface RoomFactory {
	Room getRoomFromDto(RoomDto roomDto,Hotel hotel);
	List<Room> getRoomsFromDtos(Hotel hotel);
	 
	
}
