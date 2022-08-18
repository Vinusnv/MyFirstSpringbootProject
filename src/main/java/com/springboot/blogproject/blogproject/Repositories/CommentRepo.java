package com.springboot.blogproject.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogproject.blogproject.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>
{

    
    
}
