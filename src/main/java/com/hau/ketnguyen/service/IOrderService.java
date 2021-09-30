package com.hau.ketnguyen.service;

import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.UserEntity;

public interface IOrderService {
	OrderEntity create(UserEntity userEntity, CustomerEntity customerEntity);
}
