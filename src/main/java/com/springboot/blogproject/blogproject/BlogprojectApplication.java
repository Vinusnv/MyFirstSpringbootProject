package com.springboot.blogproject.blogproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

    @Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	

}
