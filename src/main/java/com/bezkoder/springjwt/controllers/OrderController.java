package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.models.Orders;
import com.bezkoder.springjwt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            return new ResponseEntity<>(iOrderService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id){
        Optional<Orders> optionalOrders = iOrderService.findById(id);
        if (optionalOrders.isPresent()) {
            return new ResponseEntity<>(optionalOrders.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        try {
            Orders ordersCreate = iOrderService.save(orders);
            return new ResponseEntity<>(ordersCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Orders> updateOrders(@RequestBody Orders orders,
                                               @RequestParam Long id){
        Orders ordersUpdate = iOrderService.findById(id).get();
        if (ordersUpdate != null){
            return new ResponseEntity<>(iOrderService.save(orders), HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
