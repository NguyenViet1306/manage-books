package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IBooksService extends IService<Books> {
//    Page<Books> findAllByNameContaining(String name, Pageable pageable);

//    Page<Books> findAllBooks(Pageable pageable);

//    Page<Books> findAllCategory(Pageable pageable);

//    Page<Books> findAllCategory(String text,Pageable pageable);

	Page<Books> findByAll( String keyword, Pageable pageable);




}
