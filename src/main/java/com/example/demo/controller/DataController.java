package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
@Controller
public class DataController {
	@Autowired
	UsersService usv;
	
	@PostMapping("/registration")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus = usv.emailExists(user.getEmail());
		if(userStatus == false) {
			usv.addUser(user);
			System.out.println("user added");
		}
		else {
			System.out.println("user already exists");
		}

		return "home";
		
		
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, 
			               @RequestParam("password") String password, HttpSession session) {
		if(usv.validateUser(email, password)==true) {
			String role = usv.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equals("Admin")) {
				return "adminHome";
			}
			else {
			return "customerHome";
			}
		}
		else {
			return "login";
			
		}
	}
//	@GetMapping("/pay")
//	public String pay(@RequestParam String email) {
//		boolean paymentStatus= false;
//		
//		if(paymentStatus == true) {
//			Users user = usv.getUser(email);
//			user.setPremium(true);
//			usv.updateUser(user);
//		}
//		return "login";
//		
//	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	

	@GetMapping("/reg")
	public String register() {
		return "register";
}
}
