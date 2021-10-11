package com.hau.ketnguyen.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.dto.CustomerDTO;
import com.hau.ketnguyen.dto.OrderDTO;
import com.hau.ketnguyen.dto.OrderDetailDTO;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.service.ICustomerService;
import com.hau.ketnguyen.service.IOrderDetailService;
import com.hau.ketnguyen.service.IOrderService;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.UserServiceImpl;

@Controller
public class CheckoutController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderDetailService orderDetailService;

	@Autowired
	private IShopingCartService cartService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IProductService productService;

	@GetMapping("/checkout")
	public String showCheckOut(Model model) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		if (user == null) {
			return "You must login !";
		}

		int count = 0, qty = 0;
		List<CartItemDTO> cartList = cartService.listAll(user);
		for (CartItemDTO item : cartList) {
			count += item.getPrice() * item.getQuantity();
			qty += item.getQuantity();
		}
		model.addAttribute("customer", new CustomerDTO());
		model.addAttribute("cartSize", cartList.size());
		model.addAttribute("total", count);
		model.addAttribute("qty", qty);
		return "checkout/home";
	}

	@PostMapping("/checkout")
	public String processCheckOut(@ModelAttribute(value = "customer") CustomerDTO customer) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		OrderDTO orderDTO = new OrderDTO();
		if (user == null) {
			return "You must login !";
		}

		List<CartItemDTO> cartList = cartService.listAll(user);
		CustomerDTO cus = customerService.create(customer);
		float total = 0;
		for (CartItemDTO item : cartList) {
			total += item.getPrice() * item.getQuantity();
		}
		orderDTO.setAmount(total);
		orderDTO = orderService.create(orderDTO, user, cus);
		for (CartItemDTO item : cartList) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setPrice(item.getPrice());
			orderDetailDTO.setQuantity(item.getQuantity());
			orderDetailService.create(orderDetailDTO, productService.findById(item.getProductId()), orderDTO);
			cartService.removeCart(item, user);
		}
		return "redirect:/checkout?success";
	}
}
