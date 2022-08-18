package com.springboot.blogproject.blogproject.Services;

import java.util.List;

import com.springboot.blogproject.blogproject.Payloades.Commentdto;



public interface CommentServices 
{
      public Commentdto createcomment(Commentdto commentdto,Integer postid);


      public Commentdto updatecomment(Commentdto commentdto,Integer commentid);


      public Commentdto singlecomment(Integer commentid);


      public List<Commentdto>  getallcomment();
}
