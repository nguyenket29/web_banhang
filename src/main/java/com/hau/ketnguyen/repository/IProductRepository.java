package com.hau.ketnguyen.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hau.ketnguyen.entity.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long>{
	Page<ProductEntity> findAllByCategoryId(Long id, Pageable pageable);
}
