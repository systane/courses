package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import javax.inject.Named;
import java.util.List;

@Named
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "cachezinha")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }
}
