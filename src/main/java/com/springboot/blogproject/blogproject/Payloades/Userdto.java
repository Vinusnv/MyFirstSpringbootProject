package com.springboot.blogproject.blogproject.Payloades;




import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Userdto {

    private int id;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String about;
    @NotNull
    private String password;
    
}
