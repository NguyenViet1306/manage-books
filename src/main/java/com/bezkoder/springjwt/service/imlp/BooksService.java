package com.bezkoder.springjwt.service.imlp;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.repository.BookRepository;
import com.bezkoder.springjwt.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements IBooksService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Books> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Books save(Books books) {
        return bookRepository.save(books);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Books> findAllByNameContaining(String name) {
        return bookRepository.findAllByNameContaining(name);
    }
}
