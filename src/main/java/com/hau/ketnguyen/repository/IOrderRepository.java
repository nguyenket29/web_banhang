package com.hau.ketnguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hau.ketnguyen.entity.OrderEntity;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query(value = "SELECT * FROM orders o WHERE o.customer_id = ?1", nativeQuery = true)
	OrderEntity findByCustomerId(long customerId);
}
