package com.hau.ketnguyen.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hau.ketnguyen.dto.CartItemDTO;
import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.service.ICategoryService;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.UserServiceImpl;

@Controller
public class ShopController {
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IShopingCartService cartService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/shop")
	public String login(Model model) {
		return viewProductCategory(model, 1);
	}

	@GetMapping({ "/shop/{page}"})
	public String viewProductCategory(Model model, @PathVariable(value = "page") int currentPage) {
		List<CartItemDTO> cartLists = cartService.listAll(userService.getCurrentlyLoggedInUser());
		Page<ProductDTO> page = productService.getAll(currentPage,6);
		List<ProductDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		model.addAttribute("lists", list);
		model.addAttribute("listCategory", categoryService.findAll());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cartSize", cartLists.size());
		return "shop/home";
	}

	@GetMapping("/shop/categoryId={id}/{page}")
	public String viewProductByCategory(Model model, @PathVariable(value = "id") long categoryId,
			@PathVariable(value = "page") int currentPage) {
		List<CartItemDTO> cartLists = cartService.listAll(userService.getCurrentlyLoggedInUser());
		Page<ProductDTO> page = productService.getProductByCategoryId(currentPage, categoryId, 6);
		List<ProductDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		model.addAttribute("lists", list);
		model.addAttribute("listCategory", categoryService.findAll());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cartSize", cartLists.size());
		return "shop/home";
	}
}
