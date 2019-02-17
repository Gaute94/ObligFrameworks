package no.oslomet.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    @ManyToOne
    private Shipping shipping;
    @ManyToMany(mappedBy = "orders")
    private List<Book> books = new ArrayList<>();
}
