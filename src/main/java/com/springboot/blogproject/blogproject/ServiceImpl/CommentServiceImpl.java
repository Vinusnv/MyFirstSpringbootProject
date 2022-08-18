package com.springboot.blogproject.blogproject.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogproject.blogproject.Entities.Comment;
import com.springboot.blogproject.blogproject.Entities.Post;
import com.springboot.blogproject.blogproject.Exceptions.ResourceNotFoundException;
import com.springboot.blogproject.blogproject.Payloades.Commentdto;
import com.springboot.blogproject.blogproject.Repositories.CommentRepo;
import com.springboot.blogproject.blogproject.Repositories.PostRepo;
import com.springboot.blogproject.blogproject.Services.CommentServices;

@Service
public class CommentServiceImpl implements CommentServices
{

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepo postRepo;

    @Override
    public Commentdto createcomment(Commentdto commentdto,Integer postid) {
        
        Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Commentid", "commentid", postid));
       

       
        Comment comment=this.CommentdtoTocomment(commentdto);
        comment.setPost(post);
        Comment savedcomment=this.commentRepo.save(comment);
        Commentdto commentdto2=this.commentTocommentdto(savedcomment);
        return commentdto2;
    }

    @Override
    public Commentdto updatecomment(Commentdto commentdto, Integer commentid) {
         Comment comment=this.commentRepo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Commentid", "commentid", commentid));

         comment.setContent(commentdto.getContent());
         Comment updatedcomment=   this.commentRepo.save(comment);
         Commentdto commentdto2=this.commentTocommentdto(updatedcomment);
        
        return commentdto2;
    }

    @Override
    public Commentdto singlecomment(Integer commentid) {
        Comment comment=this.commentRepo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Commentid", "commentid", commentid));
        Commentdto commentdto=this.commentTocommentdto(comment);
        return commentdto;
    }

    @Override
    public List<Commentdto> getallcomment() {
       List<Comment> comments=this.commentRepo.findAll();
       List<Commentdto> commentdto=comments.stream().map(comment->this.commentTocommentdto(comment)).collect(Collectors.toList());

        return commentdto;
    }


     //delete comment
    public void deletecomment(Integer commentid)
     {
        Comment comment=this.commentRepo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Commentid", "commentid", commentid));

           this.commentRepo.delete(comment);
      }

    //converting the comment to commentdto
    public Commentdto commentTocommentdto(Comment comment)
    {
       Commentdto commentdto=this.mapper.map(comment,Commentdto.class);

        return  commentdto;
    }

    //converting commentdto to comment

    public Comment CommentdtoTocomment(Commentdto commentdto)
    {
        Comment comment=this.mapper.map(commentdto, Comment.class);
        return comment;
    }
    
}
