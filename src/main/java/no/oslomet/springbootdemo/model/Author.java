package no.oslomet.springbootdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String rating;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();


    public Author(){

    }

    public Author(String firstName, String lastName, String rating){
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }
}
