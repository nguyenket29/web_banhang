package com.hau.ketnguyen.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.entity.UserEntity;

public interface IShopingCartService {
	Page<CartItemDTO> listCartItems(int page, UserEntity userEntity);
	
	List<CartItemDTO> listAll(UserEntity entity);

	int addProductToCart(long productId, int qty, UserEntity userEntity);
	
	float updateQuantity(int quantity, long productId, long userId);
	
	void removeProductFromCart(long userId, long productId);
	
	void removeCart(CartItemDTO cartItemDTO, UserEntity userEntity);
}
