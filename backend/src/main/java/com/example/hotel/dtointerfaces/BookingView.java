package com.example.hotel.dtointerfaces;

import java.time.LocalDate;

public interface BookingView {
 public Long getBookingId();
 public String getHotelName();
 public Long getRoomNo();
 public double getAmount();
 public LocalDate getCheckInDate();
 public LocalDate getCheckOutDate();
}
