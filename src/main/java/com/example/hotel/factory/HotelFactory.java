package com.example.hotel.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.models.HotelDto;

@Component
public class HotelFactory {
	private final RoomFactory roomFactory;
	
	public HotelFactory(RoomFactory roomFactory) {
		this.roomFactory = roomFactory;
	}
		
	public Hotel createHotelFromDto(HotelDto dto) {
		
		Hotel hotel = new Hotel.HotelBuilder()
				  .setHotelName(dto.getHotelName())
				  .setDescription(dto.getDescription())
				  .setEmail(dto.getEmail())
				  .setContactNo(dto.getContactNo())
				  .setLocation(dto.getLocation())
				  .setRating(dto.getRating())
				  
			.build();
		
		
		
		
		
		  if (dto.getRooms() != null && !dto.getRooms().isEmpty()) {
			  hotel.setRooms(roomFactory.createListRoomsFromDto(dto.getRooms(), hotel));
	        }

		
		return hotel;
				   
	}
}
