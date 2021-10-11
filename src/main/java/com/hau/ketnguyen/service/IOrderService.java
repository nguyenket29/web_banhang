package com.hau.ketnguyen.service;

import org.springframework.data.domain.Page;

import com.hau.ketnguyen.dto.CustomerDTO;
import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.entity.UserEntity;

public interface IOrderService {
	OrderDTO create(OrderDTO orderDTO, UserEntity userEntity, CustomerDTO customerDTO);
	OrderDTO findById(long id);
	Page<OrderDTO> getAll(int currentPage, int size);
}
