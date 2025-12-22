package com.example.hotel.mapper;

import java.util.List;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.entitys.Hotel;

public interface HotelMapper {
	HotelDto getHotelDto(Hotel hotel);
	List<HotelDto> getHotelDtos(List<Hotel> hotels);
}
