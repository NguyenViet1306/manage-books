package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Books;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

//    @Query(value = "select * from books where books like ?1", nativeQuery = true)
//    Page<Books> findAllByNameContaining(String name, Pageable pageable);

//    @Query(value = "select * from books", countQuery = "SELECT count(*) FROM books", nativeQuery = true)
//    Page<Books> findAllBooks(Pageable pageable);

	@Query(value = "select * FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
			+ "(:name is null OR LOWER(BOOKS.NAME) LIKE LOWER(:name) OR LOWER(BOOKS.AUTHOR) LIKE LOWER(:name) "
			+ "OR LOWER(CATEGORIES.name) LIKE LOWER(:name)) " 
			,countQuery = "SELECT count(*) FROM BOOKS,CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
					+ "(:name is null OR LOWER(BOOKS.NAME) LIKE LOWER(:name)or LOWER(BOOKS.AUTHOR) LIKE LOWER(:name)"
					+ "or LOWER(categories.name) LIKE LOWER(:name))"
					, nativeQuery = true)
	Page<Books> findByAll(@Param("name") String nameSearch, Pageable pageable);

//	@Query(value = "select * FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and (:name is null OR LOWER(BOOKS.NAME) LIKE LOWER(:name))",
//  countQuery = "select count(*) FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and (:name is null OR LOWER(BOOKS.NAME) LIKE LOWER(:name))", nativeQuery = true)
//	Page<Books> findByAll(@Param("name") String nameSearch, Pageable pageable);

}
