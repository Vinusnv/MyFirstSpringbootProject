package com.springboot.blogproject.blogproject.Services;

import java.util.List;

import com.springboot.blogproject.blogproject.Payloades.PostResponse;
import com.springboot.blogproject.blogproject.Payloades.Postdto;

public interface PostServices 
{
    public Postdto createpost(Postdto postdto,Integer categoryid,Integer userid);

    public Postdto updatepost(Postdto postdto,Integer postid);

    public Postdto getsinglepost(Integer postid);

    public PostResponse getallpost(Integer pagenumber,Integer pagesize, String sortBy, String sortDir);


    public void deletepost(Integer postid);

    public List<Postdto> getallpostbycategory(Integer categoryid);

    public List<Postdto> getallpostbyuser(Integer userid);


    
}
