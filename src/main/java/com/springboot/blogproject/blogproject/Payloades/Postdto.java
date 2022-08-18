package com.springboot.blogproject.blogproject.Payloades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.springboot.blogproject.blogproject.Entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Postdto {


    private int  posstid;
   
    private String posttitle;
  
    private String postcontent;
   
    private String postimage;
   
    private Date adddate;
   
   
    private Categorydto category;
   
    private Userdto user;

    private Set<Comment> comments=new HashSet<>();

    
}
