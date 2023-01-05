package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.User;

import java.util.Optional;

public interface IUserService extends IService<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
