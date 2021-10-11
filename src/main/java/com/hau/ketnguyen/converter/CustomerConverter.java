package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.CustomerDTO;
import com.hau.ketnguyen.entity.CustomerEntity;

@Component
public class CustomerConverter {
	public CustomerDTO toDto(CustomerEntity entity) {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setInfo(entity.getInformation());
		return dto;
	}
	
	public CustomerEntity toEntity(CustomerEntity entity, CustomerDTO dto) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setInformation(dto.getInfo());
		return entity;
	}
	
	public CustomerEntity toEntity(CustomerDTO dto) {
		CustomerEntity entity = new CustomerEntity();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setInformation(dto.getInfo());
		return entity;
	}
}
