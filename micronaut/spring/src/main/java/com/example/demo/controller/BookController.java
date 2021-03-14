package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/hello")
    public List<Book> index() {
        return bookService.findAll();
    }

    @PostMapping(value = "/hello")
    public ResponseEntity<String> create(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

}
