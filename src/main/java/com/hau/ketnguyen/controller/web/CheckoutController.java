package com.hau.ketnguyen.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.CustomerEntity;
import com.hau.ketnguyen.entity.OrderEntity;
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.service.ICustomerService;
import com.hau.ketnguyen.service.IOrderDetailService;
import com.hau.ketnguyen.service.IOrderService;
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

	@GetMapping("/checkout")
	public String showCheckOut(Model model) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		if (user == null) {
			return "You must login !";
		}

		int count = 0,qty = 0;
		List<CartItemEntity> cartList = cartService.listAll(user);
		for (CartItemEntity item : cartList) {
			count += item.getProduct().getPrice() * item.getQuantity();
			qty += item.getQuantity();
		}
		model.addAttribute("customer", new CustomerEntity());
		model.addAttribute("cartSize", cartList.size());
		model.addAttribute("total", count);
		model.addAttribute("qty", qty);
		return "checkout/home";
	}

	@PostMapping("/checkout")
	public String processCheckOut(@ModelAttribute(value = "customer") CustomerEntity customer) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		if (user == null) {
			return "You must login !";
		}

		CustomerEntity cus = customerService.create(customer);
		List<CartItemEntity> cartList = cartService.listAll(user);
		for (CartItemEntity item : cartList) {
			OrderEntity orderEntity = orderService.create(item.getUser(), cus);
			orderEntity.setAmount(item.getProduct().getPrice() * item.getQuantity());
			orderDetailService.create(orderEntity, item.getProduct(), item);
			cartService.removeCart(item, item.getUser());
		}
		
		return "redirect:/checkout";
	}
}
