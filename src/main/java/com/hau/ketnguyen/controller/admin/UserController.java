package com.hau.ketnguyen.controller.admin;

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

import com.hau.ketnguyen.dto.UserDTO;
import com.hau.ketnguyen.exception.UserAlreadyExistException;
import com.hau.ketnguyen.service.IRoleService;
import com.hau.ketnguyen.service.IUserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@GetMapping("/home/{pageNumber}")
	public String home(Model model, @PathVariable(name = "pageNumber") int currentPage) {
		Page<UserDTO> page = userService.getUser(currentPage, 3);
		List<UserDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("users", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		return "admin/list/user/homeList";
	}

	@GetMapping("/edit")
	public String editHome(Model model) {
		UserDTO pro = new UserDTO();

		if (roleService.listAll() != null) {
			model.addAttribute("roles", roleService.listAll());
		}

		model.addAttribute("user", pro);
		return "admin/add/user/homeEdit";
	}

	@PostMapping("/edit")
	public String editHome(Model model, @ModelAttribute("user") UserDTO userDTO) {

		try {
			userService.adminSave(userDTO);
		} catch (UserAlreadyExistException e) {
			e.printStackTrace();
		}

		return "redirect:/admin/user/home/1";
	}

	@GetMapping("/update/{id}")
	public String updateHome(Model model, @PathVariable(value = "id", required = false) Long id) {
		UserDTO pro = new UserDTO();

		if (id != null) {
			pro = userService.findOneById(id);
		}

		if (roleService.listAll() != null) {
			model.addAttribute("roles", roleService.listAll());
		}

		model.addAttribute("user", pro);
		return "admin/update/user/homeUpdate";
	}

	@PostMapping("/update/{id}")
	public String updateHome(Model model, @ModelAttribute("user") UserDTO userDTO,
			@PathVariable(value = "id", required = false) Long id) {
		try {
			userService.adminSave(userDTO);
		} catch (UserAlreadyExistException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/user/home/1";
	}

	@GetMapping("/delete/{pageNumber}/{id}")
	public String deleteHome(Model model, @PathVariable(value = "id", required = false) Long id,
			@PathVariable(name = "pageNumber") int currentPage) {
		Page<UserDTO> page = userService.getUser(currentPage, 3);
		List<UserDTO> list = page.getContent();
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		userService.delete(id);
		model.addAttribute("users", list);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		return "redirect:/admin/user/home/" + currentPage;
	}

}
