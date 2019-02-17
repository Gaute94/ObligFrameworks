package no.oslomet.springbootdemo.controller;

import no.oslomet.springbootdemo.model.Author;
import no.oslomet.springbootdemo.model.Book;
import no.oslomet.springbootdemo.model.Building;
import no.oslomet.springbootdemo.model.Category;
import no.oslomet.springbootdemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping({"/", "/list"})
    public String list(Model model ){
        List<Building> buildings = buildingRepository.findAll();
        model.addAttribute("buildings", buildings);
        Building building = new Building();
        model.addAttribute("building", building);
        return "index";
    }

    @GetMapping("/Book")
    public String listBooks(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Book";
    }

    @GetMapping("/Author")
    public String listAuthors(Model model){
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "Author";
    }

    @PostMapping("/saveBuilding")
    public String save(@ModelAttribute("building") Building building ){
        buildingRepository.save(building);
        return "redirect:/list";
    }

    @PostMapping("/saveBook")
    public String save(@ModelAttribute("book") Book book ){
        bookRepository.save(book);
        return "redirect:/Book";
    }

    @PostMapping("/saveAuthor")
    public String save(@ModelAttribute("author") Author author ){
        authorRepository.save(author);
        return "redirect:/Author";
    }




    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id,Model model ){
        Building building  =this.buildingRepository.findById(Long.parseLong(id)).get();
        List<Building> buildings = buildingRepository.findAll();
        model.addAttribute("building", building );
        model.addAttribute("buildings", buildings );

        return "index";
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @GetMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn){
        bookRepository.deleteById(Long.parseLong(isbn));
        return "redirect:/Book";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable long id){
        authorRepository.deleteById(id);
        return "redirect:/Author";
    }

    @GetMapping("/EditAuthor/{id}")
    public String editAuthor(@PathVariable("id") String id,Model model ){
        Author author  =this.authorRepository.findById(Long.parseLong(id)).get();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("author", author );
        model.addAttribute("authors", authors );
        return "EditAuthor";
    }

}

