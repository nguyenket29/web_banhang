package com.hau.ketnguyen.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hau.ketnguyen.dto.ProductDTO;
import com.hau.ketnguyen.service.ICategoryService;
import com.hau.ketnguyen.service.IProductService;

@Controller(value = "homeAdminController")
@RequestMapping("/admin")
public class HomeController {
	@Autowired
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/home")
	public String viewHome() {
		return "admin/home";
	}

	@GetMapping("/edit")
	public String editHome(Model model) {
		ProductDTO pro = new ProductDTO();

		if (categoryService.listAll() != null) {
			model.addAttribute("categories", categoryService.listAll());
		}

		model.addAttribute("product", pro);
		return "admin/add/homeEdit";
	}

	@PostMapping("/edit")
	public String editHome(Model model, @ModelAttribute("product") ProductDTO product,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		if (categoryService.listAll() != null) {
			model.addAttribute("categories", categoryService.listAll());
		}
		product.setThumbnail(saveFile(file));
		productService.saveOrUpdate(product);
		model.addAttribute("product", product);
		return "admin/add/homeEdit";
	}

	@GetMapping("/update/{id}")
	public String updateHome(Model model, @PathVariable(value = "id", required = false) Long id) {
		ProductDTO product = new ProductDTO();

		if (id != null) {
			product = productService.findOneById(id);
		}

		if (categoryService.listAll() != null) {
			model.addAttribute("categories", categoryService.listAll());
		}

		model.addAttribute("product", product);
		return "admin/update/homeUpdate";
	}

	@PostMapping("/update/{id}")
	public String updateHome(Model model, @ModelAttribute("product") ProductDTO products,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable(value = "id", required = false) Long id) {
		products.setThumbnail(saveFile(file));
		productService.saveOrUpdate(products);
		model.addAttribute("product", products);
		return "redirect:/admin/list/1";
	}

	@GetMapping("/list/{pageNumber}")
	public String viewPaginated(Model model, @PathVariable(name = "pageNumber") int currentPage) {
		Page<ProductDTO> page = productService.getAll(currentPage,4);
		List<ProductDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();

		model.addAttribute("lists", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		return "admin/list/homeList";
	}
	
	@GetMapping("/delete/{pageNumber}/{id}")
	public String deleteHome(Model model, @PathVariable(value = "id", required = false) Long id,
			@PathVariable(name = "pageNumber") int currentPage) {
		Page<ProductDTO> page = productService.getAll(currentPage,4);
		List<ProductDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		productService.delete(id);
		model.addAttribute("lists", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		return "redirect:/admin/list/" + currentPage;
	}

	private String saveFile(MultipartFile file) {
		if (null != file && !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// creating folder save file
				File dir = new File("D:\\jee-2020-09\\project\\springboot-project-web\\src\\main\\resources"
						+ File.separator + "static/img");
				if (!dir.exists()) {
					dir.mkdir();
				}

				// creating file on server
				String name = String.valueOf("image" + new Date().getTime() + ".jpg");
				File serverFile = new File(dir.getAbsoluteFile() + File.separator + name);
				System.out.println("Path of image on server: " + serverFile.getPath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return name;
			} catch (IOException e) {
				System.out.println("Error Upload File: " + e.getMessage());
			}
		} else {
			System.out.println("File not exists !");
		}
		return null;
	}
}
