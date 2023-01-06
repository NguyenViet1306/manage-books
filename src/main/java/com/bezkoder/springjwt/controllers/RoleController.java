package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role role1 = iRoleService.save(role);
            return new ResponseEntity<>(role1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            return new ResponseEntity<>(iRoleService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
