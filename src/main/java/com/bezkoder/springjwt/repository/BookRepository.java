package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
//	@Query(value = "select * FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and (:keyword is null or LOWER(books.name ||books.author"
//			+ "||categories.name as category) like lower('%'||:keyword||'%'))" ,countQuery = "SELECT count(*) FROM BOOKS,CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
//					+ " ((:keyword is null or LOWER(books.name ||books.author||categories.name as category) LIKE LOWER('%'||:keyword||'%')) )", nativeQuery = true)
//	Page<Books> findByAll(@Param("keyword") String nameSearch, Pageable pageable);


	@Query(value = "select * FROM BOOKS, CATEGORIES WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID "
					+ "and (:bookName is null or LOWER(books.name) like (lower('%'||:bookName||'%') collate binary_ai)) "
					+ "and (:authorName is null or LOWER(books.author) like (lower('%'||:authorName||'%')collate binary_ai)) "
					+ "and (:categoryName is null or LOWER(categories.name) like (lower('%'||:categoryName||'%')collate binary_ai)) "
					+ "and (:keyword is null or LOWER(books.name||books.author||categories.name) LIKE (LOWER('%'||:keyword||'%')collate binary_ai))",
					countQuery = "SELECT count(*) FROM BOOKS,CATEGORIES WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID "
							+ "and (:bookName is null or LOWER(books.name) like (lower('%'||:bookName||'%') collate binary_ai)) "
							+ "and (:authorName is null or LOWER(books.author) like (lower('%'||:authorName||'%')collate binary_ai)) "
							+ "and (:categoryName is null or LOWER(categories.name) like (lower('%'||:categoryName||'%')collate binary_ai)) "
							+ "and (:keyword is null or LOWER(books.name||books.author||categories.name) LIKE (LOWER('%'||:keyword||'%')collate binary_ai))", nativeQuery = true)
	Page<Books> findByNameContaining(@Param("bookName") String bookName, @Param("authorName") String authorName,
					@Param("categoryName") String categoryName,	@Param("keyword") String keyword, Pageable pageable);
	

	
}
