package com.hau.ketnguyen.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hau.ketnguyen.entity.CartItemEntity;
import com.hau.ketnguyen.entity.ProductEntity;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.UserServiceImpl;

@Controller(value = "homeController")
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IShopingCartService cartService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public String viewHome(Model model) {
		return viewPaginated(model, 1);
	}
	
	@GetMapping("/page/{pageNumber}")
	public String viewPaginated(Model model,@PathVariable(name = "pageNumber") int currentPage) {
		List<CartItemEntity> cartLists = cartService.listAll(userService.getCurrentlyLoggedInUser());
		Page<ProductEntity> page = productService.getAll(currentPage,8);
		List<ProductEntity> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		model.addAttribute("lists", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cartSize", cartLists.size());
		return "home";
	}
}
