package com.hau.ketnguyen.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.entity.ProductEntity;

public interface IProductService {
	Page<ProductDTO> getAll(int pageNumber,int size);

	List<ProductDTO> findAll();

	ProductDTO findById(long id);
	
	ProductDTO findOneById(Long id);
	
	void delete(long id);

	int getTotalItems();

	Page<ProductDTO> getProductByCategoryId(int page, long id, int size);

	Page<ProductEntity> getProductSort(int pageNumber, String sortField, String sortDir);
	
	ProductDTO saveOrUpdate(ProductDTO productDTO);
}
