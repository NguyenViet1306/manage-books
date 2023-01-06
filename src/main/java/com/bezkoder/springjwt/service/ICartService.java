package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.models.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService extends IService<Cart>{
    List<Cart> findCartByIdUser( Long id);

}
