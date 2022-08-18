package com.springboot.blogproject.blogproject.Services;

import java.util.List;

import com.springboot.blogproject.blogproject.Payloades.Userdto;

public interface UserServices 
 {

    public Userdto createuser(Userdto userdto);


    public Userdto updateuser(Userdto userdto,Integer uid);


    public Userdto getuserbyid(Integer uid);


    public List<Userdto> getalluser();


    public void deleteuser(Integer uid);
    
}
