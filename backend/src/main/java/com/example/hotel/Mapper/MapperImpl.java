package com.example.hotel.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.BookingDto;
import com.example.hotel.entitys.Bookings;

@Component
public class MapperImpl implements Mapper {

	@Override
	public BookingDto getBookingDto(Bookings booking) {

		BookingDto b = new BookingDto()
				.setUserId(booking.getBookingId())
				.setAmount(booking.getAmount())
				.setCheckInDate(booking.getCheckInDate())
				.setCheckOutDate(booking.getCheckOutDate())
				.setHotelName(booking.getHotel().getHotelName())
				.setRoomId(booking.getRoom().getRoomId())
				.setRoomNumber(booking.getRoom().getRoomNumber());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		String checkIn = b.getCheckInDate().format(formatter);
		String checkOut = b.getCheckOutDate().format(formatter);

		b.setCheckIn(checkIn);
		b.setCheckOut(checkOut);

		return b;
	}

	@Override
	public List<BookingDto> getBookingDtos(List<Bookings> bookings) {
		// TODO Auto-generated method stub

		return bookings.stream().map(b -> getBookingDto(b)).toList();
	}
}
