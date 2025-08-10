package com.example.ecommerce_app_restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_app_restapi.model.Customer;
import com.example.ecommerce_app_restapi.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	
	
	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}
	
	@GetMapping("/all")
	public List<Customer> GetAllCustomers(){
		return customerService.GetAllCustomers();
	}
	
	@GetMapping("/all/{id}")
	public Customer getbyId(@PathVariable Long id) {
		return customerService.getbyId(id);
	}
	
	@PutMapping("/put/{id}")
	public Customer updateCustomer (@RequestBody Customer customer, @PathVariable Long id){
		Customer existingCustomer = customerService.getbyId(id);
    	existingCustomer.setName(customer.getName());
    	existingCustomer.setPassword(customer.getPassword());
    	existingCustomer.setEmail(customer.getEmail());
    	
    	Customer result = 	customerService.saveCustomer(existingCustomer);
    	return result;
		
	}	
	
	@DeleteMapping("/delete/{id}")
		public String deleteCustomer(@PathVariable Long id) {
			customerService.deleteCustomer(id) ;
			return "deleted" +id;
		}
		
		
	}
	
	
	


