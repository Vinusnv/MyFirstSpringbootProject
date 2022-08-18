package com.springboot.blogproject.blogproject.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogproject.blogproject.Payloades.ApiResponse;
import com.springboot.blogproject.blogproject.Payloades.Userdto;
import com.springboot.blogproject.blogproject.Services.UserServices;
@RestController()
@RequestMapping("/api/user/")
public class UserController {


   @Autowired
    private UserServices userServices;


    //Creating the user using the POSTMAPPING
  @PostMapping("/")
  public ResponseEntity<Userdto> createuser(@Valid @RequestBody Userdto userdto)
  {

     Userdto createduser=this.userServices.createuser(userdto);


    // Userdto createduser1=this.userServices.createuser(userdto);

     return   new ResponseEntity<Userdto>(createduser, HttpStatus.CREATED);


  }


  //Updating the user using the PUTMAPPING
@PutMapping("/{uid}")
  public ResponseEntity<Userdto> updateuser(@Valid @RequestBody Userdto userdto,@PathVariable ("uid") Integer uid)
  {

  Userdto updateduser=this.userServices.updateuser(userdto, uid);

  return   ResponseEntity.ok(updateduser);
  }

  //Get the single user using the GETMAPPING
@GetMapping("/{uid}")
  public ResponseEntity<Userdto> getsingleuser(@PathVariable("uid")Integer uid)
  {

    Userdto userdto=this.userServices.getuserbyid(uid);

    return   ResponseEntity.ok(userdto); 

  }

@GetMapping("/")
  public ResponseEntity<List<Userdto>> getalluser()
  {

       List<Userdto> userdtos=this.userServices.getalluser();
       return ResponseEntity.ok(userdtos);


  }

@DeleteMapping("/{uid}")
  public ResponseEntity<ApiResponse> deleteuser(@PathVariable ("uid")Integer uid)
  {
    this.userServices.deleteuser(uid);

       return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true,null), HttpStatus.OK);

  
  }
    

  






    
    
}
