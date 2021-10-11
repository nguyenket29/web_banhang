package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.OrderDetailDTO;
import com.hau.ketnguyen.entity.OrderDetailEntity;

@Component
public class OrderDetailConverter {
	public OrderDetailDTO toDto(OrderDetailEntity entity) {
		OrderDetailDTO dto = new OrderDetailDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		dto.setProductId(entity.getProduct().getId());
		dto.setOrderId(entity.getOrder().getId());
		return dto;
	}
	
	public OrderDetailEntity toEntity(OrderDetailEntity entity, OrderDetailDTO dto) {
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
	
	public OrderDetailEntity toEntity(OrderDetailDTO dto) {
		OrderDetailEntity entity = new OrderDetailEntity();
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
}
