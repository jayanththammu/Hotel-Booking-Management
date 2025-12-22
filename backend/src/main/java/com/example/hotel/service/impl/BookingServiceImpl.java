package com.example.hotel.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.Factory.BookingFactory;
import com.example.hotel.dtos.BookingDto;
import com.example.hotel.entitys.Bookings;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.entitys.Users;
import com.example.hotel.mapper.Mapper;
import com.example.hotel.repositorys.BookingsRepository;
import com.example.hotel.repositorys.HotelRepository;
import com.example.hotel.repositorys.RoomRepository;
import com.example.hotel.repositorys.UsersRepository;
import com.example.hotel.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingsRepository bookingRepo;
	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepo;
	private final UsersRepository usersRepository;
	private final BookingFactory bookingFactory;
	private final Mapper mapper;

	public BookingServiceImpl(BookingsRepository bookingRepo, HotelRepository hotelRepository, RoomRepository roomRepo,
			UsersRepository usersRepository, BookingFactory bookingFactory, Mapper mapper) {
		this.bookingRepo = bookingRepo;
		this.hotelRepository = hotelRepository;
		this.roomRepo = roomRepo;
		this.usersRepository = usersRepository;
		this.bookingFactory = bookingFactory;
		this.mapper = mapper;
	}

	@Override
	public String book(BookingDto bookingDto) {
		// TODO Auto-generated method stub
		
		if (bookingDto.getCheckInDate().equals(bookingDto.getCheckInDate())) {
		    throw new IllegalArgumentException("Check-in and check-out cannot be the same date");
		}


		Users user = usersRepository.findById(bookingDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Hotel hotel = hotelRepository.findById(bookingDto.getHotelId())
				.orElseThrow(() -> new RuntimeException("Hotel Not Found"));

		Room room = roomRepo.findById(bookingDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room Not Found"));

		LocalDate checkInDate = bookingDto.getCheckInDate();

		LocalDate checkOut = bookingDto.getCheckOutDate();

		List<Bookings> bookings = bookingRepo
				.findByHotelAndRoomAndCheckInDateIsLessThanAndCheckOutDateIsGreaterThanEqual(hotel, room, checkOut,
						checkInDate);

		if (!bookings.isEmpty()) {
			throw new RuntimeException("No Booking Available");
		}

		Bookings booking = bookingFactory.getBookingFromDto(user, hotel, room, checkInDate, checkOut,
				bookingDto.getAmount());

		bookingRepo.save(booking);
		return "Booking Successfull";
	}

	@Override
	public List<BookingDto> getBookings(Long userId) {

		List<Bookings> bookings = bookingRepo.findByUser(userId);

		return mapper.getBookingDtos(bookings);
	}

}
