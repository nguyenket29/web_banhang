package com.hau.ketnguyen.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hau.ketnguyen.dto.UserDTO;
import com.hau.ketnguyen.exception.UserAlreadyExistException;
import com.hau.ketnguyen.service.IUserService;

@Controller
@RequestMapping("/registration")
public class RegisterController {
	@Autowired
	private IUserService usserService;
	
	@GetMapping
	public String showRegistration(Model model) {
		model.addAttribute("user",new UserDTO());
		return "register/home";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("user") UserDTO userDTO) throws UserAlreadyExistException {
		usserService.save(userDTO);
		return "redirect:/registration?success";
	}
}
