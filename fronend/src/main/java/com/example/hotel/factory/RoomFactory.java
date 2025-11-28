package com.example.hotel.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.models.RoomDto;
@Component
public class RoomFactory {
	public Room createRoomFromDto(RoomDto roomDto,Hotel Hotel) {
		Room room =new Room.RoomBuilder()
						.setHotel(Hotel)
						.setPricePerNight(roomDto.getPricePerNight())
						.setRoomNo(roomDto.getRoomNo())
						.setRoomShares(roomDto.getRoomShares())
						.setRoomType(roomDto.getRoomType())
						.setStatus(roomDto.getStatus())
			.build();
		
		return room;
	}
	public List<Room> createListRoomsFromDto(List<RoomDto> roomDtos,Hotel hotel){
		
		List<Room> rooms = roomDtos.stream().map( r -> createRoomFromDto(r, hotel)).toList();
		return rooms;
	}
}
