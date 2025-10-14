package com.example.hotel.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain=true)
public class Bookings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    @ManyToOne
	    @JoinColumn(name = "user_id") // foreign key to User
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "hotel_id") // foreign key to Hotel
	    private Hotel hotel;

	    @ManyToOne
	    @JoinColumn(name = "room_id") // foreign key to Room
	    private Room room;
	 
	private LocalDateTime endTime;
	private LocalDateTime startTime;
	private int amount;
	
}
