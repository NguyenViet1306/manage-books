package com.bezkoder.springjwt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bezkoder.springjwt.models.Books;

public interface IBooksService extends IService<Books> {
//    Page<Books> findAllByNameContaining(String name, Pageable pageable);


//    Page<Books> findAllCategory(Pageable pageable);

//    Page<Books> findAllCategory(String text,Pageable pageable);

	Page<Books> findByNameContaining(String bookName, String authorName, String categoryName, String keyword, Pageable pageable);


}
