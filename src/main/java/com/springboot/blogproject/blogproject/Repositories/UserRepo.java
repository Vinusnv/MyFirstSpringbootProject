package com.springboot.blogproject.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogproject.blogproject.Entities.User;

public interface UserRepo  extends JpaRepository<User,Integer>
 {
    
}
