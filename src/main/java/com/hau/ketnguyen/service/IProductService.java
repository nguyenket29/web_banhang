package com.hau.ketnguyen.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.entity.ProductEntity;

public interface IProductService {
	Page<ProductEntity> getAll(int pageNumber,int size);

	List<ProductDTO> findAll();

	ProductEntity findById(long id);
	
	ProductDTO findOneById(Long id);

	int getTotalItems();

	Page<ProductEntity> getProductByCategoryId(int page, long id);

	Page<ProductEntity> getProductSort(int pageNumber, String sortField, String sortDir);
	
	ProductDTO saveOrUpdate(ProductDTO productDTO);
}
