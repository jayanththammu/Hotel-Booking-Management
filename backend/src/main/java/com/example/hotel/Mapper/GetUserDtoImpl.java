package com.example.hotel.mapper;

import org.springframework.stereotype.Component;

import com.example.hotel.dtos.UserDto;
import com.example.hotel.entitys.Users;

@Component
public class GetUserDtoImpl implements GetUserDto {

	@Override
	public UserDto getUserDto(Users user) {
 
		return new UserDto()
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setNumber(user.getUserNumber())
				.setId(user.getUserId());
	}

}
