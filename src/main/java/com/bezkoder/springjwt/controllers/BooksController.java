package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.repository.CategoryRepository;
import com.bezkoder.springjwt.service.IBooksService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BooksController {

	@Autowired
	IBooksService iBooksService;
	@Autowired
	CategoryRepository categoryRepository;

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

	// @GetMapping("/sort-by")
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
	// @GetMapping("/find-name")
//    public ResponseEntity<List<Books>> getByName(@RequestParam String name) {
//        List<Books> booksList = iBooksService.findAllByNameContaining(name);
//        return new ResponseEntity<>(booksList, HttpStatus.OK);
//    }

	//// pt chon kieu sap xep
	private Sort.Direction sortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> getByAll(@RequestParam(defaultValue = "0") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "") String nameSearch,
			@RequestParam(required = false) String[] textSort) {
//        try {		

		List<Order> orders = new ArrayList<Order>();
		//// tao 1 mang string mac dinh
		String[] sortDefault = { "books.id@asc" };
		if (textSort == null || textSort.length == 0) {
			textSort = sortDefault;
		}

		for (String orderSort : textSort) {
			//// su dung contains de tim ky tu trong chuoi
			if (orderSort.contains("@")) {
				//// orderSort = "column, direction" ; sd Split cat chuoi de lay value
				String[] textSortSplit = orderSort.split("@");
				orders.add(new Order(sortDirection(textSortSplit[1]), textSortSplit[0]));
			} else {
				//// textSort = [column, direction]
				orders.add(new Order(sortDirection(""), orderSort));
			}
		}

		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(orders));
		Page<Books> booksPage = iBooksService.findByAll(nameSearch, pageable);

		// getContent() dung de truy xuat cac ban ghi trong trang
		List<Books> booksList = booksPage.getContent();

		Map<String, Object> page = new HashMap<>();
		page.put("books", booksList);
		page.put("currentPage", booksPage.getNumber());
		page.put("totalItems", booksPage.getTotalElements());
		page.put("totalPages", booksPage.getTotalPages());

		return new ResponseEntity<>(page, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
	}

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
	public ResponseEntity<Books> updateBook(@RequestBody Books books, @RequestParam Long id) {
		try {
			Optional<Books> booksFind = iBooksService.findById(id);
			if (booksFind.isPresent()) {
				Books booksUpdate = iBooksService.save(books);
				return new ResponseEntity<>(booksUpdate, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

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
