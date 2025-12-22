package com.example.hotel.Factory;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.entitys.Hotel;

public interface HotelFactory {
	Hotel getHotelFromDto(HotelDto hotelDto);
	 
	
}
