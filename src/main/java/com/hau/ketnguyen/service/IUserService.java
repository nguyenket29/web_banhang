package com.hau.ketnguyen.service;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.UserDTO;
import com.hau.ketnguyen.exception.UserAlreadyExistException;

public interface IUserService{
	UserDTO save(UserDTO userDto) throws UserAlreadyExistException;
	UserDTO adminSave(UserDTO userDto) throws UserAlreadyExistException;
	Page<UserDTO> getUser(int curentPage, int size);
	UserDTO findOneById(Long id);
	void delete(long id);
}
