package com.example.ecommerce_app_restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_app_restapi.model.Customer;
import com.example.ecommerce_app_restapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	public CustomerRepository customerRepository; 
	
	public Customer saveCustomer(Customer customer) {
		Customer c= customerRepository.save(customer);
		return c;	
	}
	
	public List<Customer> GetAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer getbyId(Long id) {
		return customerRepository.findById(id).get();	
	}
	
	public void deleteCustomer(Long id) {
	  customerRepository.deleteById(id);
	}
}
