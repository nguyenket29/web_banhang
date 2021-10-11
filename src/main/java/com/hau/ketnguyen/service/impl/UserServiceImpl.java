package com.hau.ketnguyen.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.UserConverter;
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
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDTO save(UserDTO userDto) throws UserAlreadyExistException {
		if (checkUserExist(userDto.getEmail())) {
			throw new UserAlreadyExistException("User already exists for this email");
		}
		UserEntity userEntity = new UserEntity();
		
		if(userDto.getId() != null) {
			UserEntity oldUser = userRepository.findById(userDto.getId()).get();
			userEntity = userConverter.toEntity(userDto, oldUser);
		}else {
			userEntity = userConverter.toEntity(userDto);
		}
		
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		updateUserRole(userEntity);
		return userConverter.toDTO(userRepository.save(userEntity));
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

	@Override
	public Page<UserDTO> getUser(int curentPage, int size) {
		Pageable pageable = PageRequest.of(curentPage - 1, size);
		Page<UserEntity> pageEntity = userRepository.findAll(pageable);
		Page<UserDTO> pageDto = pageEntity.map(new Function<UserEntity, UserDTO>() {
			@Override
			public UserDTO apply(UserEntity entity) {
				return userConverter.toDTO(entity);
			}
			
		});
		return pageDto;
	}
}
