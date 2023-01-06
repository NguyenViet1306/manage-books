package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "select * from cart where customer_id = ?1", nativeQuery = true)
    List<Cart> findCartByCustomerId( Long id);
}
