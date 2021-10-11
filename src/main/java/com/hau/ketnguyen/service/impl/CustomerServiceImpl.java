package com.hau.ketnguyen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.converter.CustomerConverter;
import com.hau.ketnguyen.dto.CustomerDTO;
import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.repository.ICustomerRepository;
import com.hau.ketnguyen.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;

	@Override
	public CustomerDTO create(CustomerDTO cus) {
		CustomerEntity customer = customerConverter.toEntity(cus);
		return customerConverter.toDto(customerRepository.save(customer));
	}

}
