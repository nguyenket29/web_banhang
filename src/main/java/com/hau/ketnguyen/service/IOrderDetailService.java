package com.hau.ketnguyen.service;

import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.OrderDetailEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.ProductEntity;

public interface IOrderDetailService {
	OrderDetailEntity create(OrderEntity orderEntity, ProductEntity productEntity, CartItemEntity cart);
}
