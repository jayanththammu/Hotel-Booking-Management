package com.example.hotel.entitys;

 

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Bookings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(nullable = false)
	private LocalDate checkInDate;
	@Column(nullable = false)
	private LocalDate checkOutDate;
	
	@Column(nullable = false)
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private Users user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id",nullable = false)
	private Room room;
	
	@ManyToOne()
	@JoinColumn(name="hotel_id",nullable = false)
	private Hotel hotel;
	
	protected Bookings() {}

	private Bookings(Builder builder) {
		this.checkInDate = builder.checkinDate;
		this.checkOutDate = builder.checkoutDate;
		this.amount = builder.amount;
		this.user = builder.user;
		this.room = builder.room;
		this.hotel = builder.hotel;
	}
	
	@Getter
	@Setter
	@Accessors(chain = true)
	public static class Builder{
		private LocalDate checkinDate;
		private LocalDate checkoutDate;
		private Double amount;
		private Users user;
		private Room room;
		private Hotel hotel;
		
		public Builder() {
			
		}
		
		public Bookings build() {
			return new Bookings(this);
		}
	}

 
	
	
}
 