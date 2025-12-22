package com.example.hotel.entitys;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;
	
	@Column(nullable = false)
	private String hotelName;
	
	private String hotelDescription;
	
	private String hotelRating;
	
	@OneToMany(mappedBy = "hotel")
	private List<Bookings> bookings;
	
	@OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
	private List<Room> rooms;
	
	protected Hotel() {}
	
	private Hotel(Builder builder) {
		
		this.hotelName = builder.hotelName;
		this.hotelDescription = builder.hotelDescription;
		this.hotelRating = builder.hotelRating;
		this.rooms = builder.rooms;
	}
	@Getter
	@Setter
	@Accessors(chain = true)
	public static class Builder {

		private String hotelName;
		private String hotelDescription;
		private String hotelRating;
		private List<Room> rooms;
		
		public Builder() {}
		
		public Hotel build() {
			return new Hotel(this);
		}
	}
	
}
/*
 * create table Hotel(
hotel_id bigint primary key auto_increment,
hotel_name varchar(20) not null,
hotel_description text,
hotel_rating varchar(10)
 
);

*/
