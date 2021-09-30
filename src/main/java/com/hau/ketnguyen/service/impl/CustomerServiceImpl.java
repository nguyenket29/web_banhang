package com.hau.ketnguyen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.repository.ICustomerRepository;
import com.hau.ketnguyen.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public CustomerEntity create(CustomerEntity cus) {
		cus.setFirstName(cus.getFirstName());
		cus.setLastName(cus.getLastName());
		cus.setEmail(cus.getEmail());
		cus.setPhone(cus.getPhone());
		cus.setAddress(cus.getAddress());
		cus.setInformation(cus.getInformation());
		return customerRepository.save(cus);
	}

}
