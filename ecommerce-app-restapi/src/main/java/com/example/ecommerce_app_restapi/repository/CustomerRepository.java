package com.example.ecommerce_app_restapi.repository;

import com.example.ecommerce_app_restapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);  // this line is crucial
}
