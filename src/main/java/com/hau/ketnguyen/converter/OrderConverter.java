package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.entity.OrderEntity;

@Component
public class OrderConverter {
	public OrderDTO toDto(OrderEntity entity) {
		OrderDTO dto = new OrderDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setStatus(true);
		dto.setAmount(entity.getAmount());
		dto.setCustomerId(entity.getCustomer().getId());
		return dto;
	}
	
	public OrderEntity toEntity(OrderEntity entity, OrderDTO dto) {
		entity.setStatus(true);
		entity.setAmount(dto.getAmount());
		return entity;
	}
	
	public OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		entity.setStatus(true);
		entity.setAmount(dto.getAmount());
		return entity;
	}
}
