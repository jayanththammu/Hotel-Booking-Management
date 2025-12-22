package com.example.hotel.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Room;

@Component
public class RoomMapperImpl implements RoomMapper {

	@Override
	public RoomDto getRoomDto(Room room) {
		// TODO Auto-generated method stub
		return RoomDto.builder()
				.roomId(room.getRoomId())
				.roomNumber(room.getRoomNumber())
				.roomType(room.getRoomType())
				.roomPrice(room.getRoomPrice())
				.noOfShares(room.getNoOfShares())
				.build();
	}

	@Override
	public List<RoomDto> getRoomDtos(List<Room> rooms) {
		// TODO Auto-generated method stub
		return rooms.stream().map(r -> getRoomDto(r)).toList();
	}
}
