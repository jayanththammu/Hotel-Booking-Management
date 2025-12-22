package com.example.hotel.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.HotelDto;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;

@Component
public class HotelMapperImpl implements HotelMapper {

	private final RoomMapper roomMapper;

	HotelMapperImpl(RoomMapper roomMapper) {

		this.roomMapper = roomMapper;
	}

	@Override
	public HotelDto getHotelDto(Hotel hotel) {

		List<RoomDto> roomdtos = roomMapper.getRoomDtos(hotel.getRooms());

		return HotelDto.builder()
				.hotelId(hotel.getHotelId())
				.hotelRating(hotel.getHotelRating())
				.hotelDescription(hotel.getHotelDescription())
				.hotelName(hotel.getHotelName())
				.roomDto(roomdtos)
				.build();
	}

	@Override
	public List<HotelDto> getHotelDtos(List<Hotel> hotels) {
 
		return hotels.stream().map(h -> getHotelDto(h)).toList();

	}
}
