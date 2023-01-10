package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IBooksService extends IService<Books> {
    List<Books> findAllByNameContaining(String name);

    Page<Books> findAllBooks(Pageable pageable);

    Page<Books> findAllCategory(Pageable pageable);


}
