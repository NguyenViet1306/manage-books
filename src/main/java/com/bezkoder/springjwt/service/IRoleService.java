package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Role;

import java.util.Optional;

public interface IRoleService extends IService<Role> {
    Optional<Role> findByName(String name);

}
