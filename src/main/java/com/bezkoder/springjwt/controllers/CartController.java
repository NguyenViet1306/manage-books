package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Cart;
import com.bezkoder.springjwt.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    ICartService iCartService;

    @GetMapping
    private ResponseEntity<List<Cart>> getAll(@RequestParam Long id) {
        return new ResponseEntity<>(iCartService.findCartByIdUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> createCard(@RequestBody Cart cart) {
        try {
           Cart cartCreat = iCartService.save(cart);
           return new ResponseEntity<>(cartCreat,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


