package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("""
            select c from Customer c
            where c.users.username = ?1
       """
    )
    Customer getCustomerByUserName(String name);
}
