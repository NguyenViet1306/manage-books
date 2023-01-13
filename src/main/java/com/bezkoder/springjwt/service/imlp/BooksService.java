package com.bezkoder.springjwt.service.imlp;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.repository.BookRepository;
import com.bezkoder.springjwt.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

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
	public Page<Books> findByAll(String nameSearch, Pageable pageable) {
		return bookRepository.findByAll(nameSearch, pageable);
	}

//    @Override
//    public Page<Books> findAllByNameContaining(String name, Pageable pageable) {
//        return bookRepository.findAllByNameContaining("%" + name + "%", pageable);
//    }

//    @Override
//    public Page<Books> findAllBooks(Pageable pageable) {
//        return bookRepository.findAllBooks(pageable);
//    }

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
