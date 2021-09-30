package com.hau.ketnguyen.converter;

import org.springframework.stereotype.Component;

import com.hau.ketnguyen.dto.CategoriesDTO;
import com.hau.ketnguyen.entity.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoriesDTO toDTO(CategoryEntity entity) {
		CategoriesDTO dto = new CategoriesDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}
	
	public CategoryEntity toEntity(CategoriesDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}
}
