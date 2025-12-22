package com.example.hotel.Factory;

import java.time.LocalDate;

import com.example.hotel.entitys.Bookings;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.entitys.Users;

public interface BookingFactory {
	Bookings getBookingFromDto(Users user,Hotel hotel,Room room,LocalDate checkIn,LocalDate checkOut,Double amout);
}
