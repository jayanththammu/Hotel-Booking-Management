package com.example.hotel.mapper;

import com.example.hotel.dtos.UserDto;
import com.example.hotel.entitys.Users;

public interface GetUserDto {

	UserDto getUserDto(Users user);
}
