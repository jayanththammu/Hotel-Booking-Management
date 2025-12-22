package com.example.hotel.entitys;

import java.util.List;

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
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String firstName;
	
	private String lastName;
	@Column(nullable = false)
	private String userNumber;
	
	@Column(nullable = false)
	private String userEmail;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Bookings> bookings;
	
	protected Users() {
		
	}
	private Users(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.userName = builder.userName;
		this.userNumber = builder.userNumber;
		this.userEmail = builder.userEmail;
		this.bookings = builder.bookings;
		this.password = builder.password;
	}
	@Getter
	@Setter
	@Accessors(chain = true)
	public static class Builder{
		private String userName;
		private String firstName;		
		private String lastName;
		private String userNumber;
		private String userEmail;
		private String password;
		private List<Bookings> bookings;
		
		public Builder() {
			
		}
		
		public Users build() {
			return new Users(this);
		}
		
	}
}
/*
 
 * create table users(
user_id bigint primary key auto_increment,
user_name varchar(50) not null,
user_number varchar(10) not null,
user_email varchar(50) not null unique
);
 
 
*/