package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    IBooksService iBooksService;

//    @GetMapping
//    public ResponseEntity<List<Books>> getAll(@RequestParam String name){
//        try{
//            List<Books> booksList = new ArrayList<>();
//            if (booksList == null){
//                booksList.addAll(iBooksService.findAll());
//            } else {
//                booksList.addAll(iBooksService.findAllByNameContaining(name));
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<Books>> getAll() {
        try {
            return new ResponseEntity<>(iBooksService.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Books> getById(@PathVariable Long id) {
        Optional<Books> optionalBooks = iBooksService.findById(id);
        if (optionalBooks.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
