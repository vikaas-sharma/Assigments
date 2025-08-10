package com.example.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.portfolio.model.Education;

import java.util.Arrays;
import java.util.List;

@Controller
public class EducationController {

	
	@GetMapping("/education")
	public String GetEducationPage(Model model) {
		List<Education> educationList =Arrays.asList(
				
				new Education("2017–2021", "Bachelor of Engineering", "GOVERNMENT ENGINEERING COLLEGE RAMANAGARA", "Completed B.E in Computer Engineering with <strong>8.15 CGPA</strong>."),
	            new Education("2016–2017", "Pre-University Education", "CHENNAMBIKA PU COLLEGE", "Completed with <strong>78.86%</strong>.")
	            );
		
		model.addAttribute("educationList",educationList);
		
		return "education";
				
	}
	
}
