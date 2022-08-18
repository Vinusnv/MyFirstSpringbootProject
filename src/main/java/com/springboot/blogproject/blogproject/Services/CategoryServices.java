package com.springboot.blogproject.blogproject.Services;

import java.util.List;

import com.springboot.blogproject.blogproject.Payloades.Categorydto;

public interface CategoryServices 
{

    //Create category

public Categorydto createcategory(Categorydto categorydto);

    //update category

    public Categorydto updatecategory(Categorydto categorydto,Integer categoryid);

    //get category

    public Categorydto getsinglecategory(Integer categoryid);

    //getall category

    public List<Categorydto> getallcategories();

    //delete category
    public void deletecategory(Integer categoryid);
    
}
