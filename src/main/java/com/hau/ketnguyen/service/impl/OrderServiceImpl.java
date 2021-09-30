package com.hau.ketnguyen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.repository.IOrderRepository;
import com.hau.ketnguyen.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public OrderEntity create(UserEntity userEntity, CustomerEntity customerEntity) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setStatus(true);
		orderEntity.setCustomer(customerEntity);
		return orderRepository.save(orderEntity);
	}

}
