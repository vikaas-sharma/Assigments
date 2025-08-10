package com.example.ecommerce_app_restapi.repository;

import com.example.ecommerce_app_restapi.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmail() {
        Customer customer = new Customer();
        customer.setName("vikas");
        customer.setEmail("vikas@gmail.com");

        customerRepository.save(customer);

        Customer found = customerRepository.findByEmail("vikas@gmail.com");
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Vikas");
    }
}
