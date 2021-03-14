package hello.world.cli.maven.repository;

import hello.world.cli.maven.model.Book;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}