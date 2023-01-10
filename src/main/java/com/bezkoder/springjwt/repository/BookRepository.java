package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Books;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "select * from books where books like ?1", nativeQuery = true)
    List<Books> findAllByNameContaining(String name);


    @Query(value = "select * from books",
            countQuery = "SELECT count(*) FROM books",
            nativeQuery = true)
    Page<Books> findAllBooks(Pageable pageable);

    @Query(value = "SELECT * from BOOKS join CATEGORIES on CATEGORY_ID = CATEGORIES.ID order by NAME_CATEGORY" ,countQuery = "SELECT count(*) FROM BOOKS", nativeQuery = true )
    Page<Books> findAllCategory(Pageable pageable);
}
