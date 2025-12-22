package com.example.hotel.Factory;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.hotel.entitys.Bookings;
import com.example.hotel.entitys.Hotel;
import com.example.hotel.entitys.Room;
import com.example.hotel.entitys.Users;

@Component
public class BookingFactImpl implements BookingFactory {

	 @Override
	public Bookings getBookingFromDto(Users user, Hotel hotel, Room room, LocalDate checkIn, LocalDate checkOut,
			Double amout) {
		// TODO Auto-generated method stub
		return new Bookings.Builder()
					.setUser(user)
					.setHotel(hotel)
					.setRoom(room)
					.setCheckinDate(checkIn)
					.setCheckoutDate(checkOut)
					.setAmount(amout)
				.build();
	}

}
