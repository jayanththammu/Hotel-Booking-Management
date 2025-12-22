package com.example.hotel.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Admins {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	
	@Column(nullable = false)
	private String adminUsername;

	 
	@Column(nullable = false)
	private String adminNumber;
	
	@Column(nullable = false)
	private String adminEmail;
	
	@Column(nullable = false)
	private String password;
	
	protected Admins() {}
	private Admins(Builder builder) {
		this.adminUsername = builder.adminUsername;
		this.password = builder.password;
		this.adminEmail = builder.adminEmail;
		this.adminNumber = builder.adminNumber;
	 }
	
	@Getter
	@Setter
	@Accessors(chain = true)
	public static class Builder{
		private String adminUsername;
		private String adminNumber;
		private String adminEmail;
		private String password;
		
		public Builder() {
			
			
		}
		
		public Admins build() {
			return new Admins(this);
		}
		
	}
}

/*
create table Admins(
admin_id bigint primary key auto_increment,
admin_name varchar(50) not null,
admin_number varchar(10) not null unique,
admin_email varchar(50) not null unique
);  
*/