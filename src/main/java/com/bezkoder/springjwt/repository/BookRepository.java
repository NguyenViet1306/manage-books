package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "select * from books where books like ?1", nativeQuery = true)
    List<Books> findAllByNameContaining(String name);
}
