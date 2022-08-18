package com.springboot.blogproject.blogproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogproject.blogproject.Payloades.Commentdto;
import com.springboot.blogproject.blogproject.Services.CommentServices;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentServices commentServices;


    //creating the comment 
    @PostMapping("/post/{postid}/comment")
    public ResponseEntity<Commentdto> createcomment(@RequestBody Commentdto commentdto,@PathVariable("postid")Integer postid)
    {
       Commentdto commentdto2=this.commentServices.createcomment(commentdto, postid);



        return new ResponseEntity<Commentdto>(commentdto2,HttpStatus.CREATED);

    }
    
}
