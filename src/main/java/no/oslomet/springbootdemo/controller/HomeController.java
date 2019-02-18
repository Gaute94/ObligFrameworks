package no.oslomet.springbootdemo.controller;

import no.oslomet.springbootdemo.model.*;
import no.oslomet.springbootdemo.repository.*;
import no.oslomet.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/Book")
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "Book";
    }

    @GetMapping("/Category")
    public String listCategories(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "Category";
    }


    @GetMapping("/Author")
    public String listAuthors(Model model){
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "Author";
    }

    @GetMapping("/AddShipping")
    public String listShipping(Model model){
        List<Shipping> shippings = shippingRepository.findAll();
        model.addAttribute("shippings", shippings);
        return "AddShipping";
    }

    @GetMapping("/AddOrder")
    public String listOrder(Model model){
        model.addAttribute("order", new Order());
        model.addAttribute("book", new Book());
        List<Shipping> shippings = shippingRepository.findAll();
        model.addAttribute("shippings", shippings);
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "AddOrder";
    }

    @GetMapping("/OrderHistory")
    public String listOrderHistory(Model model){
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        List<Shipping> shippings = shippingRepository.findAll();
        model.addAttribute("shippings", shippings);
        model.addAttribute("shipping", new Shipping());
        return "OrderHistory";
    }

    @PostMapping("/OrderHistory")
    public String listOrderHistory(@RequestParam("shipping") String shippingID, Model model){
        Shipping shipping  =this.shippingRepository.findById(Long.parseLong(shippingID)).get();
        model.addAttribute("shipping", shipping);
        List<Order> orders = orderRepository.findAll().stream()
                .filter(o -> o.getShipping().getId() == shipping.getId())
                .collect(Collectors.toList());
        model.addAttribute("orders", orders);
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        List<Shipping> shippings = shippingRepository.findAll();
        model.addAttribute("shippings", shippings);
        return "OrderHistory";
    }



    @GetMapping("/ShippingInfo/{id}")
    public String viewShipping(@PathVariable("id") String id,Model model ){
        Order order  =this.orderRepository.findById(Long.parseLong(id)).get();
        List<Shipping> shippings = shippingRepository.findAll();
        model.addAttribute("shippings", shippings );
        model.addAttribute("order", order );
        return "ShippingInfo";
    }


    @PostMapping("/saveBook")
    public String save(@ModelAttribute("book") Book book ){
        bookService.save(book);
        return "redirect:/Book";
    }

    @PostMapping("/saveCategory")
    public String save(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/Category";
    }

    @PostMapping("/saveAuthor")
    public String save(@ModelAttribute("author") Author author ){
        authorRepository.save(author);
        return "redirect:/Author";
    }

    @PostMapping("/saveShipping")
    public String save(@ModelAttribute("shipping")Shipping shipping){
        shippingRepository.save(shipping);
        return "redirect:/Author";
    }

    @PostMapping("/saveOrder")
    public String save(@ModelAttribute("order") Order order){
        orderRepository.save(order);
        return "redirect:/OrderHistory";
    }


    @GetMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn){
        bookService.delete(Long.parseLong(isbn));
        return "redirect:/Book";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable long id){
        authorRepository.deleteById(id);
        return "redirect:/Author";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryRepository.deleteById(id);
        return "redirect:/Category";
    }


    @GetMapping("/EditAuthor/{id}")
    public String editAuthor(@PathVariable("id") String id,Model model ){
        Author author  =this.authorRepository.findById(Long.parseLong(id)).get();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("author", author );
        model.addAttribute("authors", authors );
        return "EditAuthor";
    }

    @GetMapping("/EditBook/{isbn}")
    public String editBook(@PathVariable("isbn") String isbn, Model model ){
        Optional<Book> book  =this.bookService.getOne(Long.parseLong(isbn));
        if(!book.isPresent()){
            return "redirect:/Book";
        }
        List<Book> books = bookService.getAll();
        List<Category> categories = categoryRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("book", book.get() );
        model.addAttribute("books", books);
        return "EditBook";
    }

    @GetMapping({"/", "/searchBooks"})
    public String searchBooks(Model model){
        model.addAttribute("books", new ArrayList<Book>());
        return "searchBooks";
    }
    @GetMapping("/searchBook")
    public String searchBook(@RequestParam("title") String title, Model model){
        if(title.equals("")) return "redirect:/searchBooks";
        model.addAttribute("books", bookService.search(title));

        return "searchBooks";
    }
}

