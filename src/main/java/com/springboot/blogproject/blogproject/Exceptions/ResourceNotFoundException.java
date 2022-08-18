package com.springboot.blogproject.blogproject.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException

{

    String Resource;
    String fieldname;
    long fieldvalue;
   
    public ResourceNotFoundException(String Resource, String fieldname, long fieldvalue) {
        super(String.format("%s is %s with  : %s", Resource,fieldname,fieldvalue));
        this.Resource = Resource;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
    }
    
    

    
}

