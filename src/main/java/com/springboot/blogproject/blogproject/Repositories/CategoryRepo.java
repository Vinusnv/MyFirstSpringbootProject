package com.springboot.blogproject.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogproject.blogproject.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category ,Integer>
{
    
}
