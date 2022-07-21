package com.mysite.minipage.user;

import java.lang.reflect.Member;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/hompiUser")
public class HompiUserController {

	private final HompiUserService hompiUserService;
	
	@GetMapping("/hompiUser/signup")
	public String signup(HompiUserCreateForm hompiuserCreateForm) {
		return "signup_form";
	}
	
	
	@GetMapping("/hompiUser/login")
	public String login() {
		return "login_form";
	}
	
//	//////////��Ű
	public String login(@Valid @ModelAttribute HompiUserCreateForm hompiuserCreateForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "hompiUser/login_form";
        }

        HompiUser loginMember =  hompiUserService.login(hompiuserCreateForm.getUsername(), hompiuserCreateForm.getProfile());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "���̵� �Ǵ� ��й�ȣ�� ���� �ʽ��ϴ�.");
            return "hompiUser/login_form";
        }

        //�α��� ���� ó��

        //��Ű�� �ð� ������ ���� ������ ���� ���(������ ����� ��� ����)
        Cookie idCookie = new Cookie("username", loginMember.getUsername());
        response.addCookie(idCookie);
        System.out.println(idCookie);
        return "redirect:/";

    }

//	////////////////////��Ű���� ����
//	@PostMapping("/logout")
//	public String logout(HttpServletResponse response) {
//	    expiredCookie(response, "memberId");
//	    return "redirect:/";
//	}
//
//	private void expiredCookie(HttpServletResponse response, String cookieName) {
//	    Cookie cookie = new Cookie(cookieName, null);
//	    cookie.setMaxAge(0);
//	    response.addCookie(cookie);
//	    System.out.println(cookie);
//	}
//	
	@GetMapping("/hompiUser/index")
	public String layout() {
		return "index";
	}
	//����
	@PostMapping("/hompiUser/signup")
	public String signup(@Valid HompiUserCreateForm hompiuserCreateForm, BindingResult bindingResult,Principal principal) {
	
		if(bindingResult.hasErrors()) {
			
			return "signup_form";
			
		}
		if(!hompiuserCreateForm.getPassword1().equals(hompiuserCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2","passwordIncorrect","2���� �н����尡 ����ġ�մϴ�.");
			return "signup_form";
		}
		
		try {
			
			hompiUserService.create(hompiuserCreateForm.getUsername(), hompiuserCreateForm.getEmail(),hompiuserCreateForm.getPassword1(),hompiuserCreateForm.getProfile());
			
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed","�̹� ��ϵ� ������Դϴ�.");
			return "signup_form";
			
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed",e.getMessage());
			return "signup_form";
			
		}
		
		return "redirect:/";
	}
}
