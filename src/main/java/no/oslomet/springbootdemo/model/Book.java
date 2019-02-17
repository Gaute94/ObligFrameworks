package no.oslomet.springbootdemo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Book {
    @Id
    private long isbn;
    private String title;
    private String releaseYear;
    private int quantity;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany
    @JoinTable
    private List<Order> orders = new ArrayList<>();

    public Book(){

    }

    public Book(long isbn, String title, String releaseYear, int quantity, Category category){
        this.isbn = isbn;
        this.title = title;
        this.releaseYear = releaseYear;
        this.quantity = quantity;
        this.category = category;
    }

}
