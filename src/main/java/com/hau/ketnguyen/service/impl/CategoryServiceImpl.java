package com.hau.ketnguyen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.CategoryConverter;
import com.hau.ketnguyen.dto.CategoriesDTO;
import com.hau.ketnguyen.entity.CategoryEntity;
import com.hau.ketnguyen.repository.ICategoryRepository;
import com.hau.ketnguyen.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public List<CategoriesDTO> findAll() {
		List<CategoriesDTO> list = new ArrayList<CategoriesDTO>();
		List<CategoryEntity> listEntity = categoryRepository.findAll();
		for (CategoryEntity item : listEntity) {
			list.add(categoryConverter.toDTO(item));
		}
		return list;
	}

	@Override
	public Map<String, String> listAll() {
		Map<String, String> map = new HashMap<String, String>();
		List<CategoryEntity> listEntity = categoryRepository.findAll();
		for (CategoryEntity item : listEntity) {
			map.put(item.getCode(), item.getName());
		}
		return map;
	}

	@Override
	public Page<CategoriesDTO> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CategoryEntity> paegEntity = categoryRepository.findAll(pageable);
		Page<CategoriesDTO> pageDto = paegEntity.map(new Function<CategoryEntity,CategoriesDTO>() {
			@Override
			public CategoriesDTO apply(CategoryEntity entity) {
				return categoryConverter.toDTO(entity);
			}
		});
		return pageDto;
	}
	
	
}
