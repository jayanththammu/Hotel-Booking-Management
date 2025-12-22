package com.example.hotel.Mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.entitys.Hotel;
import com.example.hotel.models.HotelDto;
import com.example.hotel.models.RoomDto;
@Component
public class HotelMapper {
	public HotelDto CreateDto(Hotel hotel) {
		HotelDto hotelDto = new HotelDto()
						.setHotelName(hotel.getHotelName())
						.setContactNo(hotel.getContactNo())
						.setDescription(hotel.getDescription())
						.setEmail(hotel.getEmail())
						.setRating(hotel.getLocation())
						.setRating(hotel.getRating());
		if(hotel.getRooms() != null && !hotel.getRooms().isEmpty()) { 
           hotelDto.setRooms(new RoomMapper().getRoomDtos(hotel.getRooms()));
		}
		return hotelDto;
	}
}
