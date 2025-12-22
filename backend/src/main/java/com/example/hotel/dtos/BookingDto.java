package com.example.hotel.dtos;

import java.time.LocalDate;

import com.example.hotel.dtointerfaces.BookingDtoInterface;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BookingDto implements BookingDtoInterface {
	
	private Long userId;
	private Long hotelId;
	private Long roomId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Double amount; 
	private String hotelName;
	private Long roomNumber;
	private String checkIn;
	private String checkOut;
	
 
}
