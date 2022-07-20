package com.mysite.minipage.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/hompiUser")
public class HompiUserController {

	private final HompiUserService hompiUserService;
	
	@GetMapping("/signup")
	public String signup(HompiUserCreateForm hompiuserCreateForm) {
		return "signup_form";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	
	@GetMapping("/index")
	public String layout() {
		return "index";
	}
}
