package com.hau.ketnguyen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.dto.UserDTO;
import com.hau.ketnguyen.entity.RoleEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.exception.UserAlreadyExistException;
import com.hau.ketnguyen.repository.IRoleRepository;
import com.hau.ketnguyen.repository.IUserRepository;
import com.hau.ketnguyen.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity save(UserDTO userDto) throws UserAlreadyExistException {
		if (checkUserExist(userDto.getEmail())) {
			throw new UserAlreadyExistException("User already exists for this email");
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		updateUserRole(userEntity);
		return userRepository.save(userEntity);
	}

	public boolean checkUserExist(String mail) {
		return userRepository.findByEmail(mail) != null ? true : false;
	}

	public void updateUserRole(UserEntity userEntity) {
		RoleEntity roleEntity = roleRepository.findByCode("USER");
		userEntity.addUserRole(roleEntity);
	}
	
	public UserEntity getCurrentlyLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) return null;
		String email = authentication.getName();
		UserEntity userEntity = userRepository.findByEmail(email);
		return userEntity;
	}
}
