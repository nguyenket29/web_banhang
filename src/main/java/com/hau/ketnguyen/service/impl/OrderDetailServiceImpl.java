package com.hau.ketnguyen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.OrderDetailEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.repository.IOrderDetailRepository;
import com.hau.ketnguyen.service.IOrderDetailService;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService{
	@Autowired
	private IOrderDetailRepository orderDetailRepository;

	@Override
	public OrderDetailEntity create(OrderEntity orderEntity, ProductEntity productEntity, CartItemEntity cart) {
		OrderDetailEntity orderDetail = new OrderDetailEntity();
		orderDetail.setOrder(orderEntity);
		orderDetail.setProduct(productEntity);
		orderDetail.setPrice(productEntity.getPrice());
		orderDetail.setQuantity(cart.getQuantity());
		return orderDetailRepository.save(orderDetail);
	}

}
