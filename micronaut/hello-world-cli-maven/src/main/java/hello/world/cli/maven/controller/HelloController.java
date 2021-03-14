package hello.world.cli.maven.controller;

import hello.world.cli.maven.model.Book;
import hello.world.cli.maven.service.BookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Controller("/hello")
@AllArgsConstructor
@Validated
public class HelloController {

    private final BookService bookService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Book> index() {
        return bookService.findAll();
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<String> create(@Valid Book book) {
        bookService.save(book);
        return HttpResponse.created("Created");
    }
}
