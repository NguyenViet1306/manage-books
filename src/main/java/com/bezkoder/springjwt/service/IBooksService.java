package com.bezkoder.springjwt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bezkoder.springjwt.models.Books;

public interface IBooksService extends IService<Books> {
//    Page<Books> findAllByNameContaining(String name, Pageable pageable);

//    Page<Books> findAllBooks(Pageable pageable);

//    Page<Books> findAllCategory(Pageable pageable);

//    Page<Books> findAllCategory(String text,Pageable pageable);

	Page<Books> findByAll(String bookName, String authorName, String categoryName, String keyword, Pageable pageable);

//	Page<Books> findByAll(String keyword, Pageable pageable);

}
