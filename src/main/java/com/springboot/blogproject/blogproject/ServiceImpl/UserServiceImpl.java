package com.springboot.blogproject.blogproject.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogproject.blogproject.Entities.User;
import com.springboot.blogproject.blogproject.Exceptions.ResourceNotFoundException;
import com.springboot.blogproject.blogproject.Payloades.Userdto;
import com.springboot.blogproject.blogproject.Repositories.UserRepo;
import com.springboot.blogproject.blogproject.Services.UserServices;


@Service
public class UserServiceImpl implements UserServices
 {
    @Autowired
    private UserRepo repo;

  @Autowired
    private ModelMapper mapper;

    @Override
    public Userdto createuser(Userdto userdto) {
       
       //we got the userdto data from the client and converted it to user 
        User user=this.dtoTouser(userdto);
        //we saved the user data that can be seen in database
        User saveuser=this.repo.save(user);
        //again we are converting user to userdto so that we can return userdto to client
        Userdto userdto2=this.userTodto(saveuser);
//returning the userdto to client
        return userdto2;
    }

    @Override
    public Userdto updateuser(Userdto userdto, Integer uid) {
       //we fetched the detail of the user using the id
       User user=this.repo.findById(uid).orElseThrow(()->new ResourceNotFoundException("resourcename", "fieldname", uid));
       //we are doing the updation process using the below logic by using userdto object comming from the client
       user.setName(userdto.getName());
         user.setEmail(userdto.getEmail());
         user.setPassword(userdto.getPassword());
         user.setAbout(userdto.getAbout());
//we are doing save process for our newly updated user
        User updateduser= this.repo.save(user);

        //again we are converting the user object to userdto in order to show to our client
        Userdto userdto2=this.userTodto(updateduser);

        //rtuening the userdto to client
        return userdto2;
    }

    @Override
    public Userdto getuserbyid(Integer uid) {
       
        User user=this.repo.findById(uid).orElseThrow(()->new ResourceNotFoundException("resourcename", "fieldname", uid));
        Userdto userdto=this.userTodto(user);

      return userdto;

    }

    @Override
    public List<Userdto> getalluser() {

        //we got all the user and converted in to list formate
         List<User> users=this.repo.findAll();
        //we are doing converssion of list of user to list of userdto
        List<Userdto> userdtos=users.stream().map(user->this.userTodto(user)).collect(Collectors.toList());
       //returning the list of userdto to client
        return userdtos;
    }

    @Override
    public void deleteuser(Integer uid) {

        //getting the single user by using the userid
        User  user=this.repo.findById(uid).orElseThrow(()->new ResourceNotFoundException("ResourceName", "Not Found", uid));
       //deleting the user using the delete method of jpacrudrepository
        this.repo.delete(user);

    }

public User dtoTouser(Userdto userdto)
{

    User user=this.mapper.map(userdto, User.class);
   //User user=new User();
 //  user.setId(userdto.getId());
  // user.setName(userdto.getName());
 //  user.setEmail(userdto.getEmail());
  // user.setAbout(userdto.getAbout());
  // user.setPassword(userdto.getPassword());


    return user;

}

public Userdto userTodto(User user)
{

    Userdto userdto=this.mapper.map(user,Userdto.class);
   // Userdto userdto=new Userdto();

//userdto.setId(user.getId());
//userdto.setName(user.getName());
//userdto.setEmail(user.getEmail());
//userdto.setAbout(user.getAbout());
//userdto.setPassword(user.getPassword());

return userdto;

}


    
}
