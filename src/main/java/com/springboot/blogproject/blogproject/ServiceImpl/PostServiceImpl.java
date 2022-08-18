package com.springboot.blogproject.blogproject.ServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blogproject.blogproject.Entities.Category;
import com.springboot.blogproject.blogproject.Entities.Post;
import com.springboot.blogproject.blogproject.Entities.User;
import com.springboot.blogproject.blogproject.Exceptions.ResourceNotFoundException;
import com.springboot.blogproject.blogproject.Payloades.PostResponse;
import com.springboot.blogproject.blogproject.Payloades.Postdto;
import com.springboot.blogproject.blogproject.Repositories.CategoryRepo;
import com.springboot.blogproject.blogproject.Repositories.PostRepo;
import com.springboot.blogproject.blogproject.Repositories.UserRepo;
import com.springboot.blogproject.blogproject.Services.PostServices;
   


@Service
public class PostServiceImpl implements PostServices
{

    @Autowired
private PostRepo postRepo;
@Autowired
private UserRepo userRepo;
@Autowired
private CategoryRepo categoryRepo;

@Autowired
private ModelMapper mapper;

    @Override
    public Postdto createpost(Postdto postdto, Integer categoryid, Integer userid) {
       
//fetch the category using category Repo

  Category category=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("category", "category id", categoryid));
  User user=this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "userid", userid));
  Post post=this.postdtoTopost(postdto);

        post.setPostimage("default.png");
        post.setAdddate(new Date(2));
        post.setCategory(category);
        post.setUser(user);

        Post savedpost=this.postRepo.save(post);

        Postdto postdto2=this.postTopostdto(savedpost) ;
        return postdto2;
    }

    @Override
    public Postdto updatepost(Postdto postdto, Integer postid) {

        Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", "Postid", postid));
        post.setPosttitle(postdto.getPosttitle());
        post.setPostcontent(postdto.getPostcontent());
        post.setPostimage(postdto.getPostimage());
        

        Post savepost=this.postRepo.save(post);
        Postdto updatedpostdto=this.postTopostdto(savepost);
       
        return updatedpostdto;
    }
//Fetching single post
    @Override
    public Postdto getsinglepost(Integer postid) {
       Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", "Postid", postid));

       Postdto postdto=this.postTopostdto(post);
        return postdto;
    }
//fetching all the posts
    @Override
    public PostResponse  getallpost(Integer pagenumber,Integer pagesize,String sortBy,String sortDir) {

       
     //  PageRequest page=PageRequest.of(pagenumber, pagesize);
    // PageRequest page=PageRequest.of(pagenumber, pagesize,Sort.by(sortBy).ascending());
Sort sort=null;
if(sortDir.equalsIgnoreCase("asc"))
{
  sort=Sort.by(sortBy).ascending();
}
else
{
    sort=Sort.by(sortBy).descending();
}

     PageRequest page=PageRequest.of(pagenumber, pagesize,sort);

       Page<Post>  pages=this.postRepo.findAll(page);

      List<Post> allpost=pages.getContent();
       List<Postdto> postdtos=allpost.stream().map(posts->this.postTopostdto(posts)).collect(Collectors.toList());

   //  List<Post> post=this.postRepo.findAll();

   PostResponse postresp=new PostResponse();
   postresp.setPageSize(pages.getNumber());
   postresp.setPageNumber(pages.getSize());
   postresp.setTotalElements(pages.getNumberOfElements());
   postresp.setTotalPages(pages.getTotalPages());
   postresp.setLastPage(pages.isLast());
   postresp.setContents(postdtos);
   

        return postresp;
    }
//deletepost
    @Override
    public void deletepost(Integer postid) {
      

        Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("This post", "postid", postid));
        
        this.postRepo.delete(post);

        
    }

    @Override
    public List<Postdto> getallpostbycategory(Integer categoryid) {
        Category category=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "categoryid", categoryid));
        List<Post> post=this.postRepo.findByCategory(category);

        List<Postdto> postdtos=post.stream().map(post1->this.postTopostdto(post1)).collect(Collectors.toList());



        return postdtos;
    }

    @Override
    public List<Postdto> getallpostbyuser(Integer userid) {
       

        User user=this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("Resource", "Userid", userid));

        List<Post> posts=this.postRepo.findByUser(user);

        List<Postdto> postdto=posts.stream().map(posts1->this.postTopostdto(posts1)).collect(Collectors.toList());
        return postdto;
    }

    

    
 


    //converting the post to postdto
 
    public Postdto postTopostdto(Post post)
    {
        return this.mapper.map(post, Postdto.class);
    }


    public Post postdtoTopost(Postdto postdto)
    {
        return this.mapper.map(postdto, Post.class);
    }

   
  


   
    
}
