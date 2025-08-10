package com.example.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

	@GetMapping("/contact")
	public String contactController() {
		return "contact";
	}
	
	@PostMapping("/contact")
	public String submitContact(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("message") String message,
			Model model
			) {
		 System.out.println("contact form submitted");
		 System.out.println("Name: "+ name);
		 System.out.println("Email: "+ email);
		 System.out.println("Message: "+ message);
		 
		 model.addAttribute("success", true);
		 return "contact";
	}
}
