package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.entity.ProductEntity;

@Component
public class ProductConverter {
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setDescription(entity.getDescription());
		dto.setCategoryCode(entity.getCategory().getCode());
		dto.setCategoryId(entity.getCategory().getId());
		dto.setThumbnail(entity.getThumbnail());
		dto.setQuantity(entity.getQuantity());
		return dto;
	}
	
	public ProductEntity toEntity(ProductDTO dto,ProductEntity entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		entity.setThumbnail(dto.getThumbnail());
		return entity;
	}
	
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setThumbnail(dto.getThumbnail());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
}
