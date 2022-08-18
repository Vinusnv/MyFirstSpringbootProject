package com.springboot.blogproject.blogproject.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogproject.blogproject.Entities.Category;
import com.springboot.blogproject.blogproject.Exceptions.ResourceNotFoundException;
import com.springboot.blogproject.blogproject.Payloades.Categorydto;
import com.springboot.blogproject.blogproject.Repositories.CategoryRepo;
import com.springboot.blogproject.blogproject.Services.CategoryServices;

  @Service
public class categoryImpl implements CategoryServices
{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Categorydto createcategory(Categorydto categorydto) {
        
   Category category=this.categorydtoTocategory(categorydto);
   Category savedcategory=this.categoryRepo.save(category);
   Categorydto categorydto2=this.categoryTocategorydto(savedcategory);

        return categorydto2;
    } 

    @Override
    public Categorydto updatecategory(Categorydto categorydto, Integer categoryid) {
        
      Category category=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryid));

      category.setCategorytitle(categorydto.getCategorytitle());
      category.setCategoryDescription(categorydto.getCategoryDescription());

         Category updatedCategory=this.categoryRepo.save(category);

        Categorydto categorydto2=this.categoryTocategorydto(updatedCategory);
        return categorydto2;
    }

    @Override
    public Categorydto getsinglecategory(Integer categoryid) {
        


        Category category=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryid));



        return this.categoryTocategorydto(category);
    }

    @Override
    public List<Categorydto> getallcategories() {
        List<Category> categories=this.categoryRepo.findAll();
        List<Categorydto> categorydtos=categories.stream().map(category->this.categoryTocategorydto(category)).collect(Collectors.toList());
        return categorydtos;
    }

    @Override
    public void deletecategory(Integer categoryid) {
        Category category=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryid));

        this.categoryRepo.delete(category);
    }

//converting  category to categorydto
    public    Categorydto categoryTocategorydto(Category category)
    {

       Categorydto categorydto=this.mapper.map(category, Categorydto.class);
        return categorydto;

    }

    //converting categorydto to category using the modelmapper.map
    public Category categorydtoTocategory(Categorydto categorydto)
    {
        Category category=this.mapper.map(categorydto, Category.class);
        return category;
    }
    
}
