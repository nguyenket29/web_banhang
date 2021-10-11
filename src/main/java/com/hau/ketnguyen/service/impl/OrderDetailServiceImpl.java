package com.hau.ketnguyen.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.OrderDetailConverter;
import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.dto.OrderDetailDTO;
import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.entity.OrderDetailEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.repository.IOrderDetailRepository;
import com.hau.ketnguyen.repository.IOrderRepository;
import com.hau.ketnguyen.repository.IProductRepository;
import com.hau.ketnguyen.service.IOrderDetailService;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService{
	@Autowired
	private IOrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderDetailConverter orderDetailConverter;
	
	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IProductRepository productRepository;

	@Override
	@Transactional
	public OrderDetailDTO create(OrderDetailDTO orderDetailDTO, ProductDTO productDTO, OrderDTO orderDTO) {
		OrderDetailEntity orderDetail = orderDetailConverter.toEntity(orderDetailDTO);
		OrderEntity order = orderRepository.findById(orderDTO.getId()).get();
		ProductEntity product = productRepository.findById(productDTO.getId()).get();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		return orderDetailConverter.toDto(orderDetailRepository.save(orderDetail));
	}

	@Override
	@Transactional
	public OrderDetailDTO findById(long id) {
		return orderDetailConverter.toDto(orderDetailRepository.findById(id).get());
	}

}
