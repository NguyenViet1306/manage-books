package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Item;
import com.bezkoder.springjwt.repository.ItemRepository;
import com.bezkoder.springjwt.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    IItemService iItemService;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item itemCreate = iItemService.save(item);
        return new ResponseEntity<>(itemCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem (@RequestBody Item item,
                                            @RequestParam Long id){
        Optional<Item> itemUpdate = iItemService.findById(id);
        if (itemUpdate.isPresent()){
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        try {
            List<Item> itemList = iItemService.findAll();
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
