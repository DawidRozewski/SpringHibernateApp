package pl.coderslab.SpringHibernateApp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int rating;
    private String description;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Author> authors = new ArrayList<>();
}
