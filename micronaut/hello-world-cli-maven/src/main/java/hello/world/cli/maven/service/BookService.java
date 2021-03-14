package hello.world.cli.maven.service;

import hello.world.cli.maven.model.Book;
import hello.world.cli.maven.repository.BookRepository;
import io.micronaut.cache.annotation.Cacheable;
import lombok.AllArgsConstructor;

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
