package com.bezkoder.springjwt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
//	@Query(value = "select * FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
//			+ "(:name is null OR LOWER(BOOKS.NAME) LIKE LOWER('%'||:keyword||'%') OR LOWER(BOOKS.AUTHOR) LIKE LOWER('%'||:keyword||'%') "
//			+ "OR LOWER(CATEGORIES.name) LIKE LOWER('%'||:keyword||'%'))", countQuery = "SELECT count(*) FROM BOOKS,CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
//					+ "(:name is null OR LOWER(BOOKS.NAME) LIKE LOWER('%'||:keyword||'%')or LOWER(BOOKS.AUTHOR) LIKE LOWER('%'||:keyword||'%')"
//					+ "or LOWER(categories.name) LIKE LOWER('%'||:keyword||'%'))", nativeQuery = true)
//	Page<Books> findByAll(@Param("keyword") String nameSearch, Pageable pageable);

	@Query(value = "select * FROM BOOKS , CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
			+ "(:keyword is null OR LOWER(BOOKS.NAME) LIKE LOWER('%'||:keyword||'%') OR LOWER(BOOKS.AUTHOR) LIKE LOWER('%'||:keyword||'%') "
			+ "OR LOWER(CATEGORIES.name) LIKE LOWER('%'||:keyword||'%'))", countQuery = "SELECT count(*) FROM BOOKS,CATEGORIES  WHERE BOOKS.CATEGORY_ID = CATEGORIES.ID and"
					+ "(:keyword is null OR LOWER(BOOKS.NAME) LIKE LOWER('%'||:keyword||'%')or LOWER(BOOKS.AUTHOR) LIKE LOWER('%'||:keyword||'%')"
					+ "or LOWER(categories.name) LIKE LOWER('%'||:keyword||'%'))", nativeQuery = true)
	Page<Books> findByAll(@Param("keyword") String nameSearch, Pageable pageable);

}
