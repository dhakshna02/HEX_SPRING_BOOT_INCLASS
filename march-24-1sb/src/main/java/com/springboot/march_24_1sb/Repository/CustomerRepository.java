package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
