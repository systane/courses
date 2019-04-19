package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
