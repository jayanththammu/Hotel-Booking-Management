package com.example.hotel.dtointerfaces;

import java.util.List;

import com.example.hotel.dtos.RoomDto;

public interface HotelDetails {
	  Long getHotelId();
	  String getHotelName();
	  String getHotelDescription();
	  String getHotelRating();
	  List<RoomDto> getRoomDto();
}
