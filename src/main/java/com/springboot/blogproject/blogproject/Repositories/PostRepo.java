package com.springboot.blogproject.blogproject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogproject.blogproject.Entities.Category;
import com.springboot.blogproject.blogproject.Entities.Post;
import com.springboot.blogproject.blogproject.Entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>
{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
  
   
    
}
