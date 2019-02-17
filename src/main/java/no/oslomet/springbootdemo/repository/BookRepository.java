package no.oslomet.springbootdemo.repository;

import no.oslomet.springbootdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
