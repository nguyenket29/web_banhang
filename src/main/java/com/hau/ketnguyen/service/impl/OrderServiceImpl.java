package com.hau.ketnguyen.service.impl;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.OrderConverter;
import com.hau.ketnguyen.dto.CustomerDTO;
import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.repository.ICustomerRepository;
import com.hau.ketnguyen.repository.IOrderRepository;
import com.hau.ketnguyen.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO, UserEntity userEntity, CustomerDTO customerDTO) {
		OrderEntity orderEntity = orderConverter.toEntity(orderDTO);
		CustomerEntity customer = customerRepository.findById(customerDTO.getId()).get();
		orderEntity.setCustomer(customer);
		orderEntity.setStatus(true);
		return orderConverter.toDto(orderRepository.save(orderEntity));
	}
	
	@Override
	@Transactional
	public OrderDTO findById(long id) {
		return orderConverter.toDto(orderRepository.findByCustomerId(id));
	}

	@Override
	public Page<OrderDTO> getAll(int currentPage, int size) {
		Pageable pageable = PageRequest.of(currentPage - 1, size);
		Page<OrderEntity> pageEntity = orderRepository.findAll(pageable);
		Page<OrderDTO> pageDTO = pageEntity.map(new Function<OrderEntity, OrderDTO>() {
			@Override
			public OrderDTO apply(OrderEntity entity) {
				return orderConverter.toDto(entity);
			}
		});
		return pageDTO;
	}

}
