package no.oslomet.springbootdemo.service;

import no.oslomet.springbootdemo.model.Book;
import no.oslomet.springbootdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {return bookRepository.findAll();}

    public Optional<Book> getOne(long id){return Optional.ofNullable(bookRepository.getOne(id));}

    public Book save(Book book){return bookRepository.save(book);}

    public void delete(long id){bookRepository.deleteById(id);}

    public List<Book> search(String searchString){
        String search = searchString.toLowerCase();
        System.out.println("Hello there, entered search!");
        return getAll().stream().filter(b -> b.getTitle().toLowerCase()
                .contains(search))
                .collect(Collectors.toList());
    }
}
