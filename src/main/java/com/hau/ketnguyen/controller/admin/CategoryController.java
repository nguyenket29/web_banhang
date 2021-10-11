package com.hau.ketnguyen.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hau.ketnguyen.dto.CategoriesDTO;
import com.hau.ketnguyen.service.ICategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/home/{pageNumber}")
	public String home(Model model, @PathVariable(name = "pageNumber") int currentPage) {
		Page<CategoriesDTO> page = categoryService.getAll(currentPage, 3);
		List<CategoriesDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("categories", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		return "admin/list/category/homeList";
	}
}
