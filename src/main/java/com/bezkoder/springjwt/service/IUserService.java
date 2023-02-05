package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserService extends IService<User> {
    UserDetails loadUserById(Long id);

    UserDetails loadUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    void blockUser(Long id);
}
