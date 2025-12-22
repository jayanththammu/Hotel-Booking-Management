package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.Factory.RoomFactory;
import com.example.hotel.dtos.RoomDto;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.mapper.RoomMapper;
import com.example.hotel.repositorys.HotelRepository;
import com.example.hotel.repositorys.RoomRepository;
import com.example.hotel.service.RoomService;

import jakarta.transaction.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

	private final RoomMapper roomMapper;
	private final HotelRepository hotelRepository;
	private final RoomFactory roomFactory;
	private final RoomRepository roomRepository;

	public RoomServiceImpl(RoomMapper roomMapper, HotelRepository hotelRepository, RoomFactory roomFactory,
			RoomRepository roomRepository) {
		this.roomMapper = roomMapper;
		this.hotelRepository = hotelRepository;
		this.roomFactory = roomFactory;
		this.roomRepository = roomRepository;
	}

	@Transactional
	@Override
	public String addRoom(Long hotelId, RoomDto roomdto) {
		System.out.println("Hotel ID"+hotelId);
		
		Hotel hotel = getHotel(hotelId);
		if (roomRepository.existsByRoomNumberAndHotel(roomdto.getRoomNumber(),hotel))
			throw new RuntimeException("Duplicate Data Room Already Exists");
		Room room = roomFactory.getRoomFromDto(roomdto, hotel);
		hotel.getRooms().add(room);

		return "Room Added Successfuly";
	}

	@Transactional
	@Override
	public String updateRoom(Long hotelId, Long roomId, RoomDto roomDto) {

		Hotel hotel = getHotel(hotelId);
		Room room = roomRepository.findByRoomIdAndHotel(roomId, hotel);
		if (room == null)
			throw new RuntimeException();
		updateRoomMethod(room, roomDto);

		roomRepository.save(room);

		return "Room Updated";
	}

	private void updateRoomMethod(Room room, RoomDto roomDto) {
		if (roomDto.getNoOfShares() != null)
			room.setNoOfShares(roomDto.getNoOfShares());
		if (roomDto.getRoomPrice() != null)
			room.setRoomPrice(roomDto.getRoomPrice());
		if (roomDto.getRoomType() != null)
			room.setRoomType(roomDto.getRoomType());

	}
	
	@Transactional
	@Override
	public String delRoom(Long hotelId, Long roomNo) {
		// TODO Auto-generated method stub
		Hotel hotel = getHotel(hotelId);
		roomRepository.deleteByRoomNumberAndHotel(roomNo, hotel);
		return "Room Deleted";
	}

	@Override
	public RoomDto getRoom(Long hotelId, Long roomNo) {
		Hotel hotel = getHotel(hotelId);
		Room room = roomRepository.findByRoomNumberAndHotel(roomNo, hotel);
		return roomMapper.getRoomDto(room);
	}

	@Override
	public List<RoomDto> getAllRooms(Long hotelId) {
		Hotel hotel = getHotel(hotelId);
		List<Room> rooms = roomRepository.findByHotel(hotel);
		return roomMapper.getRoomDtos(rooms);
	}

	private Hotel getHotel(Long hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
	}

}
