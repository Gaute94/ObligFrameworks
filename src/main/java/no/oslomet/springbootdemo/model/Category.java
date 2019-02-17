package no.oslomet.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Category {
    @Id
    private String name;

    public Category(){

    }
    public Category(String name){
        this.name = name;
    }

}
