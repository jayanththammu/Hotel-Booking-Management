package com.example.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entitys.Bookings;

@Repository
public interface BookingRepo extends JpaRepository<Bookings, Long> {

}
