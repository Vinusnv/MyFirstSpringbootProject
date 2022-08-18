package com.springboot.blogproject.blogproject.Payloades;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Categorydto {


    
    private int Categoryid ;
    @NotBlank
    @Size(min = 4 ,message = "Minimum Character should be more than 4")
    private String Categorytitle;
    @NotBlank
    @Size(min  = 10,message = "Minimum Character should be more than 10")
    private String CategoryDescription;

    
    
}
