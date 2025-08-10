package com.example.practice_ecommerce_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.practice_ecommerce_app.model.Customer;
import com.example.practice_ecommerce_app.service.CustomerService;
import java.util.List;


@Controller
public class AppController {
       
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/home")
	public String showHomePage(Model model) {
		List<Customer> customerList =customerService.getAllCustomers();
		model.addAttribute("customerList", customerList);
		return "home";
	}
	@GetMapping("/add-customer")
	public String showAddCustomerPage(Model model) {
		Customer c =new Customer();
		model.addAttribute("customer", c);
		return "add-customer";
	}
	@PostMapping("/save-customer")
	public String saveCustomer(@ModelAttribute Customer customer) {
	    System.out.println("Inside saveCustomer: " + customer);
	    customerService.saveCustomer(customer);
	    return "redirect:/home";
	}
	@GetMapping("/edit/{id}")
	public String showEditCustomer(Model model, @PathVariable Long id) {
		Customer customer =customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "edit-customer";
	}
	@PostMapping("/update-customer")
	public String updateCustomer(@ModelAttribute Customer customer) {
	    System.out.println("Inside saveCustomer: " + customer);
	    Customer existingCustomer =customerService.getCustomerById(customer.getId());
	    existingCustomer.setName(customer.getName());
	    existingCustomer.setName(customer.getEmail());
	    existingCustomer.setName(customer.getPassword());
	    customerService.saveCustomer(existingCustomer);
	    return "redirect:/home";
	}
	@GetMapping("/delete/{id}")
	public String showDeleteCustomer(@PathVariable Long id) {
	    customerService.deleteCustomer(id);
	    return "redirect:/home";
	}


}
