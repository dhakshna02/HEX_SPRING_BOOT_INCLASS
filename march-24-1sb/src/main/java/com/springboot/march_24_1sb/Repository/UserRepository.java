package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.JavaBean;

public interface UserRepository extends JpaRepository<Users,Long> {

    @Query("select u from Users u where u.username = ?1")
    Users FindByUserName(String username);
}
