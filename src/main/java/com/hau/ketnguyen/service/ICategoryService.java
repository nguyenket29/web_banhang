package com.hau.ketnguyen.service;

import java.util.List;
import java.util.Map;

import com.hau.ketnguyen.dto.CategoriesDTO;

public interface ICategoryService {
	List<CategoriesDTO> findAll();
	Map<String, String> listAll();
}
