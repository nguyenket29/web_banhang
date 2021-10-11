package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.entity.CartItemEntity;

@Component
public class CartItemConverter {
	public CartItemDTO toDto(CartItemEntity entity) {
		CartItemDTO dto = new CartItemDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setProductId(entity.getProduct().getId());
		dto.setName(entity.getProduct().getName());
		dto.setPrice(entity.getProduct().getPrice());
		dto.setThumbnail(entity.getProduct().getThumbnail());
		dto.setTotal(entity.getTotal());
		dto.setQuantity(entity.getQuantity());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}
	
	public CartItemEntity toEntity(CartItemDTO dto,CartItemEntity entity) {
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
	
	public CartItemEntity toEntity(CartItemDTO dto) {
		CartItemEntity entity = new CartItemEntity();
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
}
