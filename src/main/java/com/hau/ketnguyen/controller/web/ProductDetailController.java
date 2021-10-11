package com.hau.ketnguyen.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.UserServiceImpl;

@Controller
public class ProductDetailController {
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IShopingCartService cartService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/detail")
	public String showDetailProduct() {
		return "detail/home";
	}

	@GetMapping("/detail/{id}")
	public String showDetailProductById(@PathVariable(value = "id") long id, Model model) {
		ProductDTO dto = productService.findById(id);
		List<CartItemDTO> cartLists = cartService.listAll(userService.getCurrentlyLoggedInUser());
		model.addAttribute("detailById", dto);
		model.addAttribute("lists", productService.findAll());
		model.addAttribute("cartSize", cartLists.size());
		return "detail/home";
	}

	@GetMapping("/detail/{action}/{id}/{qty}")
	public String showCart(Model model, @PathVariable("action") String action, @PathVariable("id") long id,
			@PathVariable("qty") int qty) {
		ProductDTO pro = productService.findById(id);
		List<CartItemDTO> cartLists = cartService.listAll(userService.getCurrentlyLoggedInUser());
		if (null != pro) {
			if (action.equals("minus")) {
				pro.setQuantity(qty - 1);
			} else {
				pro.setQuantity(qty + 1);
			}
		}
		model.addAttribute("detailById", pro);
		model.addAttribute("lists", productService.findAll());
		model.addAttribute("cartSize", cartLists.size());
		return "detail/home";
	}
}
