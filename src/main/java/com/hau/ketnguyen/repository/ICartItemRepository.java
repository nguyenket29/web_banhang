package com.hau.ketnguyen.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.entity.UserEntity;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItemEntity, Long> {
	CartItemEntity findByProductAndUser(ProductEntity productEntity, UserEntity userEntity);

	Page<CartItemEntity> findByUser(UserEntity userEntity, Pageable pageable);
	
	@Query(value = "UPDATE cart_items c SET c.quantity = ?1 "
			+ "WHERE c.product_id = ?2 AND c.user_id = ?3", nativeQuery = true)
	@Modifying
	public void updateQuantity(int quantity, long productId, long userId);
	
	@Query(value = "DELETE FROM cart_items c WHERE c.user_id = ?1 AND c.product_id = ?2", nativeQuery = true)
	@Modifying
	public void deleteByUserAndProduct(long userId, long productId);
	
	@Query(value = "DELETE FROM cart_items c WHERE c.user_id = ?1 AND c.id = ?2", nativeQuery = true)
	@Modifying
	public void deleteAllByUser(long userId, long cartId);
	
	List<CartItemEntity> findAllByUser(UserEntity userEntity);
}
