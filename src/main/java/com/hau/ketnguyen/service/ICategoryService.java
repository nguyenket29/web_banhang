package com.hau.ketnguyen.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.CategoriesDTO;

public interface ICategoryService {
	List<CategoriesDTO> findAll();
	Map<String, String> listAll();
	Page<CategoriesDTO> getAll(int page, int size);
}
