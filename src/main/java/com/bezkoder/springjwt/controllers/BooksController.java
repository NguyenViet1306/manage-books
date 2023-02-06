package com.bezkoder.springjwt.controllers;

import java.security.Principal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.repository.CartRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.security.jwt.AuthTokenFilter;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.service.IUserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.bezkoder.springjwt.service.IBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/books")
@SecurityRequirement(name = "bearerAuth")
public class BooksController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthTokenFilter authTokenFilter;
    @Autowired
    IBooksService iBooksService;

    @Autowired
    private IUserService iUserService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RoleRepository roleRepository;

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
    public ResponseEntity<Map<String, Object>> getByAll(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(required = false) String[] textSort, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "") String bookName, @RequestParam(defaultValue = "") String authorName, @RequestParam(defaultValue = "") String categoryName) {

        List<Order> orders = new ArrayList<Order>();
        //// tao 1 mang string mac dinh
        String[] sortDefault = {"books.id@asc"};
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
        Page<Books> booksPage = iBooksService.findByNameContaining(bookName, authorName, categoryName, keyword, pageable);

        // getContent() dung de truy xuat cac ban ghi trong trang
        List<Books> booksList = booksPage.getContent();
        Map<String, Object> page = new HashMap<>();
        page.put("books", booksList);
        page.put("currentPage", booksPage.getNumber());
        page.put("totalItems", booksPage.getTotalElements());
        page.put("totalPages", booksPage.getTotalPages());

        return new ResponseEntity<>(page, HttpStatus.OK);
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

    @Operation()
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Books> createBook(@RequestBody Books books) {
        try {
            return new ResponseEntity<>(iBooksService.save(books), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation()
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Books> updateBook(@RequestBody Books books, @RequestParam Long id) {
        try {
            Optional<Books> booksFind = iBooksService.findById(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (booksFind.isPresent()) {
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                if (userDetails.getId().equals(booksFind.get().getUser().getId())
                        // chuyen kieu du lieu của String "ROLE_ADMIN" sang Authorities
                        || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    books.setUpdateat(Date.from(Instant.now()));
                    Books booksUpdate = iBooksService.save(books);
                    return new ResponseEntity<>(booksUpdate, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation()
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Books> deleteBook(@PathVariable Long id) {
        iBooksService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
