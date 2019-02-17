package no.oslomet.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private int postalCode;



    public Shipping() {
    }
}
