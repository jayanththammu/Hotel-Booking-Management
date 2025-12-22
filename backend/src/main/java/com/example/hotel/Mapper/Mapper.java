package com.example.hotel.mapper;

import java.util.List;

import com.example.hotel.dtos.BookingDto;
import com.example.hotel.entitys.Bookings;

public interface Mapper {
 List<BookingDto> getBookingDtos(List<Bookings> bookings);
 BookingDto getBookingDto(Bookings booking);
}
