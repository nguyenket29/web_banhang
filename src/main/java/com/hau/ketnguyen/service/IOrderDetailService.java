package com.hau.ketnguyen.service;

import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.dto.OrderDetailDTO;
import com.hau.ketnguyen.dto.ProductDTO;

public interface IOrderDetailService {
	OrderDetailDTO create(OrderDetailDTO orderDetailDTO, ProductDTO productDTO, OrderDTO orderDTO);
	OrderDetailDTO findById(long id);
}
