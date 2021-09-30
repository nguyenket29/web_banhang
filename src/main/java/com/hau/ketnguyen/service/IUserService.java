package com.hau.ketnguyen.service;

import com.hau.ketnguyen.dto.UserDTO;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.exception.UserAlreadyExistException;

public interface IUserService{
	UserEntity save(UserDTO userDto) throws UserAlreadyExistException;
}
