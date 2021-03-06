package com.hau.ketnguyen.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.ProductConverter;
import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.CategoryEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.repository.ICartItemRepository;
import com.hau.ketnguyen.repository.ICategoryRepository;
import com.hau.ketnguyen.repository.IProductRepository;
import com.hau.ketnguyen.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private ICartItemRepository cartRepository;

	@Override
	public Page<ProductDTO> getAll(int pageNumber,int size) {
		Pageable pageable = PageRequest.of(pageNumber - 1, size);
		Page<ProductEntity> pageEntity = productRepository.findAll(pageable);
		Page<ProductDTO> pageDto = pageEntity.map(new Function<ProductEntity, ProductDTO>() {
			@Override
			public ProductDTO apply(ProductEntity entity) {
				return productConverter.toDTO(entity);
			}
		});
		return pageDto;
	}

	@Override
	public ProductDTO findById(long id) {
		return productConverter.toDTO(productRepository.findById(id).get());
	}

	@Override
	public int getTotalItems() {
		return (int) productRepository.count();
	}

	@Override
	public List<ProductDTO> findAll() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		List<ProductEntity> listEntity = productRepository.findAll();
		for (ProductEntity item : listEntity) {
			list.add(productConverter.toDTO(item));
		}
		return list;
	}

	@Override
	public Page<ProductDTO> getProductByCategoryId(int page, long id, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<ProductEntity> pageEntity = productRepository.findAllByCategoryId(id, pageable);
		Page<ProductDTO> pageDto = pageEntity.map(new Function<ProductEntity, ProductDTO>() {
			@Override
			public ProductDTO apply(ProductEntity entity) {
				return productConverter.toDTO(entity);
			}
		});
		return pageDto;
	}

	@Override
	public Page<ProductEntity> getProductSort(int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public ProductDTO saveOrUpdate(ProductDTO productDTO) {
		ProductEntity productEntity = new ProductEntity();
		
		if(productDTO.getId() != null) {
			ProductEntity oldProductEntity = productRepository.findById(productDTO.getId()).get();
			productEntity = productConverter.toEntity(productDTO, oldProductEntity);
		}else {
			productEntity = productConverter.toEntity(productDTO);
		}
		
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());
		productEntity.setCategory(categoryEntity);
		productEntity = productRepository.save(productEntity);
		return productConverter.toDTO(productEntity);
	}

	@Override
	@Transactional
	public ProductDTO findOneById(Long id) {
		return productConverter.toDTO(productRepository.findById(id).get());
	}

	@Override
	@Transactional
	public void delete(long id) {
		CartItemEntity cart = cartRepository.findByProductId(id);
		if(cart != null) {
			cartRepository.delete(cart);
		}
		productRepository.deleteById(id);
	}
}
