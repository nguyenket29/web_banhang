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
import com.hau.ketnguyen.entity.UserEntity;
import com.hau.ketnguyen.service.IProductService;
import com.hau.ketnguyen.service.IShopingCartService;
import com.hau.ketnguyen.service.impl.UserServiceImpl;

@Controller
public class ShoppingCartController {
	@Autowired
	private IProductService productService;

	@Autowired
	private IShopingCartService cartService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/cart/{page}")
	public String showCart(Model model, @PathVariable("page") int page) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		Page<CartItemDTO> cart = cartService.listCartItems(page, user);
		List<CartItemDTO> lists = cart.getContent();
		long totalItems = cart.getTotalElements();
		int totalPages = cart.getTotalPages();

		int count = 0;
		List<CartItemDTO> cartLists = cartService.listAll(user);
		for (CartItemDTO item : cartLists) {
			count += item.getPrice() * item.getQuantity();
		}

		model.addAttribute("cartItems", lists);
		model.addAttribute("cartSize", lists.size());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("count", count);
		return "cart/home";
	}

	@GetMapping("/cart/{page}/{id}/{qty}")
	public String addCart(Model model, @PathVariable("page") int page, @PathVariable("id") long id,
			@PathVariable("qty") int qty) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		Page<CartItemDTO> cart = cartService.listCartItems(page, user);
		List<CartItemDTO> lists = cart.getContent();
		long totalItems = cart.getTotalElements();
		int totalPages = cart.getTotalPages();
		
		cartService.addProductToCart(id, qty, user);

		int count = 0;
		List<CartItemDTO> cartLists = cartService.listAll(user);
		for (CartItemDTO item : cartLists) {
			count += item.getPrice() * item.getQuantity();
		}

		model.addAttribute("cartItems", lists);
		model.addAttribute("cartSize", lists.size());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("count", count);
		return "redirect:/cart/1";
	}

	@GetMapping("/cart/{page}/{action}/{id}/{qty}")
	public String updateQuantityCart(Model model, @PathVariable("page") int page, @PathVariable("action") String action,
			@PathVariable("id") long id, @PathVariable("qty") int qty) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		Page<CartItemDTO> cart = cartService.listCartItems(page, user);
		List<CartItemDTO> lists = cart.getContent();
		long totalItems = cart.getTotalElements();
		int totalPages = cart.getTotalPages();

		ProductDTO pro = productService.findById(id);
		if (null != pro) {
			if (action.equals("minus")) {
				pro.setQuantity(qty - 1);
			} else {
				pro.setQuantity(qty + 1);
			}
		}

		int count = 0;
		List<CartItemDTO> cartLists = cartService.listAll(user);
		for (CartItemDTO item : cartLists) {
			count += item.getPrice() * item.getQuantity();
		}

		cartService.updateQuantity(pro.getQuantity(), pro.getId(), user.getId());

		model.addAttribute("cartItems", lists);
		model.addAttribute("cartSize", lists.size());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("count", count);
		return "cart/home";
	}

	@GetMapping("/cart/remove/{page}/{pid}")
	public String removeProductFromCart(Model model, @PathVariable("page") int page, @PathVariable("pid") long id) {
		UserEntity user = userService.getCurrentlyLoggedInUser();
		Page<CartItemDTO> cart = cartService.listCartItems(page, user);
		List<CartItemDTO> lists = cart.getContent();
		long totalItems = cart.getTotalElements();
		int totalPages = cart.getTotalPages();

		if (user == null) {
			return "You must login to remove product.";
		}

		cartService.removeProductFromCart(user.getId(), id);

		int count = 0;
		List<CartItemDTO> cartLists = cartService.listAll(user);
		for (CartItemDTO item : cartLists) {
			count += item.getPrice() * item.getQuantity();
		}

		model.addAttribute("cartItems", lists);
		model.addAttribute("cartSize", lists.size());
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("count", count);
		return "redirect:/cart/" + page;
	}
}
