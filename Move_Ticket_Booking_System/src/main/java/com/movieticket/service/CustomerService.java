package com.movieticket.service;

import com.movieticket.dto.CustomerDto;
import com.movieticket.entity.Customer;
import com.movieticket.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }
    
    public Customer createCustomer(CustomerDto customerDto) {
        // Check if email already exists
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new IllegalArgumentException("Customer with email " + customerDto.getEmail() + " already exists");
        }
        
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        
        return customerRepository.save(customer);
    }
    
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = getCustomerById(id);
        
        // Check if email is being changed and if it already exists
        if (!existingCustomer.getEmail().equals(customerDto.getEmail()) && 
            customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new IllegalArgumentException("Customer with email " + customerDto.getEmail() + " already exists");
        }
        
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhone(customerDto.getPhone());
        
        return customerRepository.save(existingCustomer);
    }
    
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
    
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email));
    }
    
    public List<Customer> searchCustomersByName(String name) {
        Optional<Customer> customer = customerRepository.findByNameContainingIgnoreCase(name);
        return customer.map(List::of).orElse(List.of());
    }
    
    public boolean customerExists(String email) {
        return customerRepository.existsByEmail(email);
    }
}
