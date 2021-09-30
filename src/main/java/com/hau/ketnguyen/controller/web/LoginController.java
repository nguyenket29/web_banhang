package com.hau.ketnguyen.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login/home";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "error/403";
	}

}
