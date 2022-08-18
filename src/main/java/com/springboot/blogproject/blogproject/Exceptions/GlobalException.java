package com.springboot.blogproject.blogproject.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.blogproject.blogproject.Payloades.ApiResponse;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler
    public ResponseEntity<ApiResponse> resourcenotfoundexception(ResourceNotFoundException ex)
    {
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message, false,"Exception Handled By Vinay");

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
        
    }
  

  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodargnotvallidexception(MethodArgumentNotValidException ex)
    {

        Map<String ,String> errors=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error->{
           String fieldname=((FieldError)error).getField();
           String message=error.getDefaultMessage();

           errors.put(fieldname, message);


        });
        return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);

    }

    
}
