package com.example.hotel.entitys;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String hotelName;
	private String location;
	private String description;
	private String rating;
	private String email;
	private String contactNo;
	
	 protected Hotel() {}
	
	 
	@OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
	private List<Room> rooms;
	
	private Hotel(HotelBuilder builder) {
	 
		this.hotelName = builder.hotelName;
		this.location = builder.location;
		this.description = builder.description;
		this.rating = builder.rating;
		this.email = builder.email;
		this.contactNo = builder.contactNo;
		
	}
	 
	
	@Data
	@Accessors(chain = true)
	public static class HotelBuilder{
		private Long id;
		private String hotelName;
		private String location;
		private String description;
		private String rating;
		private String email;
		private String contactNo;
		private List<Room> rooms;
		
		public Hotel build() {
			return new Hotel(this);
		}
		
	
			
	}
	
	 public static HotelBuilder from(Hotel hotel) {
	        return new HotelBuilder()
	                .setHotelName(hotel.getHotelName())
	                .setDescription(hotel.getDescription())
	                .setEmail(hotel.getEmail())
	                .setContactNo(hotel.getContactNo())
	                .setLocation(hotel.getLocation())
	                .setRating(hotel.getRating())
	                .setRooms(hotel.getRooms());
	    }
	

	}
	 
	
	

