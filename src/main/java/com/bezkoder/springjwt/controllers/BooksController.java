package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.repository.CategoryRepository;
import com.bezkoder.springjwt.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    IBooksService iBooksService;
    @Autowired
    private CategoryRepository categoryRepository;

//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) Integer pageNum,
//                                                      @RequestParam Integer pageSize,
//                                                      @RequestParam(required = false) String text,
//                                                      @RequestParam(required = false) String nameSearch) {
//        try {
//            List<Books> booksList = new ArrayList<Books>();
//            Sort sort = Sort.by(text);
//
//            Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
////            Pageable pageable = PageRequest.of(pageNum, pageSize);
//            Page<Books> booksPage;
////            if (booksList) {
//            booksPage = iBooksService.findAllCategory(text, pageable);
////            } else {
//            booksPage = iBooksService.findAllByNameContaining(nameSearch, pageable);
////            }
//            booksList = booksPage.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("books", booksList);
//            response.put("currentPage", booksPage.getNumber());
//            response.put("totalItems", booksPage.getTotalElements());
//            response.put("totalPages", booksPage.getTotalPages());
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<Books>> getAll(@RequestParam Integer pageNum,
//                                              @RequestParam Integer pageSize) {
//        try {
//            Sort sort = Sort.by(Sort.Direction.ASC, "books");
//            Pageable pageable = PageRequest.of(pageSize, pageNum, sort);
//
//            return new ResponseEntity<>(iBooksService.findAllBooks(pageable), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getByAll(@RequestParam(required = false) Integer pageNum,
                                                        @RequestParam Integer pageSize,
                                                        @RequestParam(required = false) String nameSearch,
                                                        @RequestParam(required = false) String textSort) {
        try {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<Books> booksPage = iBooksService.findByAll(nameSearch, pageable);

            List<Books> booksList = booksPage.getContent();

            Map<String, Object> page = new HashMap<>();
            page.put("books", booksList);
            page.put("currentPage", booksPage.getNumber());
            page.put("totalItems", booksPage.getTotalElements());
            page.put("totalPages", booksPage.getTotalPages());

            return new ResponseEntity<>(page, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    @GetMapping("/sort-by")
//    public ResponseEntity<Map<String, Object>> getBySortAuthor(@RequestParam Integer pageNum,
//                                                               @RequestParam Integer pageSize,
//                                                               @RequestParam String text) {
//        try {
//            Sort sortAuthor = Sort.by(Sort.Direction.ASC, text);
//
//            Pageable pageable = PageRequest.of(pageNum, pageSize, sortAuthor);
//            Page<Books> booksPage = iBooksService.findAllBooks(pageable);
//
//            List<Books> booksList = booksPage.getContent();
//
//            Map<String, Object> page = new HashMap<>();
//            page.put("books", booksList);
//            page.put("currentPage", booksPage.getNumber());
//            page.put("totalItems", booksPage.getTotalElements());
//            page.put("totalPages", booksPage.getTotalPages());
//
//            return new ResponseEntity<>(page, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    //    @GetMapping("/find-name")
//    public ResponseEntity<List<Books>> getByName(@RequestParam String name) {
//        List<Books> booksList = iBooksService.findAllByNameContaining(name);
//        return new ResponseEntity<>(booksList, HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<Books> getById(@PathVariable Long id) {
        Optional<Books> optionalBooks = iBooksService.findById(id);
        if (optionalBooks.isPresent()) {
            return new ResponseEntity<>(optionalBooks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books books) {
        try {
            return new ResponseEntity<>(iBooksService.save(books), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Books> updateBook(@RequestBody Books books,
                                            @RequestParam Long id) {
        try {
            Books booksFind = iBooksService.findById(id).get();
//            if ()
            Books booksUpdate = iBooksService.save(books);
            return new ResponseEntity<>(booksUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable Long id) {
        iBooksService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
