package com.bezkoder.springjwt.service.imlp;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.repository.BookRepository;
import com.bezkoder.springjwt.service.IBooksService;

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
	public Page<Books> findByNameContaining(String bookName, String authorName, String categoryName, String keyword,
			Pageable pageable) {
		return bookRepository.findByNameContaining(bookName, authorName, categoryName, keyword, pageable);
	}




//    @Override
//    public Page<Books> findAllCategory(Pageable pageable) {
//        return null;
//    }

//    @Override
//    public Page<Books> findAllCategory(Pageable pageable) {
//        return bookRepository.findAllCategory(pageable);
//    }

//    @Override
//    public Page<Books> findAllCategory(String cate,
//                                       Pageable pageable) {
//        return bookRepository.findAllCategory(cate, pageable);
//    }

}
