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
import com.springboot.blogproject.blogproject.Payloades.Categorydto;
import com.springboot.blogproject.blogproject.Services.CategoryServices;


@RestController
@RequestMapping("/api/category/")
public class CategoryController {

     @Autowired
    private  CategoryServices categoryServices ;

    //creating the category using the post mapping
    @PostMapping("/")
    public ResponseEntity<Categorydto> createcategory1(@Valid @RequestBody Categorydto categorydto)
    {

        Categorydto createdcategorydto=this.categoryServices.createcategory(categorydto);

        return new ResponseEntity<Categorydto>(createdcategorydto, HttpStatus.CREATED);

    }


    //updating the user using the put mapping 
    @PutMapping("/{categoryid}")
    public ResponseEntity<Categorydto> updatecategory(@Valid @RequestBody Categorydto categorydto ,@PathVariable("categoryid")Integer categoryid)
    {
        Categorydto updatedcategory=this.categoryServices.updatecategory(categorydto, categoryid);

        return  ResponseEntity.ok(updatedcategory);
    }


    //get single category  using the get mapping 
  @GetMapping("/{categoryid}")
    public    ResponseEntity<Categorydto>  getsinglecategory(@PathVariable("categoryid") Integer categoryid)
    {
        Categorydto categorydto=this.categoryServices.getsinglecategory(categoryid);

        return ResponseEntity.ok(categorydto);
    }

    //get all categoriess using the get mapping
    @GetMapping("/")
    public ResponseEntity<List<Categorydto>>  getallcategories()
    {

          List<Categorydto> categorydtos=this.categoryServices.getallcategories();
          return ResponseEntity.ok(categorydtos);

    }

    //deleting the category using the deletemapping
    @DeleteMapping("/{categoryid}")
    public ResponseEntity<ApiResponse> deletecategory(  @PathVariable("categoryid") Integer categoryid )
    {
        this.categoryServices.deletecategory(categoryid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted", true, "vinay"),HttpStatus.OK);
         
    }


    
}
