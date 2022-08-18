package com.springboot.blogproject.blogproject.Payloades;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {



    private List<Postdto> contents;
    private int pageNumber;
    private int PageSize;

    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    
}
