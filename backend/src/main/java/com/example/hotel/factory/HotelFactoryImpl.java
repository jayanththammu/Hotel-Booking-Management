package com.example.hotel.Factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;

@Component
public class HotelFactoryImpl implements HotelFactory{
	
	private final RoomFactory roomFactory;
	
	public HotelFactoryImpl(RoomFactory roomFactory) {
		this.roomFactory = roomFactory;
	}
	
	public Hotel getHotelFromDto(HotelDto hotelDto) {
		
		List<RoomDto> roomDtos = hotelDto.getRoomDto();
			
		List<Room> rooms = new ArrayList<>();
		
		 
		
		 Hotel hotel =  new Hotel.Builder()
									.setHotelName(hotelDto.getHotelName())
									.setHotelDescription(hotelDto.getHotelDescription())
									.setHotelRating(hotelDto.getHotelRating())
									
									 
									.build();
		 if(roomDtos != null) {
			 for(RoomDto dto:roomDtos) {
				 rooms.add(roomFactory.getRoomFromDto(dto, hotel));
			 }
			 hotel.setRooms(rooms);
		 }
		 
		return hotel; 
	}
}
