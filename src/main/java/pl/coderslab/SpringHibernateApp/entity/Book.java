package pl.coderslab.SpringHibernateApp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(min = 5)
    private String title;

    @Range(min = 1, max = 10, message = "Wartosci maja byc od 1 do 10.")
    private int rating;

    @Size(max = 600)
    private String description;

    @ManyToOne
    @NotNull
    private Publisher publisher;

    @ManyToMany
    @NotEmpty
    private List<Author> authors = new ArrayList<>();

    @Min(1)
    private int pages;

    @ManyToOne
    private Category category;


}
