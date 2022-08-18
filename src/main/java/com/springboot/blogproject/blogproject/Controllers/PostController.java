package com.springboot.blogproject.blogproject.Controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogproject.blogproject.AppConfig.Appconstants;
import com.springboot.blogproject.blogproject.Payloades.ApiResponse;
import com.springboot.blogproject.blogproject.Payloades.PostResponse;
import com.springboot.blogproject.blogproject.Payloades.Postdto;
import com.springboot.blogproject.blogproject.Services.PostServices;

@RestController
@RequestMapping("/api/")
public class PostController {


    //post mapping
    @Autowired
    private PostServices postServices;
    


    @PostMapping("/user/{userid}/category/{categoryid}/posts")
    public ResponseEntity<Postdto> createpost(@RequestBody Postdto postdto,@PathVariable ("userid")Integer userid ,@PathVariable("categoryid")Integer categoryid )
    {
       Postdto post2=this.postServices.createpost(postdto, categoryid, userid);

        return new ResponseEntity<Postdto>(post2, HttpStatus.CREATED);
    }



    //gte mapping in the project

    @GetMapping("/user/{userid}/posts")
     public ResponseEntity<List<Postdto>> getpostbyuser(@PathVariable("userid")Integer userid)
     {



    List<Postdto> posts= this.postServices.getallpostbyuser(userid);

        return new  ResponseEntity<List<Postdto>>(posts, HttpStatus.OK);



     }

     @GetMapping("/category/{categoryid}/posts")
     public ResponseEntity<List<Postdto>> getallpostsbycategory(@PathVariable("categoryid") Integer categoryid)
     {


       List<Postdto>  posts=this.postServices.getallpostbycategory(categoryid);

     

        return new  ResponseEntity<List<Postdto>>(posts, HttpStatus.OK);
        

     }

     //getting all posts

     @GetMapping("/posts")
     public  ResponseEntity<PostResponse> getallposts(
     @RequestParam(value = "pageNumber",defaultValue =Appconstants.PageNumber,required =false )Integer pageNumber,
     @RequestParam(value = "pageSize",defaultValue = Appconstants.PageSize,required =false )Integer pagesize,
     @RequestParam(defaultValue = Appconstants.SortBy,value = "sortBy",required = false) String sortBy,
     @RequestParam(defaultValue = Appconstants.SortDir,value = "sortDir",required = false) String sortDir )
     {
       
//to pu all data in the pages we are dividing the data into multiple pagessint pagesize=5;
         

        PostResponse postResponse=this.postServices.getallpost(pageNumber,pagesize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);

     }
     
//get single post using the post id

@GetMapping("/posts/{postid}")
public ResponseEntity<Postdto> getsinglepost(@PathVariable("postid")Integer postid)
{
    Postdto postdto=this.postServices.getsinglepost(postid);
    return new ResponseEntity< Postdto>(postdto,HttpStatus.OK);

}
     
//delete post
@DeleteMapping("/delete/{postid}")
public ResponseEntity<ApiResponse> deletepost(@PathVariable("postid")Integer postid)
{

   this.postServices.deletepost(postid);

     return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true,null), HttpStatus.OK);

}

//update post
@PutMapping("/updatepost/{postid}")
public ResponseEntity<Postdto> updatepost(@RequestBody Postdto postdto,@PathVariable("postid")Integer postid)
{

  Postdto postdto2=this.postServices.updatepost(postdto, postid);
    return new ResponseEntity< Postdto>(postdto2,HttpStatus.OK);
} 




    
}
