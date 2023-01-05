package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Books;

import java.util.List;

public interface IBooksService extends IService<Books>{
     List<Books> findAllByNameContaining(String name);

}
