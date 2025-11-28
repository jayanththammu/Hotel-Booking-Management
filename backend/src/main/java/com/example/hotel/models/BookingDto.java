package com.example.hotel.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookingDto {

	private Long hotelId;
	private String roomType;
	private LocalDate startdate;
	private LocalDate endDate;
}
