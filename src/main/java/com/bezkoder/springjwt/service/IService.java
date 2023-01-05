package com.bezkoder.springjwt.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save( T t);

    void delete(Long id);
}
