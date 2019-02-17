package no.oslomet.springbootdemo;

import no.oslomet.springbootdemo.model.Author;
import no.oslomet.springbootdemo.model.Book;
import no.oslomet.springbootdemo.model.Category;
import no.oslomet.springbootdemo.repository.AuthorRepository;
import no.oslomet.springbootdemo.repository.BookRepository;
import no.oslomet.springbootdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        Category fantasy = new Category("Fantasy");
        categoryRepository.save(fantasy);
        Category thriller = new Category("Thriller");
        categoryRepository.save(thriller);
        Book book = new Book(1234567891011L, "Harry Potter", "1998", 1, fantasy);
        bookRepository.save(book);
        Author author = new Author("Gaute", "Kvalheim", "8/10");
        authorRepository.save(author);
    }
}
