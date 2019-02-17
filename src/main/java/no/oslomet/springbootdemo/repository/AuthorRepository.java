package no.oslomet.springbootdemo.repository;

import no.oslomet.springbootdemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
