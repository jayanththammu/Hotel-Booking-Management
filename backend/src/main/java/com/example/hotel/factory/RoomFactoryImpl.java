package com.example.hotel.Factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;

@Component
public class RoomFactoryImpl implements RoomFactory{
	
	public Room getRoomFromDto(RoomDto roomDto,Hotel hotel) {
		
		return new Room.Builder()
					.setRoomNumber(roomDto.getRoomNumber())
					.setRoomType(roomDto.getRoomType())
					.setNoOfShares(roomDto.getNoOfShares())
					.setRoomPrice(roomDto.getRoomPrice())
					.setHotel(hotel)
					.build();
	}
	
	@Override
	public List<Room> getRoomsFromDtos(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}
}
