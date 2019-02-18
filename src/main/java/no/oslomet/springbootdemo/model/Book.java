package no.oslomet.springbootdemo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Category category;

    @ManyToMany(mappedBy = "books")
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
